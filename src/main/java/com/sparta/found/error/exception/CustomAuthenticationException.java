package com.sparta.found.error.exception;

import com.sparta.found.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class CustomAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    private String reason;
    private final ErrorCode errorCode = ErrorCode.AUTHENTICATION_ERROR;

    public CustomAuthenticationException(String reason) {
        super(reason);
        this.reason = reason;
    }
}
