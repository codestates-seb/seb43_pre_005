package com.potatos.stackoverflow.domain.member.dto;

import com.potatos.stackoverflow.domain.answer.entity.Answer;
import com.potatos.stackoverflow.domain.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MyPageResponseDto {
    private String displayName;
    private String title;
    private String introduce;
    private String email;

    private int questionsCount;
    private int answersCount;

    List<Question> questionList;
    List<Answer> answerList;

    public static MyPageResponseDto of(String displayName, String title, String introduce, String email,
                                       List<Question> questionList, int answersCount) {
        MyPageResponseDto mypage = new MyPageResponseDto();

        mypage.displayName = displayName;
        mypage.title = title;
        mypage.introduce = introduce;
        mypage.email = email;
        mypage.questionsCount = questionList.size();
        mypage.answersCount = answersCount;
        mypage.questionList = questionList;

        return mypage;
    }
}
