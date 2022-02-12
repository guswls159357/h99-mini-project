package com.sparta.found.web.dto;

import com.sparta.found.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostUpdateRequestDto {
    private String postTitle;
    private String postContents;
    private String postLanguage;
    private List<String> postTag;

}
