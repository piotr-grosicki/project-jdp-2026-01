package com.kodilla.ecommercee.controller;

import jakarta.persistence.EntityNotFoundException;

public class GroupNotFoundException extends EntityNotFoundException {
    public GroupNotFoundException() {
        super("Group was not found");
    }
}
