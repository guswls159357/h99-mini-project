package com.sparta.found.domain.entity;

import com.sparta.found.web.dto.UserInfo;
import com.sparta.found.web.dto.post.PostDto;
import com.sparta.found.web.dto.post.PostResponseDto;
import com.sparta.found.web.dto.post.PostUpdateRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post",
        indexes = {@Index(name = "idx_post_created_at", columnList = "created_at"),
                @Index(name = "idx_post_updated_at", columnList = "updated_at")
        })

public class Post extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;

    @Column(name = "post_title", nullable = false, columnDefinition = "varchar(50)")
    private String title;

    @Column(name = "post_contents", nullable = false, columnDefinition = "varchar(3000)")
    private String contents;

    @Column(name = "post_language", nullable = false, columnDefinition = "varchar(15)")
    private String language;

    @Column(name = "post_problem", nullable = false)
    private Boolean problem;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostTag> postTagList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(String title, String contents, String language, Boolean problem, User user) {
        this.title = title;
        this.contents = contents;
        this.language = language;
        this.problem = problem;
        setUser(user);
    }

    private void setUser(User user) {
        this.user = user;
        user.getPostList().add(this);
    }

    public PostResponseDto toPostResponseDto(UserInfo userInfo, List<String> postTag) {

        PostDto postDto = toPostDto(userInfo, postTag);
        return PostResponseDto.builder().post(postDto).build();
    }

    public PostDto toPostDto(UserInfo userInfo, List<String> postTag) {
        return PostDto.builder()
                .postId(this.id)
                .postContents(this.contents)
                .postLanguage(this.language)
                .postProblem(this.problem)
                .postTitle(this.title)
                .postTag(postTag)
                .userInfo(userInfo)
                .build();
    }

    public void update(PostUpdateRequestDto dto){

        this.title = dto.getPostTitle();
        this.contents = dto.getPostContents();
        this.language = dto.getPostLanguage();
        this.problem = dto.getPostProblem();
    }

}


