package com.sparta.found.domain.entity;

import com.sparta.found.web.dto.comment.CommentResponseDto;
import com.sparta.found.web.dto.user.UserInfo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "comment", indexes = {@Index(name = "idx_comment_created_at", columnList = "created_at"),
        @Index(name = "idx_comment_updated_at", columnList = "updated_at")})
public class Comment extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comment_contents", nullable = false, columnDefinition = "varchar(5000)")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment", orphanRemoval = true)
    private List<CommentLike> commentLikeList = new ArrayList<>();

    private void setUser(User user) {
        this.user = user;
        user.getCommentList().add(this);
    }

    private void setPost(Post post) {
        this.post = post;
        post.getCommentList().add(this);
    }

    @Builder
    public Comment(String contents, User user, Post post) {
        this.contents = contents;
        setUser(user);
        setPost(post);
    }

    public CommentResponseDto toCommentResponseDto(UserInfo writeUserInfo, List<UserInfo> likeUserInfoList) {

        return CommentResponseDto.builder()
                .commentId(this.id)
                .commentContent(this.contents)
                .userInfo(writeUserInfo)
                .commentLikes(likeUserInfoList)
                .build();
    }

}
