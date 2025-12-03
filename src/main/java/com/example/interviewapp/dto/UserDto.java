package com.example.interviewapp.dto;

import java.util.UUID;

public record UserDto(
        UUID id,
        String email,
        String fullName,
        String password,
        String role
) {
}
