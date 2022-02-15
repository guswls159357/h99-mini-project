package com.sparta.found.web.controller;

import com.sparta.found.domain.repository.UserRepository;
import com.sparta.found.domain.service.AuthService;
import com.sparta.found.domain.service.UserService;
import com.sparta.found.web.dto.*;
import com.sparta.found.web.dto.auth.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/user/signup")
    @ResponseStatus(HttpStatus.OK)
    public ResDto signUp(@Validated @RequestBody SignupRequestDto dto){

        userService.signUp(dto);

        return ResDto
                .builder()
                .result(true)
                .build();
    }

    @PostMapping("/user/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequestDto dto){

        TokenDto tokenDto = authService.login(dto);
        HttpHeaders headers = authService.tokenToHeader(tokenDto);

        ResDto<Object> resDto = ResDto.builder()
                .result(true)
                .data(userRepository.findByUsername(dto.getUsername()).get().toUserInfo())
                .build();

        return ResponseEntity.ok()
                .headers(headers)
                .body(resDto);

    }

    @PostMapping("/user/idCheck")
    public ResDto idCheck(@Valid @RequestBody IdcheckRequestDto dto){

        userService.idDuplicateCheck(dto.getUsername());

        return ResDto.builder()
                .result(true)
                .build();
    }

    @GetMapping("/user")
    @Secured("ROLE_USER")
    public ResDto getUserInfo(){

        UserInfo userInfo = userService.getUserInfo();

            return ResDto.builder()
                    .result(true)
                    .data(userInfo)
                    .build();

    }

}
