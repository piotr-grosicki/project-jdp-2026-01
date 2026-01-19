package com.kodilla.ecommercee.controller;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
        super("User was not found");
    }
}
