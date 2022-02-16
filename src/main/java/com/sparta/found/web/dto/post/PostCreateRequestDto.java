package com.sparta.found.web.dto.post;

import com.sparta.found.domain.entity.Post;
import com.sparta.found.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostCreateRequestDto {

    @NotBlank(message = "제목을 입력해 주세요")
    private String postTitle;
    @NotBlank(message = "내용을 입력해 주세요")
    private String postContents;
    @NotBlank(message = "언어를 선택해 주세요")
    private String postLanguage;
    @Valid
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
