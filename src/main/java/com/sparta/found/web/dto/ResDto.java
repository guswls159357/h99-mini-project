package com.sparta.found.web.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResDto<T> {

    private Boolean result;
    private T data;
}
