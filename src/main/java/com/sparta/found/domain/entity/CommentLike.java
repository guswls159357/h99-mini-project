package com.sparta.found.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "commentLike",
        indexes = {
                @Index(name = "idx_commentLike_created_at", columnList = "created_at"),
                @Index(name = "idx_commentLike_updated_at", columnList = "updated_at")
        })
public class CommentLike extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private void setComment(Comment comment){
        this.comment = comment;
        comment.getCommentLikeList().add(this);
    }

    private void setUser(User user){
        this.user = user;
        user.getCommentLikeList().add(this);
    }

    @Builder
    public CommentLike(User user, Comment comment) {
        setUser(user);
        setComment(comment);
    }
}
