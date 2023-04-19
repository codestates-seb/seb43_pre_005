package com.potatos.stackoverflow.tags.repository;

import com.potatos.stackoverflow.tags.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository extends JpaRepository<Tag, Long> {
}
