package com.sparta.found.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.found.error.ErrorCode;
import com.sparta.found.error.ErrorRes;
import com.sparta.found.web.dto.ResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.sparta.found.error.ErrorCode.*;

@RequiredArgsConstructor
@Component
public class FormAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper om;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMsg;

        if(exception instanceof BadCredentialsException||
        exception instanceof InternalAuthenticationServiceException){
            errorMsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해 주세요";
        }else if(exception instanceof CredentialsExpiredException) {
            errorMsg = "비밀번호 유효기간이 지났습니다. 비밀번호를 재설정해주세요";
        }else{
            errorMsg = "로그인이 필요합니다";
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(AUTHENTICATION_ERROR.getHttpStatus());
        ErrorRes exceptionRes = ErrorRes.of(errorMsg, AUTHENTICATION_ERROR);
        ResDto<Object> resDto = ResDto.builder()
                .result(false)
                .data(exceptionRes)
                .build();

        String errorJson = om.writeValueAsString(resDto);

        PrintWriter writer = response.getWriter();
        writer.print(errorJson);
    }

}
