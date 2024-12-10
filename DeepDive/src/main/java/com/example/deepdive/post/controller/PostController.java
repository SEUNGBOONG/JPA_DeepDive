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

    @GetMapping("/user/{memberId}")
    public ResponseEntity<PostUserResponse> getUserPost(@Member Long memberId) {
        PostUserResponse userPosts = postUserService.findUserPost(memberId);
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

    //PostController는 REST API 엔드포인트를 정의하고 있으며, PostService와 PostUserService를 사용하여 게시물 관련 요청을 처리합니다.
    //
    //생성 (createPost): 게시물을 생성할 때, PostRequestDTO를 받아서 유효성을 검사하고, PostService에서 createPost 메서드를 호출하여 새로운 게시물을 저장합니다.
    //게시물 목록 조회 (getAllPosts): PostService의 findAllPost를 통해 모든 게시물을 가져와서 반환합니다.
    //사용자 게시물 조회 (getUserPosts, getUserPost): 두 개의 메서드가 정의되어 있으나, getUserPosts와 getUserPost는 같은 URL 경로(/user/{memberId})를 사용하고 있어 이 부분에서 충돌이 발생할 수 있습니다. 하나를 수정해야 합니다.
    //게시물 수정 (updatePost, updatePassword): 게시물의 제목과 내용을 수정하거나 비밀번호를 업데이트합니다.
    //게시물 삭제 (deletePost): PostUserService의 deleteBoard를 통해 게시물을 삭제합니다.
}
