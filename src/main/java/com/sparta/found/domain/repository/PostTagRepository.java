package com.sparta.found.domain.repository;

import com.sparta.found.domain.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTag,Integer> {

    @Query("select pt from PostTag pt join fetch pt.tag where pt.post.id = :postId")
    List<PostTag> findByPostId(@Param("postId") Integer postId);


    @Modifying
    @Query("delete from PostTag pt where pt.post.id = :postId")
    void deleteAllByPostId(Integer postId);
}
