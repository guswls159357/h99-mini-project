package com.sparta.found.domain.repository;

import com.sparta.found.domain.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag,Integer> {
}
