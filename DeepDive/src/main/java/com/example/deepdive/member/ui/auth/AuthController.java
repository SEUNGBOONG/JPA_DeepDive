package com.example.deepdive.member.ui.auth;

import com.example.deepdive.member.application.auth.AuthService;
import com.example.deepdive.member.mapper.auth.AuthMapper;

import com.example.deepdive.member.ui.auth.dto.LoginRequest;
import com.example.deepdive.member.ui.auth.dto.LoginResponse;
import com.example.deepdive.member.ui.auth.dto.SignUpRequest;
import com.example.deepdive.member.ui.auth.dto.SignUpResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/members")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = AuthMapper.toSignUpResponse(authService.signUp(signUpRequest));
        URI location = URI.create("/members/" + signUpResponse.id());
        log.info("유저 생성 - {}번 유저 : {}", signUpResponse.id(), signUpResponse.memberNickname());
        return ResponseEntity.created(location).body(signUpResponse);
    }
    // 서비스 계층의 authService.signUP 메서드를 호출하여 회원가입 처리
    //생성된 사용자 정보를 SignUpResponse로 반환
    //Http 201 함께 사용자 리소스의 URI포함

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        log.info("로그인 성공");
        return ResponseEntity.ok(loginResponse);
    }
    //LoginRequest DTO로 요청 데이터를 받아 처리.
    //authService.login() 호출하여 인증 수행.
    //성공 시 LoginResponse 반환.
    //HTTP 응답 코드 200 OK.
}
