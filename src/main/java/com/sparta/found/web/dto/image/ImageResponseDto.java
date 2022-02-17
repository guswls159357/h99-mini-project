package com.sparta.found.web.dto.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ImageResponseDto {

    private String profileImageUrl;
}
