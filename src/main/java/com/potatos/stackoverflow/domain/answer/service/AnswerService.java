package com.potatos.stackoverflow.domain.answer.service;

import com.potatos.stackoverflow.domain.answer.entity.Answer;
import com.potatos.stackoverflow.domain.answer.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
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
    public List<Answer> findAnswers(Long questionId){

        return answerRepository.findAnswersByQuestionId(questionId);
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



    public Answer findVerifiedAnswer(Long anwerId){
        Optional<Answer> optionalAnswer = answerRepository.findById(anwerId);

        return optionalAnswer.orElseThrow();
    }
}
