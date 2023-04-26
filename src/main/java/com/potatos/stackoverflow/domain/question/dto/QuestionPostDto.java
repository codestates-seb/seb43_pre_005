package com.potatos.stackoverflow.domain.question.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuestionPostDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Size(max = 5)
    private List<Long> tagIds = new ArrayList<>();

    private Long memberId;

}
