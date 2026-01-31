package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "orders")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column (name = "status")
    private OrderStatus status;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @Column (name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable (
            name = "order_items",
            joinColumns = @JoinColumn (name = "order_id"),
            inverseJoinColumns = @JoinColumn (name = "product_id")
    )
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    public void setUser(User user) {
        this.user = user;
        if (user != null && !user.getOrders().contains(this)) {
            user.addOrder(this);
        }
    }

}
