package com.sparta.found.domain.entity;

import com.sparta.found.web.dto.user.UserInfo;
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

    @Column(name="user_role", columnDefinition = "varchar(10)")
    private String role = "ROLE_USER";

    @Column(name="user_profile_image_url")
    private String profileImageUrl = "https://mini-project.s3.ap-northeast-2.amazonaws.com/static/fc11d9b3-7a4d-469e-8671-a037cb3979acIMG_5663.jpeg";


    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public UserInfo toUserInfo(){
        return UserInfo.builder()
                .userId(this.id)
                .username(this.username)
                .profileImageUrl(this.profileImageUrl)
                .build();
    }

    public void changeProfileImageUrl(String profileImageUrl){

        this.profileImageUrl = profileImageUrl;
    }
}
