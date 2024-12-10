package com.example.deepdive.member.exception.exceptionhandler;

import com.example.deepdive.member.exception.exceptionhandler.dto.MemberErrorResponse;

import com.example.deepdive.member.exception.exceptions.MemberErrorCode;
import com.example.deepdive.member.exception.exceptions.MemberException;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 모든 칸트롤러의 전역 예외를 처리합니다.
public class MemberExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<MemberErrorResponse> handleException(MemberException e) {
        MemberErrorCode memberErrorCode = e.getErrorCode();
        MemberErrorResponse memberErrorResponse = new MemberErrorResponse(memberErrorCode.getCustomCode(), memberErrorCode.getMessage());
        return ResponseEntity.status(memberErrorCode.getHttpStatus()).body(memberErrorResponse);
    }
    //MemberException을 잡아 에러코트들 추출하고 이를 DTO로전달

}
