package com.example.deepdive.post.service;

import com.example.deepdive.global.exception.LengthTitleException;
import com.example.deepdive.global.exception.NullTitleException;

import com.example.deepdive.post.controller.dto.PostResponse;

import org.springframework.stereotype.Service;

@Service
public class PostService {

    public PostResponse createPost(String title, String post) {
        validateTitle(title);
        return new PostResponse(title, post);
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
