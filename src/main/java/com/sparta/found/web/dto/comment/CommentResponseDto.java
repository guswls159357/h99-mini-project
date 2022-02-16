package com.sparta.found.web.dto.comment;

import com.sparta.found.web.dto.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private UserInfo userInfo;
    private Integer commentId;
    private String commentContent;
    private List<UserInfo> commentLikes;

}
