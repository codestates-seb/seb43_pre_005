package com.potatos.stackoverflow.domain.answer.repository;

import com.potatos.stackoverflow.domain.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
