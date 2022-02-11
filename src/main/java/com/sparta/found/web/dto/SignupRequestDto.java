package com.sparta.found.web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequestDto {
    private String username;
    private String password;
    private String passwordCheck;
}
