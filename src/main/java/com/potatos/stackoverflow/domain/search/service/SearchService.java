package com.potatos.stackoverflow.domain.search.service;

import com.potatos.stackoverflow.domain.question.mapper.entity.Question;
import com.potatos.stackoverflow.domain.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

//pageNation import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Service
public class SearchService {

    private final QuestionRepository questionRepository;

    public SearchService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public Page<Question> searchList(int page, String searchWord){

        Pageable pageable = PageRequest.of(page, 10);
        Page<Question> searchList = questionRepository.findByTitleContaining(pageable, searchWord);

        return searchList;
    }


}