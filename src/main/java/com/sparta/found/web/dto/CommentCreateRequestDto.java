package com.sparta.found.web.dto;

import com.sparta.found.domain.entity.Comment;
import com.sparta.found.domain.entity.Post;
import com.sparta.found.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentCreateRequestDto {
    private String commentContents;


    public Comment toEntity(User user, Post post){

        return Comment.builder()
                .post(post)
                .user(user)
                .contents(commentContents)
                .build();
    }
}
