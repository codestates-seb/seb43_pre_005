package com.potatos.stackoverflow.domain.question.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionResponseDto {

    private Long questionId;
    private String title;
    private String content;

    //private MemberResponseDto member;

}
