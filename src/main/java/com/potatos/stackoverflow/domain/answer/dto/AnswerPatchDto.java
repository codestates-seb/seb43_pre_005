package com.potatos.stackoverflow.domain.answer.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AnswerPatchDto {

    private Long answerId;
    @NotBlank(message = "답변에 들어갈 내용을 입력해주세요.")
    private String content;
    private Long questionId;

}
