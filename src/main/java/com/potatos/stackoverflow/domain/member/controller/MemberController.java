package com.potatos.stackoverflow.domain.member.controller;

import com.potatos.stackoverflow.domain.member.dto.MembersPageDto;
import com.potatos.stackoverflow.domain.member.response.MypageResponse;
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


        @GetMapping("/{member_id}")
        public ResponseEntity getMyPage(@PathVariable("member_id")Long memberId){

            MypageResponse response = memberService.readMyPage(memberId);
            //1. User의 정보를 불러옴 > 출력1

            //2. user에 맵핑되어있는 questionId를 불러옴 > 출력2

            //3. questionId를 통해 count를 진행 > 출력3

            //return ResponseEntity(null, HttpStatus.OK);

            return new ResponseEntity<MypageResponse>(response, HttpStatus.OK);
        }
}
