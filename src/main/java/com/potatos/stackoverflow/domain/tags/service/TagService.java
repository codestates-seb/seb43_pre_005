package com.potatos.stackoverflow.domain.tags.service;

import com.potatos.stackoverflow.domain.tags.repository.TagRepository;
import com.potatos.stackoverflow.domain.tags.mapper.TagMapper;
import org.springframework.stereotype.Service;
import com.potatos.stackoverflow.domain.tags.entity.Tag;

import java.util.List;

//pageNation import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class TagService {

    TagRepository tagRepository;
    TagMapper mapper;

    public TagService(TagRepository tagRepository, TagMapper mapper){
        this.tagRepository = tagRepository;
        this.mapper = mapper;

    }


    //테스트 API로 실제로 사용하지 않습니다
    public Tag createTag(Tag tag){
        return tagRepository.save(tag);
    }

    public Page<Tag> getTags(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return this.tagRepository.findAll(pageable);
    }
}











