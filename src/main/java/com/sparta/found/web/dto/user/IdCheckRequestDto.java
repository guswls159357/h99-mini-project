package com.sparta.found.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdCheckRequestDto {

    @NotBlank(message = "아이디를 입력해 주세요")
    private String username;
}
