package com.example.deepdive.global.exception.jwt;


import com.example.deepdive.global.exception.CustomErrorCode;
import com.example.deepdive.global.exception.CustomException;

public class TokenTimeException extends CustomException {

    public TokenTimeException() {
        super(CustomErrorCode.EXPIRED_TOKEN);
    }
}
