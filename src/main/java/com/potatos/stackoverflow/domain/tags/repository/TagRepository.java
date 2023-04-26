package com.potatos.stackoverflow.domain.tags.repository;

import com.potatos.stackoverflow.domain.tags.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

//pagenation import
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface TagRepository extends JpaRepository<Tag, Long> {
    Page<Tag> findAll(Pageable pageable);

    Tag findByName(String name);
}
