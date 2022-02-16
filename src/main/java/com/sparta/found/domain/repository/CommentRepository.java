package com.sparta.found.domain.repository;

import com.sparta.found.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query("select distinct c from Comment c join fetch c.user join fetch c.commentLikeList cl join fetch cl.user order by c.createdAt desc")
    List<Comment> findAllByPostIdFetchAll();
}
