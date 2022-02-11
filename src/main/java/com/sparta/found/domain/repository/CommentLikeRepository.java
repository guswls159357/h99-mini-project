package com.sparta.found.domain.repository;

import com.sparta.found.domain.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Integer> {
}
