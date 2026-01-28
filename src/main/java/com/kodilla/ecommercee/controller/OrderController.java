package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = List.of(
                new OrderDto(1L, "CREATED", 10L),
                new OrderDto(2L, "PAID", 11L)
        );
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        if (orderId == null || orderId <= 0) {
            throw new OrderNotFoundException();
        }
        OrderDto order = new OrderDto(orderId, "CREATED", 10L);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto) {
        if (orderId == null || orderId <= 0) {
            throw new OrderNotFoundException(orderId);
        }
        OrderDto updatedOrder = new OrderDto(orderId, orderDto.status(), orderDto.userId());
        return ResponseEntity.ok(updatedOrder);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = new OrderDto(3L, orderDto.status(), orderDto.userId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.noContent().build();
    }
}
