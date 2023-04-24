package com.potatos.stackoverflow.domain.member.service;

import com.potatos.stackoverflow.domain.member.dto.MembersPageDto;
import com.potatos.stackoverflow.domain.member.repository.MemberRepository;
import com.potatos.stackoverflow.domain.member.dto.MemberPostDto;
import com.potatos.stackoverflow.domain.member.dto.MemberResponseDto;
import com.potatos.stackoverflow.domain.member.entity.Member;
import com.potatos.stackoverflow.domain.member.response.MypageResponse;
import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.repository.QuestionRepository;
import com.potatos.stackoverflow.domain.question.response.MyPageQuestion;
import org.springframework.stereotype.Service;

//pageNation import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //mypage
    private final QuestionRepository questionRepository;

    public MemberService(MemberRepository memberRepository, QuestionRepository questionRepository) {
        this.memberRepository = memberRepository;
        this.questionRepository = questionRepository;
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

    public MypageResponse readMyPage(Long memberId){ //최종적으로 담겨야 할 거는 readMyPage의 응답임

        //1. User의 정보를 불러옴 > 출력1(displayName, title, introduce)
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();


        //2. user에 맵핑되어있는 questionId를 불러옴 > 출력2
        //List<Question> writeQuestions = questionRepository.findByMemberId(memberId);


        //3. questionId를 통해 count를 진행 > 출력3

        List<Question> question =this.questionRepository.findByMemberId(memberId);

        List<MyPageQuestion> myPageQuestions = MyPageQuestion.listOf(question);


        //하나의 MyPageQuestion 채우기
//        MyPageQuestion myPageQuestion = MyPageQuestion.of(
//        //getId
//
//        //getTitle
//        )



        //최종 출력 Response
        MypageResponse mypageResponse = MypageResponse.of(
                member.getDisplayName(),
                member.getTitle(),
                member.getIntroduce(),
                member.getEmail(),
                (MyPageQuestion) myPageQuestions,
        this.questionRepository.countByMemberId(memberId));






                //this.questionRepository.countByMemberId(memberId)); //question 개수
        //이렇게 하면 member 객체를 통해 담을 수 있는 값을 담고,
        //이제 setter로 접근해서 count값을 넣어주어야 response를 쓸 수 있을듯
        //아니다 위에 로직에서 count들을 다 계산해오자


        return mypageResponse;

    }

}
