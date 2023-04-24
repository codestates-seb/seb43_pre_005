package com.potatos.stackoverflow.domain.member.response;

import com.potatos.stackoverflow.domain.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MypageResponse {
    private String displayName;
    private String title;
    private String introduce;
    private String email;
    private List<Question> question;
    //private Long answerCount;
    private Long questionCount;

}