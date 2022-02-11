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
@Table(name = "commentLike",
        indexes = {
                @Index(name = "idx_commentLike_created_at", columnList = "created_at"),
                @Index(name = "idx_commentLike_updated_at", columnList = "updated_at")
        })
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private Integer id;

    @Column(name="tag_contents",nullable = false, columnDefinition = "varchar(10)")
    private String contents;


}
