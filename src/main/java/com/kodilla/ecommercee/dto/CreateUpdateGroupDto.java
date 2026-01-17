package com.kodilla.ecommercee.dto;

import jakarta.validation.constraints.Size;

//dla PUT i dla POST obiekty DTO sa takie same, wiec nie ma sensu tworzenia
//dwoch takich samych klas :)
public record CreateUpdateGroupDto(
        @Size(min = 3, max = 200, message = "Nazwa grupy musi byc dluższa niż 3 znaki oraz krótsza niż 200 znaków")
        String name) {
}
