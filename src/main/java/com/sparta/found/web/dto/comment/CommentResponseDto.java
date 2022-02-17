package com.sparta.found.web.dto.comment;

import com.sparta.found.web.dto.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private UserInfo userInfo;
    private Integer commentId;
    private String commentContent;
    private List<String> commentLikesUsername;
    private Integer commentLike;
}
