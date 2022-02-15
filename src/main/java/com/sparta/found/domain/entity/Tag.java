package com.sparta.found.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tag",
        indexes = {
                @Index(name = "idx_tag_created_at", columnList = "created_at"),
                @Index(name = "idx_tag_updated_at", columnList = "updated_at")
        })
public class Tag extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer id;

    @Column(name = "tag_contents",unique = true, nullable = false, columnDefinition = "varchar(100)")
    private String contents;

    @OneToMany(mappedBy = "tag")
    private List<PostTag> postTagList = new ArrayList<>();

    @Builder
    public Tag(String contents) {
        this.contents = contents;
    }
}
