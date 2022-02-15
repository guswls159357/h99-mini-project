package com.sparta.found.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post_tag",
        indexes = {@Index(name = "idx_post_tag_created_at", columnList = "created_at"),
                @Index(name = "idx_post_tag_updated_at", columnList = "updated_at")
        })
public class PostTag extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_tag_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public PostTag(Post post, Tag tag) {
        setTag(tag);
        setPost(post);
    }

    private void setPost(Post post){
        this.post = post;
        post.getPostTagList().add(this);
    }

    private void setTag(Tag tag){
        this.tag = tag;
        tag.getPostTagList().add(this);
    }
}
