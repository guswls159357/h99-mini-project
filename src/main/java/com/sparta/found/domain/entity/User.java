package com.sparta.found.domain.entity;

import com.sparta.found.web.dto.UserInfo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user",
        indexes = {
                @Index(name = "idx_user_created_at", columnList = "created_at"),
                @Index(name = "idx_user_updated_at", columnList = "updated_at")
        })
public class User extends TimeEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_username",
            nullable = false,
            unique = true,
            columnDefinition = "varchar(30)")
    private String username; //이메일 형식

    @Column(name = "user_password",
            nullable = false,
            columnDefinition = "varchar(200)")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CommentLike> commentLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Post> postList = new ArrayList<>();

    @Column(name="user_role")
    private String role = "ROLE_USER";

    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserInfo toUserInfo(){
        return UserInfo.builder()
                .userId(this.id)
                .username(this.username)
                .build();
    }
}
