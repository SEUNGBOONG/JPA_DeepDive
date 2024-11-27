package com.example.deepdive.member.exception.exceptions.auth;

import com.example.deepdive.member.exception.exceptions.MemberErrorCode;
import com.example.deepdive.member.exception.exceptions.MemberException;

public class DuplicateEmailException extends MemberException {

    public DuplicateEmailException() {
        super(MemberErrorCode.DUPLICATED_EMAIL);
    }
}
