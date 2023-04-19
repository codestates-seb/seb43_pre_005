package com.potatos.stackoverflow.tags.controller;


import com.potatos.stackoverflow.tags.dto.TagPostDto;
import com.potatos.stackoverflow.tags.entity.Tag;
import com.potatos.stackoverflow.tags.mapper.TagMapper;
import com.potatos.stackoverflow.tags.response.TagResponse;
import com.potatos.stackoverflow.tags.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("stackOverflow/tags")
public class TagController {

    private TagService tagService;
    private TagMapper mapper;

    public TagController(TagService tagService, TagMapper mapper){
        this.mapper = mapper;
        this.tagService = tagService;
    }


    //테스트 API로 실제로 사용하지 않습니다(POST)
    @PostMapping
    public ResponseEntity postTag(@RequestBody TagPostDto tagDto){

        Tag tag = mapper.postTagDtoToEntity(tagDto); //entity가 된다

        Tag response = tagService.createTag(tag); //entity를 비즈니스 로직 돌림

        return new ResponseEntity<>(mapper.tagResponseToDto(response), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getTags(){

        return new ResponseEntity<>(HttpStatus.OK);
    }





}
