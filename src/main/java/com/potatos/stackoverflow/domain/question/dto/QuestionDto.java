package com.potatos.stackoverflow.domain.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class QuestionDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "제목을 입력해주세요.")
        private String title;

        @NotBlank(message = "내용을 입력해주세요.")
        private String content;

       // private Long memberId;

        private List<String> tags;
    }

}
