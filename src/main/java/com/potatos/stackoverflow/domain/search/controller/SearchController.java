package com.potatos.stackoverflow.domain.search.controller;

import com.potatos.stackoverflow.domain.question.dto.QuestionResponseDto;
import com.potatos.stackoverflow.domain.question.mapper.entity.Question;
import com.potatos.stackoverflow.domain.response.MultiResponseDto;
import com.potatos.stackoverflow.domain.search.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("search")
@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping
    public ResponseEntity<MultiResponseDto<List<QuestionResponseDto>>> getQuestions(@Positive @RequestParam(required = false, defaultValue = "1") int page,
                                                                                    @Positive @RequestParam(required = false, defaultValue = "10") int size,
                                                                                    @RequestParam(value = "searchWord") String searchWord){

        Page<Question> questionPage = searchService.getSearchTest(page-1, size, searchWord);
        List<Question> questionList = questionPage.getContent();

        List<QuestionResponseDto> questionResponseDtoList = questionList.stream()
                .map(QuestionResponseDto::new)
                .collect(Collectors.toList());


        MultiResponseDto<List<QuestionResponseDto>> multiResponseDto =
                new MultiResponseDto<>(questionResponseDtoList, questionPage);

        return new ResponseEntity<>(multiResponseDto, HttpStatus.OK);
    }



}
