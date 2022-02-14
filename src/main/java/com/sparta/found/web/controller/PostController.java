package com.sparta.found.web.controller;

import com.sparta.found.domain.entity.User;
import com.sparta.found.domain.service.PostService;
import com.sparta.found.web.dto.ResDto;
import com.sparta.found.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResDto createPost(@RequestBody PostCreateRequestDto dto,
                             @AuthenticationPrincipal User user){


        postService.create(user,dto);

        return ResDto.builder()
                .result(true)
                .data(null)
                .build();
    }

    @GetMapping("/posts")
    public ResDto getAll(){

        PostListResponseDto postListResponseDto = postService.getAll();

        return ResDto.builder()
                .result(true)
                .data(postListResponseDto)
                .build();
    }

    @GetMapping("/posts/{postId}")
    public ResDto getOne(@PathVariable("postId") Integer postsId){

        PostDto dto = postService.getOne(Integer.valueOf(postsId));

        return ResDto.builder()
                .result(true)
                .data(dto)
                .build();
    }

    @PutMapping("/posts/{postId}")
    public ResDto update(@RequestBody PostUpdateRequestDto dto,
                         @PathVariable("postId") Integer postId,
                         @AuthenticationPrincipal User user){

        postService.update(dto,user,postId);
        return null;
    }
}
