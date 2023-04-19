package com.potatos.stackoverflow.member.service;

import com.potatos.stackoverflow.member.dto.MemberPostDto;
import com.potatos.stackoverflow.member.dto.MemberResponseDto;
import com.potatos.stackoverflow.member.entity.Member;
import com.potatos.stackoverflow.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto saveMember(MemberPostDto memberPostDto) {
        System.out.println("service > save member");
        Member member = Member.of(
                memberPostDto.getDisplayName(),
                memberPostDto.getEmail(),
                memberPostDto.getPassword(),
                memberPostDto.getMemberStatus());

        memberRepository.save(member);

        MemberResponseDto memberResponseDto = new MemberResponseDto(
                member.getDisplayName(),
                member.getEmail(),
                member.getPassword(),
                member.getMemberStatus().getStrStatus());

        return memberResponseDto;
    }
}
