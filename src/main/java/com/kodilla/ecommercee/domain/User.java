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
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Column(name = "session_key")
    private String sessionKey;

    @Column(name = "session_key_expires_at")
    private LocalDateTime sessionKeyExpiresAt;
}
