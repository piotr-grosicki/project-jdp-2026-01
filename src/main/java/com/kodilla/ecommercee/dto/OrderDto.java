package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.NotNull;

public record OrderDto (Long id, @NotNull Long userId) {}
