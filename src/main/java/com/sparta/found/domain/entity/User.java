package com.sparta.found.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
public class User extends TimeEntity{


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
            columnDefinition = "varchar(50)")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CommentLike> commentLikeList = new ArrayList<>();

}
