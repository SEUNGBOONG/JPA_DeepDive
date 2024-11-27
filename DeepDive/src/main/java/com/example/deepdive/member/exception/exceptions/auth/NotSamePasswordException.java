package com.example.deepdive.member.exception.exceptions.auth;

import com.example.deepdive.member.exception.exceptions.MemberErrorCode;
import com.example.deepdive.member.exception.exceptions.MemberException;

public class NotSamePasswordException extends MemberException {

    public NotSamePasswordException() {
        super(MemberErrorCode.NOT_SAME_PASSWORD);
    }
}
