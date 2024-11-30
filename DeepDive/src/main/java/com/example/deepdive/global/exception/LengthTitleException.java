package com.example.deepdive.global.exception;

public class LengthTitleException extends RuntimeException{
    public LengthTitleException() {
        super("제목은 10자 이하입니다.");
    }
}
