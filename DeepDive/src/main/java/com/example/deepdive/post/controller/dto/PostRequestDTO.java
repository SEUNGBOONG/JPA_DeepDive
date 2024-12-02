package com.example.deepdive.post.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostRequestDTO {

    private String title;

    private String content;

    private String password;
}
