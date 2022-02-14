package com.sparta.found.domain.repository;


import com.sparta.found.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    boolean existsByContents(String contents);

    @Query("select t.contents from Tag t where t.contents in (:contents)")
    List<String> findAllByContents(@Param("contents") List<String> contents);
}
