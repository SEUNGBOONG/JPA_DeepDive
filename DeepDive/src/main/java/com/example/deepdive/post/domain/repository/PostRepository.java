package com.example.deepdive.post.domain.repository;

import com.example.deepdive.post.controller.dto.PostListResponse;
import com.example.deepdive.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<PostListResponse> findAllByOrderByModifiedAtDesc();

    List<PostListResponse> findAllByCommentsAndId(Long memberId);

    Post findByIdAAndMemberId(Long id, Long memberId);

    void deleteByIdAndMemberId(Long memberId,Long id);

    Post findAllByMemberId(Long memberId);
}
