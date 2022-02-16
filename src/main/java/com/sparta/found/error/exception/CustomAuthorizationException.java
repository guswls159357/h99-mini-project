package com.sparta.found.error.exception;

import com.sparta.found.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;

@Getter
public class CustomAuthorizationException extends AccessDeniedException {

    private static final long serialVersionUID = 1L;

    private String reason;
    private final ErrorCode errorCode = ErrorCode.AUTHORIZATION_ERROR;

    public CustomAuthorizationException(String reason) {
        super(reason);
        this.reason = reason;
    }
}
