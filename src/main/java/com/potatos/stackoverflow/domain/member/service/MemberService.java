package com.potatos.stackoverflow.domain.member.service;

import com.potatos.stackoverflow.domain.member.dto.MembersPageDto;
import com.potatos.stackoverflow.domain.member.repository.MemberRepository;
import com.potatos.stackoverflow.domain.member.dto.MemberPostDto;
import com.potatos.stackoverflow.domain.member.dto.MemberResponseDto;
import com.potatos.stackoverflow.domain.member.entity.Member;
import org.springframework.stereotype.Service;

//pageNation import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

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


    public List<MembersPageDto> getMembersPage(int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Member> response = this.memberRepository.findAll(pageable);

        return response.stream()
                .map(member -> new MembersPageDto(member.getId(), member.getDisplayName()))
                .collect(Collectors.toList());

    }
}
