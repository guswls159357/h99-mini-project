package com.sparta.found.web.controller;

import com.sparta.found.domain.entity.User;
import com.sparta.found.domain.repository.UserRepository;
import com.sparta.found.domain.service.UserService;
import com.sparta.found.error.exception.CustomException;
import com.sparta.found.web.dto.IdcheckRequestDto;
import com.sparta.found.web.dto.ResDto;
import com.sparta.found.web.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public ResDto signUp(@Validated @RequestBody SignupRequestDto dto){

        userService.signUp(dto);

        return ResDto
                .builder()
                .result(true)
                .build();
    }

    @PostMapping("/idCheck")
    public ResDto idCheck(@Valid @RequestBody IdcheckRequestDto dto){

        userService.idDuplicateCheck(dto.getUsername());

        return ResDto.builder()
                .result(true)
                .build();
    }

}
