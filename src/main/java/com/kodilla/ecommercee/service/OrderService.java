package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.OrderNotFoundException;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kodilla.ecommercee.domain.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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

    public Order updateOrder(final Long id, final OrderStatus status) {
        Order foundOrder = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        foundOrder.setStatus(status);
        return orderRepository.save(foundOrder);
    }

    public void deleteOrder(final Long id) {
        if (!orderRepository.existsById(id)) throw new OrderNotFoundException();
        orderRepository.deleteById(id);
    }


}


