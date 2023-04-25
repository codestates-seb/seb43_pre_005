package com.potatos.stackoverflow.domain.question.repository;

import com.potatos.stackoverflow.domain.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository  extends JpaRepository<Question, Long> {

    Page<Question> findAll(Pageable pageable);

    //search
    Page<Question> findByTitleContaining(Pageable pageable, String searchWord);

    //List<Question> findAllById(Long questionId);
}
