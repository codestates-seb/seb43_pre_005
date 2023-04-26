package com.potatos.stackoverflow.domain.member.repository;

import com.potatos.stackoverflow.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
