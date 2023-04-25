package com.potatos.stackoverflow.domain.search.service;

import com.potatos.stackoverflow.domain.question.mapper.entity.Question;
import com.potatos.stackoverflow.domain.question.repository.QuestionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//pageNation import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;



@Service
public class SearchService {

    private final QuestionRepository questionRepository;

    public SearchService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public Page<Question> getSearchTest(int page, int size, String searchWord){

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("questionId").descending());
        Page<Question> searchList = questionRepository.findByTitleContaining(pageRequest, searchWord);

        return searchList;
    }

}