package com.sparta.found.web.dto.post;

import com.sparta.found.web.dto.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDto {

    private UserInfo userInfo;
    private Integer postId;
    private String postTitle;
    private String postContents;
    private String postLanguage;
    private List<String> postTag;
    private boolean postProblem;
}
