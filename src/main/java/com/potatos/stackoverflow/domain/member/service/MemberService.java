package com.potatos.stackoverflow.domain.member.service;

import com.potatos.stackoverflow.domain.answer.entity.Answer;
import com.potatos.stackoverflow.domain.answer.service.AnswerService;
import com.potatos.stackoverflow.domain.member.dto.MembersPageDto;
import com.potatos.stackoverflow.domain.member.dto.MyPageResponseDto;
import com.potatos.stackoverflow.domain.member.repository.MemberRepository;
import com.potatos.stackoverflow.domain.member.dto.MemberPostDto;
import com.potatos.stackoverflow.domain.member.dto.MemberResponseDto;
import com.potatos.stackoverflow.domain.member.entity.Member;
import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.service.QuestionService;
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
    private final QuestionService questionService;
    private final AnswerService answerService;


    public MemberService(MemberRepository memberRepository, QuestionService questionService, AnswerService answerService) {
        this.memberRepository = memberRepository;
        this.questionService = questionService;
        this.answerService = answerService;

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

    /*
     * 마이페이지 조회
     */
    public MyPageResponseDto readMyPage(Long memberId) {

        Member member=memberRepository.findById(memberId).orElseThrow();

        List<Question> questionList=questionService.getQuestionListByMember(member);
        int answerCount = answerService.getAnswersCountByMemberId(member.getId());

        MyPageResponseDto responseDto=MyPageResponseDto.of(member.getDisplayName(),member.getTitle(), member.getIntroduce(),
                member.getEmail(),questionList, answerCount);

        return responseDto;
    }


    public void deleteMember(long memberId) {
        System.out.println("service > delete member");

        memberRepository.deleteById(memberId);
    }

    public void logoutMember(long memberId) {
        System.out.println("service > logout");

        System.out.println("뭔동작하냐 uesrId:"+memberId);

    }
}
