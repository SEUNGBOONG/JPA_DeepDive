package com.example.deepdive.post.controller;

import com.example.deepdive.global.annotaion.Member;

import com.example.deepdive.post.controller.dto.PostListResponse;
import com.example.deepdive.post.controller.dto.PostRequestDTO;
import com.example.deepdive.post.controller.dto.PostUserResponse;

import com.example.deepdive.post.service.PostService;
import com.example.deepdive.post.service.PostUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final PostUserService postUserService;


    @PostMapping("/create")
    public ResponseEntity<PostListResponse> createPost(@Member Long memberId, @RequestBody PostRequestDTO postRequestDTO) {
        PostListResponse response = postService.createPost(memberId, postRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostListResponse>> getAllPosts() {
        List<PostListResponse> posts = postService.findAllPost();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user/{memberId}")
    public ResponseEntity<List<PostUserResponse>> getUserPosts(@Member Long memberId) {
        List<PostUserResponse> userPosts = postUserService.findUserAllPost(memberId);
        return ResponseEntity.ok(userPosts);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Long> updatePost(@Member Long memberId, @PathVariable Long id, @RequestBody PostRequestDTO postRequestDTO) {
        Long updatedPostId = postUserService.updateBoard(memberId, id, postRequestDTO);
        return ResponseEntity.ok(updatedPostId);
    }

    @PutMapping("/update-password/{id}")
    public ResponseEntity<Long> updatePassword(@Member Long memberId, @PathVariable Long id, @RequestBody PostRequestDTO postRequestDTO) {
        Long updatedPostId = postUserService.updatePassword(memberId, id, postRequestDTO);
        return ResponseEntity.ok(updatedPostId);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deletePost(@Member Long memberId, @PathVariable Long id) {
        Long deletedPostId = postUserService.deleteBoard(memberId, id);
        return ResponseEntity.ok(deletedPostId);
    }
}
