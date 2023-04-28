package com.potatos.stackoverflow.domain.member.controller;

import com.potatos.stackoverflow.domain.member.dto.MembersPageDto;
import com.potatos.stackoverflow.domain.member.dto.MyPageResponseDto;
import com.potatos.stackoverflow.domain.member.entity.Member;
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

    @PostMapping("/signup")
    public ResponseEntity createMember(@RequestBody MemberPostDto memberPostDto){

        System.out.println("controller > create member");

        Member member=memberService.saveMember(memberPostDto);
        URI location = UriCreator.createUri("/users", member.getId());

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/group")
    public ResponseEntity getUsers(@RequestParam(value = "page", defaultValue = "0") int page) {

        List<MembersPageDto> response = this.memberService.getMembersPage(page);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{member_id}")
    public ResponseEntity getMyPage(@PathVariable("member_id")Long memberId) {

        System.out.println("controller > get one member");

        MyPageResponseDto responseDto=memberService.readMyPage(memberId);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity deleteMember(@PathVariable long userId){

        System.out.println("controller > delete member");

        memberService.deleteMember(userId);

        return new ResponseEntity(HttpStatus.OK);
    }

    //로그아웃을 여기 컨트롤러에 넣는게 맞는가?
    @PostMapping("/logout/{userId}")
    public ResponseEntity logOutMember(@PathVariable long userId) {

        System.out.println("controller > logout member");

        memberService.logoutMember(userId);


        return new ResponseEntity(HttpStatus.OK);

    }
}
