package com.sparta.found.error.exception;

import com.sparta.found.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomFieldException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String field;
    private String reason;
    private ErrorCode errorCode;
}
