package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CartDto(Long id, @NotNull Long userId) {
}
