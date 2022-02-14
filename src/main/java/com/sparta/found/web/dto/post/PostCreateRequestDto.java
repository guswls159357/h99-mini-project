package com.sparta.found.web.dto.post;

import com.sparta.found.domain.entity.Post;
import com.sparta.found.domain.entity.User;
import com.sparta.found.web.dto.tag.TagContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostCreateRequestDto {
    private String postTitle;
    private String postContents;
    private String postLanguage;
    private List<String> postTag;
    private Boolean postProblem;

    public Post toEntity(User user){

        return Post.builder()
                .title(this.postTitle)
                .contents(this.postContents)
                .language(this.postLanguage)
                .problem(this.postProblem)
                .user(user)
                .build();
    }
}
