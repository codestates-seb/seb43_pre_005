package com.potatos.stackoverflow.domain.question.dto;


import com.potatos.stackoverflow.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class QuestionPatchDto{
    private Member member;
}
