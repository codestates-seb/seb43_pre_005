package com.potatos.stackoverflow.domain.question.dto;

import com.potatos.stackoverflow.domain.member.entity.Member;
import com.potatos.stackoverflow.domain.question.entity.QuestionTag;
import com.potatos.stackoverflow.domain.tags.entity.Tag;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuestionPostDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private List<Long> tagIds = new ArrayList<>();

    private Long memberId;

}
