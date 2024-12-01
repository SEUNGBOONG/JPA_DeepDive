package com.example.deepdive.member.exception.exceptions;

public class NotContainSpecialChars extends RuntimeException {
    public NotContainSpecialChars() {
        super("특수문자를 꼭 포함하세요");
    }
}
