package com.kodilla.ecommercee.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GroupNotFoundException extends EntityNotFoundException {
    public GroupNotFoundException() {
        super("Group was not found");
    }
}
