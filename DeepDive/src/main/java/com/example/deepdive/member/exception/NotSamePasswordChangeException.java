package com.example.deepdive.member.exception;

public class NotSamePasswordChangeException extends IllegalArgumentException {
    public NotSamePasswordChangeException() {
        super("이전 비밀번호와 일치합니다.");
    }
}
