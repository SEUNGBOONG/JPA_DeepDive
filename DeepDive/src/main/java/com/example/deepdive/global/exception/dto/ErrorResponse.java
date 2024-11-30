package com.example.deepdive.global.exception.dto;

public record ErrorResponse(
        String customCode,
        String message
) {
}
