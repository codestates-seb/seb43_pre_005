package com.potatos.stackoverflow.domain.answer.service;

import com.potatos.stackoverflow.domain.answer.entity.Answer;
import com.potatos.stackoverflow.domain.answer.repository.AnswerRepository;
import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.service.QuestionService;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, QuestionService questionService){
        this.answerRepository = answerRepository;
        this.questionService = questionService;
    }


    /*
     * 답변 업로드하는 method 입니다.
     * 입력값 : Answer
     * 출력값 : Answer
     */
    public Answer createAnswer(Answer answer){
        return answerRepository.save(answer);
    }

    /*
     * 답변 불러오는 method 입니다.
     * 입력값 : questionId
     * 출력값 : List<Answer>
     */
    public List<Answer> findAnswersByQuestionId(Long questionId){

        Question question = questionService.findVerifiedQuestion(questionId);

        return question.getAnswers();
    }

    /*
     * 답변 수정하는 method 입니다.
     * 입력값 : Answer
     * 출력값 : Answer
     */
    public Answer updateAnswer(Answer answer){

        return answerRepository.save(answer);
    }


    /*
     * 답변 삭제하는 method 입니다.
     * 입력값 : answerId
     * 출력값 : void
     */
    public void deleteAnswer(Long answerId){
        answerRepository.delete(findVerifiedAnswer(answerId));
    }



    public Answer findVerifiedAnswer(Long answerId){
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        return optionalAnswer.orElseThrow();
    }
}
