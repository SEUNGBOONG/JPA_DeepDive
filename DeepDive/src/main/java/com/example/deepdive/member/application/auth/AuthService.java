package com.example.deepdive.member.application.auth;

import com.example.deepdive.member.domain.member.User;
import com.example.deepdive.member.domain.member.MemberRepository;

import com.example.deepdive.member.exception.exceptions.auth.InvalidSignUpRequestException;
import com.example.deepdive.member.exception.exceptions.auth.NotFoundMemberByEmailException;
import com.example.deepdive.member.infrastructure.auth.JwtTokenProvider;
import com.example.deepdive.member.mapper.auth.AuthMapper;
import com.example.deepdive.member.ui.auth.dto.LoginRequest;
import com.example.deepdive.member.ui.auth.dto.LoginResponse;
import com.example.deepdive.member.ui.auth.dto.SignUpRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final int EXTRACT_PASSWORD_NUMBER = 7;
    // 비밀번호 7글자 제한하는 상수화

    private final MemberRepository memberRepository;
    // JPA 저장소

    private final JwtTokenProvider jwtTokenProvider;
    //TokenProvider

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Transactional
    public User signUp(SignUpRequest signUpRequest) {
        validateSignupRequestFormat(signUpRequest);
        validateEmailFormat(signUpRequest.memberEmail());
        checkPasswordLength(signUpRequest.memberPassword());
        User user = AuthMapper.toMember(signUpRequest);
        checkDuplicateMemberNickName(user.getMemberNickName());
        checkDuplicateMemberEmail(user.getMemberEmail());

        return memberRepository.save(user);
    }
    //다양한 검증을 통한 회원가입
    // 조금 더 객체지향적으로 할 수 있을지에 대한 고민을 해봐야겠다.

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginRequest) {
        validateLoginRequestFormat(loginRequest);
        User user = findMemberByEmail(loginRequest.memberEmail());
        user.checkPassword(loginRequest.memberPassword());
        String token = jwtTokenProvider.createToken(user.getId());
        return new LoginResponse(token, user.getMemberName(), user.getMemberNickName());
    }

    private void validateSignupRequestFormat(SignUpRequest signUpRequest) {
        if (signUpRequest == null ||
                isEmpty(signUpRequest.memberEmail()) ||
                isEmpty(signUpRequest.memberName()) ||
                isEmpty(signUpRequest.memberPassword()) ||
                isEmpty(signUpRequest.memberNickName())) {
            throw new InvalidSignUpRequestException();
        }
    }

    private void checkPasswordLength(String password) {
        if (password.length() <= EXTRACT_PASSWORD_NUMBER) {
            throw new RuntimeException();
        }
    }

    private void validateEmailFormat(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new RuntimeException();
        }
    }

    private void checkDuplicateMemberNickName(String nickName) {
        if (memberRepository.existsByMemberNickName(nickName)) {
            throw new RuntimeException();
        }
    }

    private void checkDuplicateMemberEmail(String email) {
        if (memberRepository.existsByMemberEmail(email)) {
            throw new RuntimeException();
        }
    }

    private void validateLoginRequestFormat(LoginRequest loginRequest) {
        if (loginRequest == null ||
                isEmpty(loginRequest.memberEmail()) ||
                isEmpty(loginRequest.memberPassword())) {
            throw new RuntimeException();
        }
    }

    private User findMemberByEmail(String email) {
        return memberRepository.findMemberByMemberEmail(email)
                .orElseThrow(NotFoundMemberByEmailException::new);
    }
}
