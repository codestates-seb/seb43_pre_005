package com.potatos.stackoverflow.domain.question.repository;

import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.entity.QuestionTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Long> {

    //바로 tagId에 접근하지 말고 Tag 객체부터 접근하는 게 나을 거 같
    List<QuestionTag>  findAllByTagId(Long tagId); //db안에는 객체 없어서 컬럼이름으로만 들어감 !!!
    Page<QuestionTag> findAllByTagId(Long tagId, Pageable pageable);


}
