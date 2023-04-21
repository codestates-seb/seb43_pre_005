package com.potatos.stackoverflow.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MembersPageDto {

    private Long id;
    private String  displayName;

}