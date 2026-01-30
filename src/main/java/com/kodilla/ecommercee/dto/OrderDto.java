package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.NotNull;

public record OrderDto (@NotNull Long id, @NotNull Long userId) {}
