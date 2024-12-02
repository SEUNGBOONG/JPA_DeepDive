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
public class PostListResponse {

    private String title;

    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public PostListResponse(Post post) {
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public PostListResponse(Optional<Post> post) {
        this.title = post.get().getTitle();
        this.createdAt = post.get().getModifiedAt();
        this.modifiedAt = post.get().getCreatedAt();
    }
}
