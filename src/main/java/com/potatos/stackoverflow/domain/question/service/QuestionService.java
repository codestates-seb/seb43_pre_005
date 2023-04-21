package com.potatos.stackoverflow.domain.question.service;

import com.potatos.stackoverflow.domain.member.service.MemberService;
import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final MemberService memberService;

    private final QuestionRepository questionRepository;

    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

    public Question getQuestion(Long questionId){

        Question findQuestion = findVerifiedQuestion(questionId);
        questionRepository.save(findQuestion);

        return findQuestion;
    }


    public Question findVerifiedQuestion(Long questionId){

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        return optionalQuestion.orElseThrow(
                () -> new NoSuchMessageException("게시물이 없습니다."));
    }



}
