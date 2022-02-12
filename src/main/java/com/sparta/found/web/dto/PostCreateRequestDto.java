package com.sparta.found.web.dto;

import com.sparta.found.domain.entity.Post;
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

    public Post toEntity(){
        return Post.builder()
                .title(this.postTitle)
                .contents(this.postContents)
                .language(this.postLanguage)
                .problem(this.postProblem)
                .build();
        //todo: 이후 태그에 대한 처리를 해주어야 함
    }
}
