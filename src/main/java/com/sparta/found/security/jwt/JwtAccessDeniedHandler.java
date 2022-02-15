package com.sparta.found.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.found.error.ErrorCode;
import com.sparta.found.error.ErrorRes;
import com.sparta.found.web.dto.ResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper om;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        String errorMsg = "권한이 없습니다";

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(ErrorCode.AUTHORIZATION_ERROR.getHttpStatus());
        ErrorRes exceptionRes = ErrorRes.of(errorMsg, ErrorCode.AUTHORIZATION_ERROR);
        ResDto<Object> resDto = ResDto.builder()
                .result(false)
                .data(exceptionRes)
                .build();

        String errorJson = om.writeValueAsString(resDto);

        PrintWriter writer = response.getWriter();
        writer.print(errorJson);
    }
}
