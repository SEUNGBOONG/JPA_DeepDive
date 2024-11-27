package com.example.deepdive.member.exception.exceptions.auth;

import com.example.deepdive.member.exception.exceptions.MemberErrorCode;
import com.example.deepdive.member.exception.exceptions.MemberException;

public class NotFoundMemberByEmailException extends MemberException {

    public NotFoundMemberByEmailException() {
        super(MemberErrorCode.NOT_FOUND_MEMBER_BY_EMAIL);
    }
}
