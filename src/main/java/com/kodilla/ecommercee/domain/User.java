package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "app_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column (name = "password_hash", nullable = false)
    @Setter
    private String passwordHash;

    @Setter
    @Column(name = "blocked", nullable = false)
    private boolean blocked;

    @Setter
    @Column (name = "session_key")
    private String sessionKey;

    @Setter
    @Column (name = "session_key_expires_at")
    private LocalDateTime sessionKeyExpiresAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @Builder.Default
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        if (cart != null && cart.getUser() != this) {
            cart.setUser(this);
        }
    }
}
