package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "app_users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "email", unique = true, nullable = false)
    private String email;

    @Column (name = "passwordHash", nullable = false)
    private String passwordHash;

    @Column (name = "blocked", nullable = false)
    private boolean blocked;

    @Column (name = "sessionKey")
    private String sessionKey;

    @Column (name = "sessionKeyExpiresAt")
    private LocalDateTime sessionKeyExpiresAt;

    @OneToOne (mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @Builder.Default
    @OneToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "user",  fetch = FetchType.LAZY)
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
        if (cart != null) {
            this.cart = cart;
            cart.setUser(this);
        }
    }
}
