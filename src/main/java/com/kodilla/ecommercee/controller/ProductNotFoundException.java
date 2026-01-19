package com.kodilla.ecommercee.controller;

import jakarta.persistence.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException() {
        super("Product was not found");
    }
}
