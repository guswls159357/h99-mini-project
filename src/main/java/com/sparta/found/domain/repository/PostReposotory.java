package com.sparta.found.domain.repository;

import com.sparta.found.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostReposotory extends JpaRepository<Post,Integer> {

}
