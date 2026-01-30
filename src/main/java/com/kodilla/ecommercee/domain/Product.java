package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column
    private String description;

    @Setter
    @Column(nullable = false)
    private BigDecimal price;

    @Setter
    @ManyToOne
    @JoinColumn(name = "product_group_id")
    private Group group;

    @Builder.Default
    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();
}

