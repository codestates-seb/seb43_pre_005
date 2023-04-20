package com.potatos.stackoverflow.domain.question.dto;

import com.potatos.stackoverflow.domain.tags.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class QuestionDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post{
        @NotBlank(message = "제목을 입력해주세요.")
        private String title;

        @NotBlank(message = "내용을 입력해주세요.")
        private String content;

        private long memberId;

        private List<Tag> tags;
    }

   /* @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch{



    }*/

}
