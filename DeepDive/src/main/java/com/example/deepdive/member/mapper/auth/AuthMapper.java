package com.example.deepdive.member.mapper.auth;

import com.example.deepdive.member.domain.member.Member;

import com.example.deepdive.member.ui.auth.dto.SignUpRequest;
import com.example.deepdive.member.ui.auth.dto.SignUpResponse;

public class AuthMapper {

    public static Member toMember(SignUpRequest signUpRequest) {
        return new Member(
                signUpRequest.memberEmail(),
                signUpRequest.memberName(),
                signUpRequest.memberPassword(),
                signUpRequest.memberNickName()
        );
    }
    public static SignUpResponse toSignUpResponse(Member member) {
        return new SignUpResponse(member.getId(), member.getMemberName(), member.getMemberEmail(),
                member.getMemberPassword(), member.getMemberNickName());
    }
}
