package com.example.deepdive.member.infrastructure.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;

import com.example.deepdive.member.domain.auth.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements Token {
    private final Algorithm algorithm;
    private final long expirationPeriod;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration-period}") long expirationPeriod) {
        this.algorithm = Algorithm.HMAC256(secretKey);
        this.expirationPeriod = expirationPeriod;
    }

    @Override
    public String createToken(final Long memberId) {
        return JWT.create()
                .withClaim("memberId", memberId) // 토큰에 memberId 저장
                .withIssuedAt(new Date()) // 생성 시간
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationPeriod * 1000L)) // 만료 시간
                .withJWTId(UUID.randomUUID().toString()) // 고유 ID
                .sign(algorithm);
    }

    @Override
    public DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token); // 토큰 검증
    }
}
