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
import com.potatos.stackoverflow.domain.response.SingleResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions/answers")
@AllArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<SingleResponseDto<AnswerResponseDto>> postAnswer(@RequestBody AnswerPostDto answerPostDto) throws ChangeSetPersister.NotFoundException{

        Answer answer = new Answer();
            answer.setContent(answerPostDto.getContent());
        Question question = questionService.findVerifiedQuestion(answerPostDto.getQuestionId());
            answer.setQuestion(question);
        Member member = memberService.findMember(answerPostDto.getMemberId());
            answer.setMember(member);
            answer.setMemberName(member.getDisplayName());

        answerService.createAnswer(answer);

        AnswerResponseDto responseDto = new AnswerResponseDto(answer);

        return new ResponseEntity<>(new SingleResponseDto<>(responseDto), HttpStatus.CREATED);
    }
}
