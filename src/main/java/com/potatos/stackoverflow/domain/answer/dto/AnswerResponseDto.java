package com.potatos.stackoverflow.domain.answer.dto;

import com.potatos.stackoverflow.domain.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AnswerResponseDto {

    private Long answerId;
    private String content;
    private String memberName;
    private LocalDateTime createdAt;
    private int answerLike;

    public AnswerResponseDto(Answer answer){
        this.answerId = answer.getAnswerId();
        this.content = answer.getContent();
        this.memberName = answer.getMember().getDisplayName();
        this.createdAt = answer.getCreatedAt();
        this.answerLike = answer.getAnswerLike();
    }

}
