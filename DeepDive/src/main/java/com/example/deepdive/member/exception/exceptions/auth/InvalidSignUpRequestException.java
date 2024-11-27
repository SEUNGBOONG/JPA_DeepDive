package com.example.deepdive.member.exception.exceptions.auth;

import com.example.deepdive.member.exception.exceptions.MemberErrorCode;
import com.example.deepdive.member.exception.exceptions.MemberException;

public class InvalidSignUpRequestException extends MemberException {
    public InvalidSignUpRequestException(){
        super(MemberErrorCode.INVALID_SIGNUP_REQUEST);
    }
}
