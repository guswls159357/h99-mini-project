package com.sparta.found.domain.repository;


import com.sparta.found.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    boolean existsByContents(String contents);

    @Query("select t.contents from Tag t where t.contents in (:contents)")
    List<String> findAllContentsByContentsList(@Param("contents") List<String> contents);

    @Query("select t from Tag t where t.contents = :contents")
    Optional<Tag> findByContents(String contents);

    @Query("select t from Tag t where t.contents in (:contents)")
    List<Tag> findAllByContentsList(@Param("contents") List<String> contents);

    @Modifying
    @Query("delete from Tag t where t.postTagList.size = 0")
    void deleteAllByDeletedReference();


}
