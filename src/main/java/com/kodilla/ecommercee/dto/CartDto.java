package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CartDto(Long id, @NotNull Long userId, @NotNull List<ProductDto> productsDto) {
}
