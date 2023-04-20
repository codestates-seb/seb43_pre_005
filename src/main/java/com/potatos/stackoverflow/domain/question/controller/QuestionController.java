package com.potatos.stackoverflow.domain.question.controller;


import com.potatos.stackoverflow.domain.question.dto.QuestionDto;
import com.potatos.stackoverflow.domain.question.dto.QuestionResponseDto;
import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.mapper.QuestionMapper;
import com.potatos.stackoverflow.domain.question.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController{

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;


    /*
     * 게시물 생성하는 메서드 입니다.
     * 필요값 : memberId, QuestionPostDto
     * 아웃풋 : QuestionResponseDto
     */
    @PostMapping("/ask")
    public ResponseEntity<QuestionResponseDto> postQuestion(@Valid @RequestBody QuestionDto.Post questionPostDto){

        Question request = questionMapper.questionPostDtoToQuestion(questionPostDto);
        Question question = questionService.createQuestion(request);

        return new ResponseEntity<>(questionMapper.questionToQuestionResponse(question), HttpStatus.CREATED);
    }
}