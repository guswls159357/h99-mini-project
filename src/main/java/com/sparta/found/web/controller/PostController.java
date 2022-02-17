package com.sparta.found.web.controller;

import com.sparta.found.domain.entity.User;
import com.sparta.found.domain.service.PostService;
import com.sparta.found.web.dto.ResDto;
import com.sparta.found.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    @Secured(value = "ROLE_USER")
    public ResDto createPost(@RequestBody PostCreateRequestDto dto){


        Integer postId = postService.create(dto);

        return ResDto.builder()
                .result(true)
                .data(PostIdDto.builder().postId(postId).build())
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
    public ResDto getOne(@PathVariable("postId") String postsId){

        PostDto dto = postService.getOne(Integer.valueOf(postsId));

        return ResDto.builder()
                .result(true)
                .data(dto)
                .build();
    }

    @GetMapping("/posts/user/{userId}")
    @Secured("ROLE_USER")
    public ResDto getAllByUserId(@PathVariable("userId") Integer userId){

        PostListResponseDto postListResponseDto = postService.getAllByUserId(userId);

        return ResDto.builder()
                .result(true)
                .data(postListResponseDto)
                .build();
    }

    @PutMapping("/posts/{postId}")
    @Secured(value = "ROLE_USER")
    public ResDto update(@RequestBody PostUpdateRequestDto dto,
                         @PathVariable("postId") Integer postId){

        postService.update(dto,postId);
        return ResDto.builder()
                .result(true)
                .data(null)
                .build();
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/posts/{postId}")
    public ResDto delete(@PathVariable("postId") Integer postId){

        postService.delete(postId);

        return ResDto.builder()
                .result(true)
                .data(null)
                .build();
    }

    @Secured("ROLE_USER")
    @GetMapping("/posts/problem/{postId}")
    public ResDto checkProblemStatus(@PathVariable("postId") Integer postId){

        postService.changeProblemStatus(postId);

        return ResDto.builder()
                .result(true)
                .data(null)
                .build();
    }

}
