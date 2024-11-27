package com.example.deepdive.member.exception.exceptions.auth;


import com.example.deepdive.member.exception.exceptions.MemberErrorCode;
import com.example.deepdive.member.exception.exceptions.MemberException;

public class InvalidLoginRequestException extends MemberException {
    public InvalidLoginRequestException() {
        super(MemberErrorCode.INVALID_LOGIN_REQUEST);
    }
}
