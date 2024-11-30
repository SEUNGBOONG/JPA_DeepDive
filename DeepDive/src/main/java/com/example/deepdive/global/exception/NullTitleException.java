package com.example.deepdive.global.exception;

public class NullTitleException extends RuntimeException {

    public NullTitleException() {
        super("제목을 입력하세요");
    }
}
