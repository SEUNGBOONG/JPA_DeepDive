package com.example.deepdive.post.service;

import com.example.deepdive.global.exception.LengthTitleException;
import com.example.deepdive.global.exception.NullTitleException;

import com.example.deepdive.post.controller.dto.PostListResponse;
import com.example.deepdive.post.controller.dto.PostRequestDTO;

import com.example.deepdive.post.domain.entity.Post;
import com.example.deepdive.post.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostListResponse createPost(Long memberId, PostRequestDTO postRequestDTO) {
        validateTitle(postRequestDTO.getTitle());
        Post post = new Post(memberId, postRequestDTO.getTitle(), postRequestDTO.getContent(), postRequestDTO.getPassword());
        postRepository.save(post);
        return new PostListResponse(post);
    }

    public List<PostListResponse> findAllPost() {
        List<Post> boardList = postRepository.findAll();
        List<PostListResponse> responseDtoList = new ArrayList<>();
        for (Post post : boardList) {
            responseDtoList.add(
                    new PostListResponse(post)
            );
        }
        return responseDtoList;
    }

    private void validateTitle(final String title) {
        if (title.isEmpty()) {
            throw new NullTitleException();
        }
        if (title.length() > 10) {
            throw new LengthTitleException();
        }
    }

}

//PostService는 게시물의 생성, 조회 및 유효성 검사를
//
//게시물 생성 시 제목의 길이를 체크하고, 제목이 비어 있는지 확인합니다.
//validateTitle 메서드에서 제목 길이와 비어있는 제목에 대한 예외 처리를 하고 있습니다.


// 개선해야 한다고 생각하는 점: -> 유효성 검사는 클라이언트에서 먼저 처리하도록 유도하는 것이 좋아보인다!
