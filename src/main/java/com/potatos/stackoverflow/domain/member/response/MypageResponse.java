package com.potatos.stackoverflow.domain.member.response;

import com.potatos.stackoverflow.domain.question.response.MyPageQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MypageResponse {
    private String displayName;
    private String title;
    private String introduce;
    private String email;

    private MyPageQuestion questions; //response 선언 -> Question Response
    //private Long answerCount;

    private Long questionCount;


//    public static List<Question> setQuestion(List<Question> questionResult) {
//       this.question = questionResult;
//        return question;
//    }

    public static MypageResponse of(String displayName, String title, String introduce, String email, MyPageQuestion questions, Long questionCount){
        MypageResponse mypageResponse = new MypageResponse();
        mypageResponse.displayName = displayName;
        mypageResponse.title = title;
        mypageResponse.introduce = introduce;
        mypageResponse.email = email;
        mypageResponse.questions = questions;
        mypageResponse.questionCount = questionCount;


        return mypageResponse;

    }

}