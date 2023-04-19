package com.potatos.stackoverflow.tags.mapper;

import com.potatos.stackoverflow.tags.dto.TagPostDto;
import com.potatos.stackoverflow.tags.entity.Tag;
import com.potatos.stackoverflow.tags.response.TagResponse;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public Tag postTagDtoToEntity(TagPostDto tagPostDto){
        return new Tag(
                0L, //jpa의 indentity는 0부터 시작해야 자동으로 1씩 오르는 구조를 가짐
                tagPostDto.getName(),
                tagPostDto.getDescription()
                );
    }

    public TagResponse tagResponseToDto(Tag tag){ //entity를 받아서 DTO로
        return new TagResponse(
        tag.getId(),
        tag.getName(),
        tag.getDescription()
        );
    }
}
