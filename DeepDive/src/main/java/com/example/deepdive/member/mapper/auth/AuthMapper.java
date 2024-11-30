package com.example.deepdive.member.mapper.auth;

import com.example.deepdive.member.domain.member.User;

import com.example.deepdive.member.ui.auth.dto.SignUpRequest;
import com.example.deepdive.member.ui.auth.dto.SignUpResponse;

public class AuthMapper {

    public static User toMember(SignUpRequest signUpRequest) {
        return new User(
                signUpRequest.memberEmail(),
                signUpRequest.memberName(),
                signUpRequest.memberPassword(),
                signUpRequest.memberNickName()
        );
    }
    public static SignUpResponse toSignUpResponse(User user) {
        return new SignUpResponse(user.getId(), user.getMemberName(), user.getMemberEmail(),
                user.getMemberPassword(), user.getMemberNickName());
    }
}
