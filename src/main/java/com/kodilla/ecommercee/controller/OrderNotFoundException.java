package com.kodilla.ecommercee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Order not found");
    }
    public OrderNotFoundException(Long orderId) {
        super("Order with id " + orderId + " not found");
    }
}