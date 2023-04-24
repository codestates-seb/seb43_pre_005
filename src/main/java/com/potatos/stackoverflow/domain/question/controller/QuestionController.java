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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Question savedQuestion = questionRepository.save(question);

       // Question createdQuestion = questionService.createQuestion(question);

        QuestionResponseDto responseDto = new QuestionResponseDto(savedQuestion);
        //responseDto.setTags(questionPostDto.getTagList());

       // URI location = UriCreator.createUri(QUESTION_DEFAULT_URL, 1);

        return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.CREATED);
    }

    /*
     * 게시물 조회하는 메서드 입니다.
     * 필요값 : questionId
     * 아웃풋 : QuestionResponseDto
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<SingleResponseDto<QuestionResponseDto>> getQuestion(@PathVariable Long questionId){

        Question question = questionService.getQuestion(questionId);

        QuestionResponseDto questionResponseDto = new QuestionResponseDto(question);

        return new ResponseEntity<>(new SingleResponseDto<>(questionResponseDto), HttpStatus.OK);
    }


    /*
     * 게시물 조회하는 메서드 입니다.
     * 필요값 : questionId
     * 아웃풋 : QuestionResponseDto
     */
    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam(required = false, defaultValue = "1") int page, @Positive @RequestParam(required = false, defaultValue = "15") int size){

        Page<Question> questionPage = questionService.getQuestions(page-1, size);

        List<Question> questionList = questionPage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(questionList, questionPage), HttpStatus.OK);


      /*  Page<Question> questionPage = questionService.getQuestions(page - 1, size);

        PageInfo pageInfo = new PageInfo(page, size, (int) questionPage.getTotalElements(), questionPage.getTotalPages());

        List<Question> questionList = questionPage.getContent();
        List<QuestionResponseDto> questionResponseDtoList = questionMapper.questionToQuestionResponseList(questionList);

        return new ResponseEntity<>(new MultiResponseDto<>(questionResponseDtoList, pageInfo), HttpStatus.OK);*/

    }


}