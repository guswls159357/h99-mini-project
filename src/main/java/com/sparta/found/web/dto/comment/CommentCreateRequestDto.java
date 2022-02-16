package com.sparta.found.web.dto.comment;

import com.sparta.found.domain.entity.Comment;
import com.sparta.found.domain.entity.Post;
import com.sparta.found.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentCreateRequestDto {

    @NotBlank(message = "댓글 내용을 입력해 주세요")
    private String commentContents;


}
