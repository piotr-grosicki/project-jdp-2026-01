package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotNull
        @Email
        @Size(max = 320)
        String email,

        @NotNull
        @Size(min=8, max=20)
        //check password sent from user, (NOT HASH)
        String password
) {
}