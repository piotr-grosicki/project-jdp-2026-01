package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.exception.EmptyCartException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kodilla.ecommercee.domain.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(final Long id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Order updateOrder(final Long id, final Order order) {
        Order foundOrder = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        foundOrder.setStatus(order.getStatus());
        foundOrder.getProducts().clear();
        foundOrder.getProducts().addAll(order.getProducts());
        return orderRepository.save(foundOrder);
    }

    public void deleteOrder(final Long id) {
        if (!orderRepository.existsById(id)) throw new OrderNotFoundException();
        orderRepository.deleteById(id);
    }

    public void createOrder(final Cart cart) {
        if (cart.getProducts().isEmpty()) throw new EmptyCartException();
        Order order = Order.builder()
                .user(cart.getUser())
                .products(new ArrayList<>(cart.getProducts()))
                .status(OrderStatus.NEW).build();
        orderRepository.save(order);
        cart.getProducts().clear();
        cartRepository.save(cart);

    }

    public void changeStatus(final Long orderId, final OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.setStatus(status);
        orderRepository.save(order);
    }
}


