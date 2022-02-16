package com.sparta.found.domain.repository;

import com.sparta.found.domain.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Integer> {

    @Query("select cl from CommentLike cl where cl.user.id = :userId and cl.comment.id = :commentId")
    Optional<CommentLike> findByCommentIdAndUserId(Integer commentId, Integer userId);

    @Query("select cl from CommentLike cl join fetch cl.user where cl.comment.id = :commentId")
    List<CommentLike> findAllByCommentIdFetchUser(@Param("commentId") Integer commentId);

    @Modifying
    @Query("delete from CommentLike cl where cl.comment.id = :commentId")
    void deleteAllByCommentId(@Param("commentId") Integer commentId);
}
