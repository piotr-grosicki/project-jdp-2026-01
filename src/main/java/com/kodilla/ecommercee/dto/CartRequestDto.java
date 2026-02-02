package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.NotNull;

public record CartRequestDto(@NotNull Long userId) {
}
