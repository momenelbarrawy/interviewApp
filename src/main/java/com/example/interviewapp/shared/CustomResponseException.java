package com.example.interviewapp.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomResponseException extends RuntimeException {
    private int statusCode;
    private String message;

    public static CustomResponseException ResourceNotFound(String message) {
        return new CustomResponseException(404, message);
    }

    public static CustomResponseException BadRuqest(String message) {
        return new CustomResponseException(400, message);
    }
}