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
        List<OrderDto> orders = List.of(new OrderDto(1L, "CREATED"), new OrderDto(2L, "PAID"));
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        if (orderId == null || orderId <= 0) {
            throw new OrderNotFoundException();
        }
        OrderDto order = new OrderDto(orderId, "CREATED");
        return ResponseEntity.ok(order);
    }

    @DeleteMapping(value = "{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = new OrderDto(); // We create empty order. To be revised later.
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}
