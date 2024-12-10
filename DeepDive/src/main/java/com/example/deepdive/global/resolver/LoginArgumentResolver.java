package com.example.deepdive.global.resolver;


import com.example.deepdive.global.annotaion.Member;
import com.example.deepdive.global.application.JwtTokenService;
import com.example.deepdive.global.exception.jwt.NotFoundTokenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String TOKEN_HEADER_NAME = "Authorization";
// JWT 토큰을 찾기위한 이름 설정
    private final JwtTokenService jwtTokenService;
    // 서비스 소환

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginAnnotation = parameter.hasParameterAnnotation(Member.class);
        boolean isMemberIdType = Long.class.isAssignableFrom(parameter.getParameterType());
        return isLoginAnnotation && isMemberIdType;
    }

    //파라미터가 처리 대상인지 검증하는 메서드
    //조건이 만족되면  밑에 메서드 실행
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String tokenHeader = Optional.ofNullable(request.getHeader(TOKEN_HEADER_NAME))
                .orElseThrow(NotFoundTokenException::new);
        String token = tokenHeader.substring(7);
        return jwtTokenService.verifyAndExtractJwtToken(token);
    }
    //Authorization 헤더에서 JWT 토큰 값을 읽어옵니다.
    //헤더가 없는 경우, NotFoundTokenException 예외를 던집니다.
    //헤더 값에서 Bearer 부분을 제거(substring(7)).
    //JWT 토큰을 검증하고 memberId를 추출.
    //검증 및 추출은 JwtTokenService에 위임.
    //반환된 memberId가 컨트롤러 메서드 파라미터에 주입됩니다.
}
