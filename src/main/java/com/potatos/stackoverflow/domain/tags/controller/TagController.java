package com.potatos.stackoverflow.domain.tags.controller;


import com.potatos.stackoverflow.domain.tags.dto.TagPostDto;
import com.potatos.stackoverflow.domain.tags.entity.Tag;
import com.potatos.stackoverflow.domain.tags.mapper.TagMapper;
import com.potatos.stackoverflow.domain.tags.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//pageNation import
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

@Controller
@RequestMapping("stackOverflow/tags")
public class TagController {

    private TagService tagService;
    private TagMapper mapper;

    public TagController(TagService tagService, TagMapper mapper){
        this.mapper = mapper;
        this.tagService = tagService;
    }


    //테스트 API로 실제로 사용하지 않습니다(POST), DB에 테스트 데이터 적재용입니다.
    @PostMapping
    public ResponseEntity postTag(@RequestBody TagPostDto tagDto){

        Tag tag = mapper.postTagDtoToEntity(tagDto);

        Tag response = tagService.createTag(tag);

        return new ResponseEntity<>(mapper.tagResponseToDto(response), HttpStatus.CREATED);
    }

    /*@GetMapping
    public ResponseEntity getTags(){

        List<Tag> tags = tagService.findTags();
        return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity getTagsPage(@RequestParam(value = "page", defaultValue = "0")int page){
        Page<Tag> paging = this.tagService.getTags(page);

        List<Tag> response = paging.getContent();

        return new ResponseEntity<List<Tag>>(response, HttpStatus.OK);
    }


}
