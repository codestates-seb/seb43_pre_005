package com.potatos.stackoverflow.domain.member.service;

import com.potatos.stackoverflow.domain.member.repository.MemberRepository;
import com.potatos.stackoverflow.domain.member.dto.MemberPostDto;
import com.potatos.stackoverflow.domain.member.dto.MemberResponseDto;
import com.potatos.stackoverflow.domain.member.entity.Member;
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
