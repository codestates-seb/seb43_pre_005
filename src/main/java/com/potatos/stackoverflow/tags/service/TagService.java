package com.potatos.stackoverflow.tags.service;

import com.potatos.stackoverflow.tags.dto.TagPostDto;
import com.potatos.stackoverflow.tags.mapper.TagMapper;
import com.potatos.stackoverflow.tags.repository.TagRepository;
import com.potatos.stackoverflow.tags.response.TagResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.potatos.stackoverflow.tags.entity.Tag;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {


    TagRepository tagRepository;
    TagMapper mapper;

    public TagService(TagRepository tagRepository, Tag tag){
        this.tagRepository = tagRepository;
        this.mapper = mapper;

    }


    //테스트 API로 실제로 사용하지 않습니다
    public Tag createTag(Tag tag){
        return tagRepository.save(tag);
    }

}











