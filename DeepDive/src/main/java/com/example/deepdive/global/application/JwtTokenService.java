package com.example.deepdive.global.application;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.deepdive.global.exception.jwt.NotFoundTokenException;
import com.example.deepdive.global.exception.jwt.TokenTimeException;
import com.example.deepdive.member.infrastructure.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    // JWT 토큰과  관련된 다양한 작업을 처리
    // 유효성검사
    // 사용자 ID 추출
    // 존재 여부 및 만료 여부 확인
    // 예외 처리

    //이 클래스가 핵심 로직(토큰 검증, 해독)을 처리하며, JwtTokenService는 이를 호출합니다.
    private final JwtTokenProvider jwtTokenProvider;

    public DecodedJWT verifyJwtToken(String token) {
        return jwtTokenProvider.verifyToken(token);
    } // DecodeJwt객체 토큰에 포함된 클레임 정보를 포함시킴  검증 로직은 Provider에게 위임한다.


    public Long verifyAndExtractJwtToken(String token) {
        try {
            return validateTokenExist(token);
        } catch (TokenExpiredException e) {
            throw new TokenTimeException();
        }
    } // memberID 만료가 되면 토큰타임익셉션을 던짐

    private Long validateTokenExist(String token) {
        return Optional.of(extractJwtToken(token))
                .orElseThrow(NotFoundTokenException::new);
    }// 토큰이 유효하지 않거나 정보를 추출할 수 없을 경우 던진다 예외

    private Long extractJwtToken(String token) {
        return verifyJwtToken(token).getClaim("memberId")
                .asLong();
    }

    //JWT의 클레임에서 memberId를 추출
}
