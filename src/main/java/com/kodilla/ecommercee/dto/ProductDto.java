package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDto (
        Long id, @NotNull String name, String description, @NotNull BigDecimal price, Long groupId) {}
