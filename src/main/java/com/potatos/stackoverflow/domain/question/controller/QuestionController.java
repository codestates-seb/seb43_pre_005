package com.potatos.stackoverflow.domain.question.controller;


import com.potatos.stackoverflow.domain.member.entity.Member;
import com.potatos.stackoverflow.domain.member.service.MemberService;
import com.potatos.stackoverflow.domain.question.entity.QuestionTag;
import com.potatos.stackoverflow.domain.question.repository.QuestionRepository;
import com.potatos.stackoverflow.domain.response.MultiResponseDto;
import com.potatos.stackoverflow.domain.question.dto.QuestionPostDto;
import com.potatos.stackoverflow.domain.question.dto.QuestionResponseDto;
import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.service.QuestionService;
import com.potatos.stackoverflow.domain.response.SingleResponseDto;
import com.potatos.stackoverflow.domain.tags.entity.Tag;
import com.potatos.stackoverflow.domain.tags.repository.TagRepository;
import com.potatos.stackoverflow.domain.tags.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController{

    private final static String QUESTION_DEFAULT_URL = "/questions";
    private final QuestionService questionService;
   // private final QuestionMapper questionMapper;
    private final MemberService memberService;

    private final TagService tagService;

    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;
    /*
     * 게시물 생성하는 메서드 입니다.
     * 필요값 : memberId, QuestionPostDto
     * 아웃풋 : QuestionResponseDto
     */
    @PostMapping("/ask")
    public ResponseEntity<SingleResponseDto<QuestionResponseDto>> postQuestion(@RequestBody QuestionPostDto questionPostDto) throws ChangeSetPersister.NotFoundException {


        Question question = new Question();
            question.setTitle(questionPostDto.getTitle());
            question.setContent(questionPostDto.getContent());


       Member member = memberService.findMember(questionPostDto.getMemberId());
            question.setMember(member);
            question.setMemberName(member.getDisplayName());


        Question savedQuestion = setQuestionTag(questionPostDto, question);
        savedQuestion.setTags(savedQuestion.getQuestionTags().stream()
                .map(questionTag -> questionTag.getTag().getName())
                .collect(Collectors.toList()));

        questionService.createQuestion(savedQuestion);

        QuestionResponseDto responseDto = new QuestionResponseDto(savedQuestion);

        return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.CREATED);
    }

    /*
     * 게시물 하나를 조회하는 메서드 입니다.
     * 필요값 : questionId
     * 아웃풋 : QuestionResponseDto
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<SingleResponseDto<QuestionResponseDto>> getQuestion(@PathVariable Long questionId) throws ChangeSetPersister.NotFoundException {

        Question question = questionService.findVerifiedQuestion(questionId);

        QuestionResponseDto questionResponseDto = new QuestionResponseDto(question);

        return new ResponseEntity<>(new SingleResponseDto<>(questionResponseDto), HttpStatus.OK);
    }


    /*
     * 게시물 조회하는 메서드 입니다.
     * 필요값 : (Option) page={page_int)&size={size_int}
     * 아웃풋 : multiResponseDto
     */
    @GetMapping
    public ResponseEntity<MultiResponseDto<List<QuestionResponseDto>>> getQuestions(@Positive @RequestParam(required = false, defaultValue = "1") int page, @Positive @RequestParam(required = false, defaultValue = "10") int size){

        Page<Question> questionPage = questionService.getQuestions(page-1, size);
        List<Question> questionList = questionPage.getContent();

        List<QuestionResponseDto> questionResponseDtoList = questionList.stream()
                .map(QuestionResponseDto::new)
                .collect(Collectors.toList());

        MultiResponseDto<List<QuestionResponseDto>> multiResponseDto =
                new MultiResponseDto<>(questionResponseDtoList, questionPage);

        return new ResponseEntity<>(multiResponseDto, HttpStatus.OK);
    }


   /*
    * 게시물 조회하는 메서드 입니다.
    * 필요값 : questionId
    * 아웃풋 : ResponseEntity - > 200, OK
    */
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive Long questionId ){

        questionService.deleteQuestion(questionId);

        return new ResponseEntity(HttpStatus.OK);
    }


    /*
     * 게시물과 태그를 연결시키기 위한 메서드 입니다. post 메서드가 지저분해서 분리했습니다.
     * 필요값 : QuestionPostDto, question
     * 아웃풋 : ResponseEntity - > 200, OK
     */
    public Question setQuestionTag(QuestionPostDto questionPostDto, Question question) throws ChangeSetPersister.NotFoundException {

        List<QuestionTag> questionTags = new ArrayList<>();
        for (Long tagId : questionPostDto.getTagIds()) {
            Optional<Tag> optionalTag = tagRepository.findById(tagId);
            if (optionalTag.isEmpty()) {
                throw new ChangeSetPersister.NotFoundException();
            }
            QuestionTag questionTag = new QuestionTag();
            questionTag.setTag(optionalTag.get());
            questionTag.setQuestion(question);
            questionTags.add(questionTag);
        }
        question.setQuestionTags(questionTags);

        return question;
    }


}