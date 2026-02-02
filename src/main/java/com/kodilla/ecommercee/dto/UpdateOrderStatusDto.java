package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusDto (@NotNull OrderStatus orderStatus) {
}
