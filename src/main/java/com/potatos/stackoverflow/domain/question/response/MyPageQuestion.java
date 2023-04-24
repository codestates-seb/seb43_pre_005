package com.potatos.stackoverflow.domain.question.response;

import com.potatos.stackoverflow.domain.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageQuestion {
    private static String title;
    private Long id;
    //private String title;

    //하나의 MyPageQuestion생성
    public static MyPageQuestion of(Question question){
        MyPageQuestion myPageQuestion = new MyPageQuestion();
        myPageQuestion.id = question.getId();
        MyPageQuestion.title = question.getTitle();

        return myPageQuestion;
    }

    //MyPageQuestion을 List타입의 MypageList로 만듦
    public static List<MyPageQuestion> listOf(List<Question> questions){

        List<MyPageQuestion> myPageQuestions =
                questions.stream()
                .map(question -> of(question))
                        .collect(Collectors.toList());

        return myPageQuestions;
    }
}
