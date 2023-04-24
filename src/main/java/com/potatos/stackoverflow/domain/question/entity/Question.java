package com.potatos.stackoverflow.domain.question.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.potatos.stackoverflow.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "questions")
@NoArgsConstructor
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "members_id")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionTag> questionTags = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void setQuestionTags(List<QuestionTag> questionTag) {
        this.questionTags = questionTag;
    }

}
