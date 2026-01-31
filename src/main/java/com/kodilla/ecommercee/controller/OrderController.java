package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.mapToOrderDtoList(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        Order order = orderService.getOrder(id);
        return ResponseEntity.ok(orderMapper.mapToOrderDto(order));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderMapper.mapToOrderDto(orderService.saveOrder(order)));

    }

    @PutMapping( "/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderDto orderDto) {
        Order order = orderService.updateOrder(id, orderMapper.mapToOrder(orderDto));
        return ResponseEntity.ok(orderMapper.mapToOrderDto(order));
    }

    @DeleteMapping( "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
