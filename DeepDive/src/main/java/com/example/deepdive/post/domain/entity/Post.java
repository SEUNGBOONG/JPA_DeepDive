package com.example.deepdive.post.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Post {

    @Id
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column
    private String title;

    @Column
    private String comments;

    public Post(final Long memberId, final String title, final String comments) {
        this.memberId = memberId;
        this.title = title;
        this.comments = validateComment(comments);
    }

    public Post() {

    }

    private String validateComment(final String comments) {
        if (comments.contains("시")){
            throw new RuntimeException("욕설이 들어가 있습니다.");
        }
        return comments;
    }
}
