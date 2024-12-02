package com.example.deepdive.post.controller.dto;

import com.example.deepdive.post.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostUserResponse {

    private String title;

    private Long memberId;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public PostUserResponse(Post post) {
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

}
