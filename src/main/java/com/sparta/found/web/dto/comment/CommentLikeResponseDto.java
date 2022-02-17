package com.sparta.found.web.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentLikeResponseDto {

    private boolean state;
}
