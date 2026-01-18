package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//put and get have use the same object (because of the same fields)
public record CreateUpdateGroupDto(
        @NotNull
        @Size(min = 3, max = 200, message = "Group name must be between 3 and 200 characters long")
        String name) {
}
