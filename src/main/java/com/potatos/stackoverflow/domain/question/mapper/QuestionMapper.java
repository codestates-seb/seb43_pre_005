package com.potatos.stackoverflow.domain.question.mapper;


import com.potatos.stackoverflow.domain.question.dto.QuestionDto;
import com.potatos.stackoverflow.domain.question.dto.QuestionResponseDto;
import com.potatos.stackoverflow.domain.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@RequiredArgsConstructor
public class QuestionMapper {
    public Question questionPostDtoToQuestion(@Valid QuestionDto.Post questionPostDto) {

        Question question = new Question();
        question.setTitle(questionPostDto.getTitle());
        question.setContent(questionPostDto.getContent());
        //question.setMember(questionPostDto.getMemberId());

        return question;
    }

    public QuestionResponseDto questionToQuestionResponse(Question question) {

        return new QuestionResponseDto(
                question.getId(),
                question.getTitle(),
                question.getContent());
    }
    /*
     *
     *
     *
     */
}
