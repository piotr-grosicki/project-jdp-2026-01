package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String passwordHash;

    private boolean isBlocked;

    private String sessionKey;

    private LocalDateTime sessionKeyExpiresAt;
}
