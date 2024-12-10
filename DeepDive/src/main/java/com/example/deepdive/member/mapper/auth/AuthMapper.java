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
    //변환 수행
    public static SignUpResponse toSignUpResponse(User user) {
        return new SignUpResponse(user.getId(), user.getMemberName(), user.getMemberEmail(),
                user.getMemberPassword(), user.getMemberNickName());
    }
}
//JWT 토큰 생성 및 검증 (JwtTokenProvider):
//
//사용자가 로그인하면 createToken을 호출하여 사용자 정보를 담은 토큰을 생성.
//인증이 필요한 요청이 오면 verifyToken을 통해 토큰 유효성을 검증.
//회원가입 및 응답 변환 (AuthMapper):
//
//회원가입 요청이 오면 DTO를 User 엔티티로 변환 후 데이터베이스에 저장.
//저장된 정보를 응답 DTO로 변환하여 클라이언트에 반환.
//예외 처리 (MemberExceptionHandler):
//
//애플리케이션 전역에서 발생하는 예외를 포착하고, 일관된 형식으로 응답을 제공.
