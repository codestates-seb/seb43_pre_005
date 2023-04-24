package com.potatos.stackoverflow.domain.question.service;

import com.potatos.stackoverflow.domain.member.service.MemberService;
import com.potatos.stackoverflow.domain.question.dto.QuestionPostDto;
import com.potatos.stackoverflow.domain.question.dto.QuestionResponseDto;
import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    /*
     * 게시물 업로드하는 method 입니다.
     *
     */
    @Transactional
    public Question createQuestion(Question question){

        return questionRepository.save(question);
    }

    /*
     * 게시물 하나를 조회하는 method 입니다.
     *
     */
    public Question getQuestion(Long questionId){

        return questionRepository.findById(questionId).orElseThrow(()-> new NoSuchMessageException("해당하는 게시물이 없습니다."));
    }

    /*
     * 게시물 리스트를 조회하는 method 입니다.
     * 입력값 : page, size
     * 출력값 : Question page
     */
    public Page<Question> getQuestions(int page, int size){

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("questionId").descending());

        return questionRepository.findAll(pageRequest);
    }


    /*
    * 게시물 존재 여부를 묻는 method 입니다.
    * 입력값 : questionId
    * 출력값 : Question
    */
    public Question findVerifiedQuestion(Long questionId){

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        return optionalQuestion.orElseThrow(
                () -> new NoSuchMessageException("게시물이 없습니다."));
    }



}
