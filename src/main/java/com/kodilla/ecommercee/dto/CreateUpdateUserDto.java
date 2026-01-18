package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record CreateUpdateUserDto(
        @NotNull
        @Email
        @Size(max = 320)
        String email,

        @NotNull
        @Size(min=8, max=20)
        //check password sent from user, (NOT HASH)
        String password,

        @NotNull
        boolean is_blocked,

        @NotNull
        @Size(max=255)
        String session_key,

        @NotNull
        LocalDateTime session_key_expires_at) {
}
