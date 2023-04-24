package com.potatos.stackoverflow.domain.question.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potatos.stackoverflow.domain.tags.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "question_tags")
@NoArgsConstructor
@Getter
@Setter
public class QuestionTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionTagId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;


}
