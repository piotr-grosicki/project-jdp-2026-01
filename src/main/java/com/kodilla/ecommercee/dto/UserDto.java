package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserDto(Long id, @NotNull @Email String email, boolean is_blocked, String session_key, LocalDateTime session_key_expires_at) {
}
