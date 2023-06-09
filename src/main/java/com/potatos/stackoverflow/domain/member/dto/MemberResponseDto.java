package com.potatos.stackoverflow.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberResponseDto {
    String displayName;
    String email;
    String password;
    String memberStatus;
}
