package com.sparta.found.web.dto.user;

import com.sparta.found.domain.entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequestDto {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",message = "이메일 형식으로 입력해 주세요")
    private String username;

    @Pattern(regexp = "^.*(?=^.{6,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#]).*$",
            message = "6-15글자의 알파벳 대,소문자와 숫자, 최소 1개 이상의 !,@,#를 입력하셔야 합니다")
    private String password;

    @NotNull(message = "비밀번호 확인을 입력해 주세요")
    private String passwordCheck;

    public User createUser(){

        return User.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
