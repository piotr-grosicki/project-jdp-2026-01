package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTestSuite {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    private User testUser;
    private List<Product> testProducts;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .email("est@example.com")
                .passwordHash("pass")
                .blocked(false)
                .build();
        userRepository.saveAndFlush(testUser);

        Product testProduct1 = Product.builder()
                .name("test product 1")
                .price(BigDecimal.valueOf(10.00))
                .build();
        Product testProduct2 = Product.builder()
                .name("test product 2")
                .price(BigDecimal.valueOf(20.00))
                .build();
        testProducts = productRepository.saveAllAndFlush(List.of(testProduct1, testProduct2));
    }

    @Test
    void testCreateOrder() {
        //Given
        Order order = Order.builder()
                .status("CREATED")
                .user(testUser)
                .build();

        //When
        Order savedOrder = orderRepository.saveAndFlush(order);

        //Then
        assertNotNull(savedOrder.getId());
        assertEquals("CREATED", savedOrder.getStatus());
    }

    @Test
    void testGetOrder() {
        //Given
        Order order = Order.builder()
                .status("CREATED")
                .user(testUser)
                .build();
        Order savedOrder = orderRepository.saveAndFlush(order);

        //When
        Optional<Order> fetchedOrder = orderRepository.findById(savedOrder.getId());

        //Then
        assertTrue(fetchedOrder.isPresent());
        assertEquals(savedOrder.getId(), fetchedOrder.get().getId());
    }

    @Test
    void testGetAllOrders() {
        //Given
        Order createdOrder = Order.builder()
                .status("CREATED")
                .user(testUser)
                .build();
        Order paidOrder = Order.builder()
                .status("CREATED")
                .user(testUser)
                .build();
        orderRepository.saveAndFlush(createdOrder);
        orderRepository.saveAndFlush(paidOrder);

        //When
        List<Order> orders = orderRepository.findAll();

        //Then
        assertEquals(2, orders.size());
    }

    @Test
    void testDeleteOrder() {
        //Given
        Order order = Order.builder()
                .status("CREATED")
                .user(testUser)
                .build();
        Order savedOrder = orderRepository.saveAndFlush(order);
        Long id = savedOrder.getId();

        //When
        orderRepository.deleteById(id);

        //Then
        assertFalse(orderRepository.findById(id).isPresent());
    }

    @Test
    void testOrderUserRelation() {
        //Given
        Order order = Order.builder()
                .status("CREATED")
                .user(testUser)
                .build();
        Order savedOrder = orderRepository.saveAndFlush(order);
        Long orderId = savedOrder.getId();
        Long userId = testUser.getId();

        //When
        orderRepository.deleteById(orderId);

        //Then
        assertFalse(orderRepository.existsById(orderId));
        assertTrue(userRepository.existsById(userId));
    }

    @Test
    void testOrderProductRelation() {
        //Given
        Order order = Order.builder()
                .status("CREATED")
                .user(testUser)
                .products(testProducts)
                .build();
        Order savedOrder = orderRepository.saveAndFlush(order);
        Long orderId = savedOrder.getId();
        Long productId = testProducts.get(0).getId();

        //When
        orderRepository.deleteById(orderId);

        //Then
        assertFalse(orderRepository.existsById(orderId));
        assertTrue(productRepository.existsById(productId));
    }
}
