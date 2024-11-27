package com.example.deepdive.member.ui.auth.dto;

public record LoginResponse(
        String token,
        String memberName,
        String memberNickName
) {
}
