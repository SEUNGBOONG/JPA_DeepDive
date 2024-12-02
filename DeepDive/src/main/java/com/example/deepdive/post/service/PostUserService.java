package com.example.deepdive.post.service;

import com.example.deepdive.member.exception.exceptions.NotCheckException;

import com.example.deepdive.post.controller.dto.PostRequestDTO;
import com.example.deepdive.post.controller.dto.PostUserResponse;

import com.example.deepdive.post.domain.entity.Post;
import com.example.deepdive.post.domain.repository.PostRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostUserService {

    private final PostRepository postRepository;

    public PostUserService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostUserResponse findUserPost(Long memberId) {
        Post post = postRepository
                .findById(memberId)
                .orElseThrow(NotCheckException::new);

        return new PostUserResponse(post.getTitle(), memberId, post.getCreatedAt(), post.getModifiedAt());
    }

    public List<PostUserResponse> findUserAllPost(Long memberId) {
        List<Post> boardList = postRepository.findById(memberId).stream().toList();
        List<PostUserResponse> responseDtoList = new ArrayList<>();

        for (Post post : boardList) {
            responseDtoList.add(
                    new PostUserResponse(post)
            );
        }
        return responseDtoList;
    }

    @Transactional
    public Long updateBoard(Long memberId, Long id, PostRequestDTO postRequestDTO) {
        Post post = postRepository
                .findByIdAAndMemberId(memberId, id);
        post.updateTitleOrComments(postRequestDTO);
        return post.getId();
    }

    @Transactional
    public Long updatePassword(Long memberId, Long id, PostRequestDTO postRequestDTO) {
        Post post = postRepository.findByIdAAndMemberId(memberId, id);
        post.updatePassword(postRequestDTO.getPassword());
        return post.getId();
    }

    @Transactional
    public Long deleteBoard(Long memberId,Long id) {
        postRepository.deleteByIdAndMemberId(memberId,id);
        return id;
    }
}
