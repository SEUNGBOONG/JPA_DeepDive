package com.example.deepdive.member.application.auth;

import com.example.deepdive.member.domain.member.Member;
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

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Transactional
    public Member signUp(SignUpRequest signUpRequest) {
        validateSignupRequestFormat(signUpRequest);
        validateEmailFormat(signUpRequest.memberEmail());
        checkPasswordLength(signUpRequest.memberPassword());
        Member member = AuthMapper.toMember(signUpRequest);
        checkDuplicateMemberNickName(member.getMemberNickName());
        checkDuplicateMemberEmail(member.getMemberEmail());

        return memberRepository.save(member);
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
        if (password.length() <= EXTRACT_PASSWORD_NUMBER {
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

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginRequest) {
        validateLoginRequestFormat(loginRequest);
        Member member = findMemberByEmail(loginRequest.memberEmail());
        member.checkPassword(loginRequest.memberPassword());
        String token = jwtTokenProvider.createToken(member.getId());
        return new LoginResponse(token, member.getMemberName(), member.getMemberNickName());
    }

    private void validateLoginRequestFormat(LoginRequest loginRequest) {
        if (loginRequest == null ||
                isEmpty(loginRequest.memberEmail()) ||
                isEmpty(loginRequest.memberPassword())) {
            throw new RuntimeException();
        }
    }

    private Member findMemberByEmail(String email) {
        return memberRepository.findMemberByMemberEmail(email)
                .orElseThrow(NotFoundMemberByEmailException::new);
    }
}
