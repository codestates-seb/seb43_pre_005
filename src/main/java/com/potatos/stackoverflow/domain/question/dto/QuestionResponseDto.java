package com.potatos.stackoverflow.domain.question.dto;


import com.potatos.stackoverflow.domain.question.mapper.entity.Question;
import lombok.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class QuestionResponseDto {

    private Long questionId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    @Size(max = 5)
    private List<String > tagNames = new ArrayList<>();

    private Long memberId;
    private String memberName;

    public QuestionResponseDto(Question question){
        this.questionId = question.getQuestionId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.memberId = question.getMember().getId();
        this.memberName = question.getMember().getDisplayName();
        this.createdAt = question.getCreatedAt();
        this.tagNames = question.getTagNames();
    }

}
