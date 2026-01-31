package com.kodilla.ecommercee.controller;

import jakarta.persistence.EntityNotFoundException;

public class CartNotFoundException extends EntityNotFoundException {
    public CartNotFoundException() {
        super("Cart was not found");
    }
}