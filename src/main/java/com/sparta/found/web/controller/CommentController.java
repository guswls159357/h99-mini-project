package com.sparta.found.web.controller;

import com.sparta.found.domain.service.CommentService;
import com.sparta.found.web.dto.ResDto;
import com.sparta.found.web.dto.comment.CommentCreateRequestDto;
import com.sparta.found.web.dto.comment.CommentIdDto;
import com.sparta.found.web.dto.comment.CommentListResponseDto;
import com.sparta.found.web.dto.post.PostCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Secured("ROLE_USER")
    @PostMapping("/{postId}")
    public ResDto create(@PathVariable("postId") Integer postId,
                         @RequestBody CommentCreateRequestDto dto){

        CommentIdDto commentIdDto = commentService.create(postId, dto);

        return ResDto.builder()
                .result(true)
                .data(commentIdDto)
                .build();
    }

    @GetMapping("/{postId}")
    public ResDto getAll(@PathVariable("postId") Integer postId){

        CommentListResponseDto commentListResponseDto = commentService.getAllByPostId(postId);

        return ResDto.builder()
                .result(true)
                .data(commentListResponseDto)
                .build();
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/{commentId}")
    public ResDto delete(@PathVariable("commentId") Integer commentId){

        commentService.delete(commentId);

        return ResDto.builder()
                .result(true)
                .data(null)
                .build();
    }

    @Secured("ROLE_USER")
    @GetMapping("/like/{commentId}")
    public ResDto commentLike(@PathVariable("commentId") Integer commentId){

        commentService.commentLike(commentId);

        return ResDto.builder()
                .result(true)
                .data(null)
                .build();
    }
}
