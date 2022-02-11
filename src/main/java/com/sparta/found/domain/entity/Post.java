package com.sparta.found.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

    private Integer id;

    @Column(name = "post_title", nullable = false, columnDefinition = "varchar(50)")
    private String title;

    @Column(name = "post_contents", nullable = false, columnDefinition = "varchar(3000)")
    private String contents;

    @Column(name = "post_language", nullable = false, columnDefinition = "varchar(15)")
    private String language;

    @Column(name = "post_problem",nullable= false)
    private Boolean problem;
}


