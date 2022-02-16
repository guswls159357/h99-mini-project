package com.sparta.found.domain.service;

import com.sparta.found.domain.entity.User;
import com.sparta.found.domain.repository.UserRepository;
import com.sparta.found.security.jwt.TokenProvider;
import com.sparta.found.web.dto.user.LoginRequestDto;
import com.sparta.found.web.dto.auth.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.found.security.jwt.TokenProvider.AUTHORIZATION_HEADER;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Transactional
    public TokenDto login(LoginRequestDto dto){

        // login ID/Password를 기반으로 Authentication 생성
        UsernamePasswordAuthenticationToken authenticationToken = dto.toAuthentication();

        // 실제로 검증 -> userdetailsService -> loaduserbyusername에서 검증
        // 비밀번호 검증은 DaoAuthenticationProvider에서 구현되어있음!
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        //JWT생성
        TokenDto tokenDto = tokenProvider.generateToken(authentication);

        User user = userRepository.findByUsername(dto.getUsername()).get();

        return tokenDto;
    }


    public HttpHeaders tokenToHeader(TokenDto tokenDto){
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION_HEADER,tokenDto.getAccessToken());

        return headers;
    }

}
