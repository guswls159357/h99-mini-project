package com.sparta.found.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDto {

    @NotNull(message = "아이디를 입력해 주세요")
    private String username;
    @NotNull(message = "비밀번호를 입력해 주세요")
    private String password;

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(this.username,this.password);
    }

}
