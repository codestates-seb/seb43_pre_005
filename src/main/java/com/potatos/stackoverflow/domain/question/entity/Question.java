package com.potatos.stackoverflow.domain.question.entity;


import com.potatos.stackoverflow.domain.answer.entity.Answer;
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
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ElementCollection
    private List<String> tagNames = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionTag> questionTags = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

    public void setQuestionTags(List<QuestionTag> questionTag) {
        this.questionTags = questionTag;
    }

    public void setAnswers(List<Answer> answers){
        this.answers = answers;
    }

}
