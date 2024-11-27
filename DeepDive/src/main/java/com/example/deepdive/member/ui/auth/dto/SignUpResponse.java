package com.example.deepdive.member.ui.auth.dto;

public record SignUpResponse(
        Long id,
        String memberName,
        String memberEmail,
        String memberPassword,
        String memberNickname
) {
}
