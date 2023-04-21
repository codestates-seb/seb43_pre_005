package com.potatos.stackoverflow.domain.question.service;

import com.potatos.stackoverflow.domain.member.service.MemberService;
import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final MemberService memberService;

    private final QuestionRepository questionRepository;


    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

}
