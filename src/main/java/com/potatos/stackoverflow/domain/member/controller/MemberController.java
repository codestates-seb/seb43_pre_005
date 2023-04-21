package com.potatos.stackoverflow.domain.member.controller;

import com.potatos.stackoverflow.domain.member.dto.MembersPageDto;
import com.potatos.stackoverflow.domain.member.service.MemberService;
import com.potatos.stackoverflow.domain.member.dto.MemberPostDto;
import com.potatos.stackoverflow.domain.member.dto.MemberResponseDto;
import com.potatos.stackoverflow.util.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;

@RequestMapping("/users")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity createMember(@RequestBody MemberPostDto memberPostDto){

        System.out.println("controller > create member");

        MemberResponseDto responseDto=memberService.saveMember(memberPostDto);
        URI location = UriCreator.createUri("/users", 1);

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/group")
    public ResponseEntity getUsers(@RequestParam(value = "page", defaultValue = "0") int page) {

        List<MembersPageDto> response = this.memberService.getMembersPage(page);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
