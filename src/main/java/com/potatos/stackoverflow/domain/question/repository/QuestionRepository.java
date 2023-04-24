package com.potatos.stackoverflow.domain.question.repository;

import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.tags.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository  extends JpaRepository<Question, Long> {

    //search
    Page<Question> findByTitleContaining(Pageable pageable, String searchWord);

    //mypage
    List<Question> findByMemberId(Long memberId);

    Long countByMemberId(Long memberId);
}
