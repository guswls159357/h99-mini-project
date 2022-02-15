package com.sparta.found.domain.repository;

import com.sparta.found.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {

    @Query("select p from Post p join fetch p.user join fetch p.postTagList t join fetch t.tag where p.id = :postId")
    Optional<Post> findByIdFetchAll(@Param("postId") Integer postId);

    @Query("select distinct p from Post p join fetch p.user")
    List<Post> findAllFetchUser();
}
