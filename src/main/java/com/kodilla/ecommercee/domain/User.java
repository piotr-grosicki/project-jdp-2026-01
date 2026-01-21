package com.kodilla.ecommercee.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String passwordHash;

    private boolean isBlocked;

    private String sessionKey;

    private String sessionKeyExpiresAt;
}
