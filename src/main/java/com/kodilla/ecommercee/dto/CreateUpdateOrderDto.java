package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.NotNull;

public record CreateUpdateOrderDto(@NotNull Long userId) {
}
