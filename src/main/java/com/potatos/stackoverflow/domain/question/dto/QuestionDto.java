package com.potatos.stackoverflow.domain.question.dto;

import com.potatos.stackoverflow.domain.member.dto.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
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

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response{

        private Long questionId;

        private MemberResponseDto member;

        private String title;

        private String content;

        private String text;

        private List<String> tags;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

    }
}
