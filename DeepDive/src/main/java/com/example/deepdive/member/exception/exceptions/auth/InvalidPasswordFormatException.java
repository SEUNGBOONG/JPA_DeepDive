package com.example.deepdive.member.exception.exceptions.auth;

import com.example.deepdive.member.exception.exceptions.MemberErrorCode;
import com.example.deepdive.member.exception.exceptions.MemberException;

public class InvalidPasswordFormatException extends MemberException {
    public InvalidPasswordFormatException(){
        super(MemberErrorCode.INVALID_PASSWORD);
    }
}
