package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kodilla.ecommercee.domain.Order;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

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
        Order foundOrder = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        orderRepository.deleteById(id);
    }



}


