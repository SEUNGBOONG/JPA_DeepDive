package com.example.deepdive.post.domain.repository;

import com.example.deepdive.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
