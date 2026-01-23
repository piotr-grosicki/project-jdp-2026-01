package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderRepositoryTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User(null, "test@example.com", "pass", false, null, null);
        userRepository.save(testUser);
    }

    @Test
    void testCreateOrder() {
        //Given
        Order order = new Order(null, "CREATED", testUser);

        //When
        Order savedOrder = orderRepository.save(order);

        //Then
        assertNotNull(savedOrder.getId());
        assertEquals("CREATED", savedOrder.getStatus());
    }

    @Test
    void testGetOrder() {
        //Given
        Order order = orderRepository.save(new Order(null, "CREATED", testUser));

        //When
        Optional<Order> fetchedOrder = orderRepository.findById(order.getId());

        //Then
        assertTrue(fetchedOrder.isPresent());
        assertEquals(order.getId(), fetchedOrder.get().getId());
    }

    @Test
    void testGetAllOrders() {
        //Given
        orderRepository.save(new Order(null, "CREATED", testUser));
        orderRepository.save(new Order(null, "PAID", testUser));

        //When
        List<Order> orders = orderRepository.findAll();

        //Then
        assertEquals(2, orders.size());
    }

    @Test
    void testUpdateOrder() {
        //Given
        Order order = orderRepository.save(new Order(null, "CREATED", testUser));

        //When
        Order orderToUpdate = new Order(order.getId(), "PAID", testUser);
        orderRepository.save(orderToUpdate);

        //Then
        Order updatedOrder = orderRepository.findById(order.getId()).orElseThrow();
        assertEquals("PAID", updatedOrder.getStatus());
    }

    @Test
    void testDeleteOrder() {
        //Given
        Order order = orderRepository.save(new Order(null, "CREATED", testUser));
        Long id = order.getId();

        //When
        orderRepository.deleteById(id);

        //Then
        assertFalse(orderRepository.findById(id).isPresent());
    }

    @Test
    void testOrderUserRelation() {
        //Given
        Order order = orderRepository.save(new Order(null, "CREATED", testUser));
        Long orderId = order.getId();
        Long userId = testUser.getId();

        //When
        orderRepository.deleteById(orderId);

        //Then
        assertFalse(orderRepository.existsById(orderId));
        assertTrue(userRepository.existsById(userId));
    }
}
