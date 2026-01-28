package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.NotNull;

public record CreateUpdateCartDto(@NotNull Long userId) {
}
