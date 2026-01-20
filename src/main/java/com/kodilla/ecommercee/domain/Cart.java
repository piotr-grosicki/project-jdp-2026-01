package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CARTS")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    //@OneToOne
    //@JoinColumn(name="USER_ID", nullable=false, unique=true)
    //private User user;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "CART_ITEMS",
//            joinColumns = @JoinColumn(name = "CART_ID"),
//            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
//    )
//    private List<Product> products;
}
