package com.sparta.found.web.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentIdDto {

    @NotNull(message = "commentId는 필수값 입니다")
    private Integer commentId;
}
