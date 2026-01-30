package com.kodilla.ecommercee.dto;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String email,
        boolean is_blocked,
        String session_key,
        LocalDateTime session_key_expires_at) {
}
