package com.potatos.stackoverflow.domain.tags.entity;

import com.potatos.stackoverflow.domain.question.entity.QuestionTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "tags")
@Component
public class Tag{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "tag")
    private List<QuestionTag> questionTags = new ArrayList<>();

    public Tag(long l, String name, String description) {

    }

/*    public void addQuestionTag(QuestionTag questionTag) {
        this.questionTags.add(questionTag);
        questionTag.setTag(this);
    }*/


}
