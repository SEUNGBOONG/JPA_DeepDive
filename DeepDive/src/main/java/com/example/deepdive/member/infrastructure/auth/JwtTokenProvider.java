package com.example.deepdive.member.infrastructure.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.deepdive.member.domain.auth.Token;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider implements Token {

    private final Algorithm algorithm;
    private final long expirationPeriod;

    public JwtTokenProvider(final Algorithm algorithm, final long expirationPeriod) {
        this.algorithm = algorithm;
        this.expirationPeriod = expirationPeriod;
    }

    @Override
    public String createToken(final Long memberId) {
        return JWT.create()
                .withClaim("memberId", memberId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationPeriod * 1000L))
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    @Override
    public DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        return verifier.verify(token);
    }
}
