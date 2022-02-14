package com.sparta.found.error.exception;

import com.sparta.found.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomAuthorizationException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String reason;
    private final ErrorCode errorCode = ErrorCode.AUTHORIZATION_ERROR;
}
