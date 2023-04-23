package com.potatos.stackoverflow.domain.search.controller;

import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.search.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("search")
@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity getSearchPage(@RequestParam(value = "word") String word, int page){


        Page<Question> searchResult = this.searchService.searchList(page, word);
        List<Question> response = searchResult.getContent();


        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}
