package com.sparta.found.domain.repository;

import com.sparta.found.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {

    @Query("select p from Post p join fetch p.postTagList")
    Optional<Post> findByIdFetchPostTag();


}
