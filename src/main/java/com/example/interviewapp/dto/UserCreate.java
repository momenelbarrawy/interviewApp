package com.example.interviewapp.dto;

public record UserCreate(
        String email,
        String fullName,
        String password,
        String role
) {
}
