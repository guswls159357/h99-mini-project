package com.sparta.found.domain.entity;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "tag_contents", nullable = false, columnDefinition = "varchar(10)")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "post_id")
    private Post post;

    private void setPost(Post post){
        this.post = post;
        post.getTagList().add(this);
    }

    @Builder
    public Tag(String contents, Post post) {
        this.contents = contents;
        setPost(post);
    }
}
