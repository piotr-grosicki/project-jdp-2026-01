package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .email("est@example.com")
                .passwordHash("pass")
                .isBlocked(false)
                .build();
        userRepository.saveAndFlush(testUser);
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
        Order savedOrder = orderRepository.saveAndFlush(createdOrder);
        Order savedOrder2 = orderRepository.saveAndFlush(paidOrder);

        //When
        List<Order> orders = orderRepository.findAll();

        //Then
        assertEquals(2, orders.size());
    }

    @Test
    void testDeleteOrder() {
        //Given
        Order order = Order.builder()
                .id(1L)
                .status("CREATED")
                .user(testUser)
                .build();
        Long id = order.getId();

        //When
        orderRepository.deleteById(id);

        //Then
        assertFalse(orderRepository.findById(id).isPresent());
    }

    @Test
    void testOrderUserRelation() {
        //Given
        Order order = Order.builder()
                .id(1L)
                .status("CREATED")
                .user(testUser)
                .build();
        Long orderId = order.getId();
        Long userId = testUser.getId();

        //When
        orderRepository.deleteById(orderId);

        //Then
        assertFalse(orderRepository.existsById(orderId));
        assertTrue(userRepository.existsById(userId));
    }
}
