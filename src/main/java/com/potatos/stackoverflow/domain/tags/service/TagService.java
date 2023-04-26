package com.potatos.stackoverflow.domain.tags.service;

import com.potatos.stackoverflow.domain.tags.dto.TagPostDto;
import com.potatos.stackoverflow.domain.tags.repository.TagRepository;
//import com.potatos.stackoverflow.domain.tags.mapper.TagMapper;
import org.springframework.stereotype.Service;
import com.potatos.stackoverflow.domain.tags.entity.Tag;

import java.util.Optional;

//pageNation import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class TagService {

    TagRepository tagRepository;

    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }


    //테스트 API로 실제로 사용하지 않습니다
    public Tag createTag(TagPostDto tagDto){

        Tag tag = Tag.of(
                tagDto.getName(),
                tagDto.getDescription());

        return tagRepository.save(tag);
    }

    public Page<Tag> getTags(int page){
        Pageable pageable = PageRequest.of(page, 12);
        return this.tagRepository.findAll(pageable);
    }

    public Tag getTag(String tagName){
        return tagRepository.findByName(tagName);
    }
}











