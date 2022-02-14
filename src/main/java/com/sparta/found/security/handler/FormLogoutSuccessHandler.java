package com.sparta.found.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.found.domain.entity.User;
import com.sparta.found.web.dto.ResDto;
import com.sparta.found.web.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class FormLogoutSuccessHandler implements LogoutSuccessHandler {

    private final ObjectMapper om;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        ResDto<Object> resDto = ResDto.builder()
                .result(true)
                .build();

        String errorJson = om.writeValueAsString(resDto);

        PrintWriter writer = response.getWriter();
        writer.print(errorJson);
    }
}

