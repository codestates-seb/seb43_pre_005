package com.potatos.stackoverflow.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPostDto {

    String displayName;
    String email;
    String password;
    String memberStatus;
}
