package com.example.server.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomResponseDto<T> {
    private boolean success;
    private T data;
    private String massege;

    public static <T> CustomResponseDto<T> success(T data, String massege) {
        return new CustomResponseDto<>(true, data, massege);
    }
    public static <T> CustomResponseDto<T> fail(T error, String massege) {
        return new CustomResponseDto<>(false, null, massege);
    }
}

