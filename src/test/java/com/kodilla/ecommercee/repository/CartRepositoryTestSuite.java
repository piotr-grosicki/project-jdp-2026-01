package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CartRepositoryTestSuite {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    Cart cart, savedCart, foundCart;
    User savedUser;
    Product p1;

    @BeforeEach
    public void beforeEach() {
        savedUser = userRepository.save(User.builder().email("test@email.com")
                .passwordHash("testPass").blocked(false).build());
        p1 = productRepository.save(Product.builder().name("P1").price(new BigDecimal(0)).build());
        Product p2 = productRepository.save(Product.builder().name("P2").price(new BigDecimal(0)).build());
        Product p3 = productRepository.save(Product.builder().name("P3").price(new BigDecimal(0)).build());
        List<Product> productList = new ArrayList<>(List.of(p1, p2, p3));

        cart = Cart.builder().user(savedUser).products(productList).build();
        savedCart = cartRepository.saveAndFlush(cart);
    }

    @Test
    void testReadCreatedCartWithProductsAndUser() {
        //When
        foundCart = cartRepository.findById(savedCart.getId()).orElseThrow();
        //Then
        assertEquals(3, foundCart.getProducts().size());
        assertNotNull(foundCart.getUser());
    }

    @Test
    void testUpdateCartByAddingProduct() {
        //Given
        foundCart = cartRepository.findById(savedCart.getId()).orElseThrow();
        //When
        Product p4 = productRepository.save(Product.builder().name("P4").price(new BigDecimal(0)).build());
        foundCart.addProduct(p4);
        foundCart.addProduct(p4);
        cartRepository.save(foundCart);
        foundCart = cartRepository.findById(foundCart.getId()).orElseThrow();
        //Then
        assertEquals(5, foundCart.getProducts().size());
    }

    @Test
    void testDeleteButOnlyCart() {
        //When
        cartRepository.delete(savedCart);
        cartRepository.flush();
        //Then
        assertFalse(cartRepository.findById(savedCart.getId()).isPresent());
        assertTrue(userRepository.findById(savedUser.getId()).isPresent());
        assertTrue(productRepository.findById(p1.getId()).isPresent());
    }
}
