package com.sparta.found.web.dto.post;

import com.sparta.found.web.dto.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostListDto {

    private UserInfoDto userInfoDto;

    private Integer postId;

    private String postTitle;

    private String postContents;

    private String postLanguage;

    private String postProblem;
}
