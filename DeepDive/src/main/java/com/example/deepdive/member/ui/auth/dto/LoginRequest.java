package com.example.deepdive.member.ui.auth.dto;

public record LoginRequest(
        String memberEmail,
        String memberPassword
) {
}
