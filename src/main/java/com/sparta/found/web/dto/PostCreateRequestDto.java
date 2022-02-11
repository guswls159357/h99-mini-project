package com.sparta.found.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCreateRequestDto {
    private String postTitle;
    private String postContents;
    private String postLanguage;
    private List<String> postTag;
    private Boolean postProblem;
}
