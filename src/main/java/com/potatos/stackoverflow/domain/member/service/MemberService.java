package com.potatos.stackoverflow.domain.member.service;

import com.potatos.stackoverflow.domain.member.dto.MembersPageDto;
import com.potatos.stackoverflow.domain.member.repository.MemberRepository;
import com.potatos.stackoverflow.domain.member.dto.MemberPostDto;
import com.potatos.stackoverflow.domain.member.dto.MemberResponseDto;
import com.potatos.stackoverflow.domain.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//pageNation import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member saveMember(MemberPostDto memberPostDto) {
        System.out.println("service > save member");
        Member member = Member.of(
                memberPostDto.getDisplayName(),
                memberPostDto.getEmail(),
                memberPostDto.getPassword(),
                memberPostDto.getMemberStatus());

        Member savedMember=memberRepository.save(member);
        //Member savedMember=memberRepository.findById()

        System.out.println("service > save member:"+savedMember.getId());

        //@@@@@@@@@@@@password 인코더 작업 필요함!!!!!!!!!!!!!
        //pwd:{bcrypt}$2a$10$SpS3yu/W/h75eSl6qZrSce593CuiJKmxNcWEdZJxlwBMFY/VNSblq



        return savedMember;
    }


    public void saveOAuthMember(Member member) {
        System.out.println("service > save oauth member");

        memberRepository.save(member);
    }


    public List<MembersPageDto> getMembersPage(int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Member> response = this.memberRepository.findAll(pageable);

        return response.stream()
                .map(member -> new MembersPageDto(member.getId(), member.getDisplayName()))
                .collect(Collectors.toList());

    }

    public Member findMember(Long memberId){
        return findVerifiedMember(memberId);
    }

    private Member findVerifiedMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }

    public MemberResponseDto readMyPage(Long memberId) {

        Member member=memberRepository.findById(memberId).orElseThrow();

        MemberResponseDto responseDto = new MemberResponseDto(
                member.getDisplayName(),
                member.getEmail(),
                member.getPassword(),
                member.getMemberStatus().getStrStatus()
        );

        return responseDto;
    }


    public void deleteMember(long memberId) {
        System.out.println("service > delete member");

        memberRepository.deleteById(memberId);
    }
}
