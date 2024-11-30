package com.example.deepdive.global.exception.jwt;

import com.example.deepdive.global.exception.CustomErrorCode;
import com.example.deepdive.global.exception.CustomException;

public class NotFoundTokenException extends CustomException {

    public NotFoundTokenException() {
        super(CustomErrorCode.NOT_FIND_TOKEN);
    }
}
