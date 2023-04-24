package com.potatos.stackoverflow.domain.question.dto;


import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.entity.QuestionTag;
import com.potatos.stackoverflow.domain.tags.entity.Tag;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class QuestionResponseDto {

    private Long questionId;

    private String title;

    private String content;

    private List<Long > tagIds;

    private Long memberId;

    public QuestionResponseDto(Question question){
        this.questionId = question.getQuestionId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.memberId = question.getMember().getId();
        this.tagIds = question.getQuestionTags().stream().map(QuestionTag::getTag).map(Tag::getId).collect(Collectors.toList());
    }


    //private MemberResponseDto member;

}
