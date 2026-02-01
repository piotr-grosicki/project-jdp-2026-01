package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderDto (Long id, @NotNull Long userId, @NotNull OrderStatus orderStatus,
                        @NotNull List<ProductDto> productsDto) {}
