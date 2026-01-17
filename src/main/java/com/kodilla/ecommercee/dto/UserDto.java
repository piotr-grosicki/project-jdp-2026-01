package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record UserDto (Long id,
                       @Email(message = "Enter the correct email address")
                       String email,
                       @Size(min = 6, max = 20, message = "The password must contain between 6 and 20 characters.")
                       String passwordHash,
                       @NotEmpty(message = "Enter the correct value true or false")
                       boolean isBlocked,
                       String sessionKey, LocalDateTime sessionKeyExpiresAt) {
}
