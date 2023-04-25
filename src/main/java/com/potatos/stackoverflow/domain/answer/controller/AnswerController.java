package com.potatos.stackoverflow.domain.answer.controller;


import com.potatos.stackoverflow.domain.answer.dto.AnswerPostDto;
import com.potatos.stackoverflow.domain.answer.dto.AnswerResponseDto;
import com.potatos.stackoverflow.domain.answer.entity.Answer;
import com.potatos.stackoverflow.domain.answer.service.AnswerService;
import com.potatos.stackoverflow.domain.member.entity.Member;
import com.potatos.stackoverflow.domain.member.service.MemberService;
import com.potatos.stackoverflow.domain.question.dto.QuestionPostDto;
import com.potatos.stackoverflow.domain.question.dto.QuestionResponseDto;
import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.service.QuestionService;
import com.potatos.stackoverflow.domain.response.MultiResponseDto;
import com.potatos.stackoverflow.domain.response.SingleResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions/{questionId}/answers")
@AllArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final MemberService memberService;


    /* POST
     * 답변 생성하는 메서드 입니다.
     * 필요값 : AnswerPostDto
     * 출력값 : SingleResponseDto-AnswerResponseDto, 201.CREATED
     */
    @PostMapping
    public ResponseEntity<SingleResponseDto<AnswerResponseDto>> postAnswer(@Positive @PathVariable Long questionId, @RequestBody AnswerPostDto answerPostDto) throws ChangeSetPersister.NotFoundException{

        Answer answer = new Answer();
            answer.setContent(answerPostDto.getContent());

        Question question = questionService.findVerifiedQuestion(questionId);
            answer.setQuestion(question);

        Member member = memberService.findMember(answerPostDto.getMemberId());
            answer.setMember(member);
            answer.setMemberName(member.getDisplayName());

        answerService.createAnswer(answer);

        AnswerResponseDto responseDto = new AnswerResponseDto(answer);

        return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.CREATED);
    }


    /* GET
     * 답변 전체 조회 메서드 입니다.
     * 필요값 : questionId
     * 출력값 : SingleResponseDto-AnswerResponseDto, 200.OK
     */
    @GetMapping
    public ResponseEntity<SingleResponseDto<List<AnswerResponseDto>>> getAnswer(@Positive @PathVariable Long questionId){
        List<Answer> answers = answerService.findAnswers(questionId);
        List<AnswerResponseDto> answerResponseDtoList = answers.stream()
                .map(AnswerResponseDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new SingleResponseDto<>(answerResponseDtoList), HttpStatus.OK);
    }

}
