package com.example.deepdive.member.exception.exceptions;

public class NotCheckException extends IllegalArgumentException {
    public NotCheckException() {
        super("아이디로 조회를 실패했습니다.");
    }
}
