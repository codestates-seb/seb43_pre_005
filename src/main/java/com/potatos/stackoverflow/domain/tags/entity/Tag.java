package com.potatos.stackoverflow.domain.tags.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Component
public class Tag{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    public static Tag of(Long id, String name, String description){
        Tag tag = new Tag();
        tag.id = 0L;
        tag.name = name;
        tag.description = description;

        return tag;
    }



}
