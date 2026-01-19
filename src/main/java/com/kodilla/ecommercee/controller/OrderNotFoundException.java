package com.kodilla.ecommercee.controller;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Order not found");
    }
    public OrderNotFoundException(Long orderId) {
        super("Order with id " + orderId + " not found");
    }
}