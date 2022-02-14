package com.sparta.found.error.exceptionhandler;

import com.sparta.found.error.ErrorCode;
import com.sparta.found.error.ErrorRes;
import com.sparta.found.error.exception.CustomAuthorizationException;
import com.sparta.found.error.exception.CustomException;
import com.sparta.found.error.exception.CustomFieldException;
import com.sparta.found.web.dto.ResDto;
import org.apache.tomcat.jni.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResDto methodArgumentNotValidException(MethodArgumentNotValidException e){

        return ResDto.builder()
                .result(false)
                .data(ErrorRes.ofField(e.getBindingResult(), ErrorCode.INVALID_INPUT_ERROR))
                .build();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResDto customException(CustomException e){

        return ResDto.builder()
                .result(false)
                .data(ErrorRes.of(e.getReason(), e.getErrorCode()))
                .build();
    }

    @ExceptionHandler(CustomFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResDto customFieldException(CustomFieldException e){

        return ResDto.builder()
                .result(false)
                .data(ErrorRes.ofField(e.getField(),e.getReason(),e.getErrorCode()))
                .build();
    }

    @ExceptionHandler(CustomAuthorizationException.class)
    public ResDto customAuthorizationException(CustomAuthorizationException e){

        return ResDto.builder()
                .result(false)
                .data(ErrorRes.of(e.getMessage(),e.getErrorCode()))
                .build();
    }

}
