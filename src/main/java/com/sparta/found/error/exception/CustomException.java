package com.sparta.found.error.exception;

import com.sparta.found.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String reason;
    private ErrorCode errorCode;
}
