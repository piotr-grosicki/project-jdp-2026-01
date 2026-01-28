package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CreateUpdateCartDto;
import com.kodilla.ecommercee.dto.CartDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    // uncomment when cart service
    // private CartService cartService

    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts() {
        return ResponseEntity.ok(List.of(new CartDto(1L, 1L)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable Long id) throws CartNotFoundException {
        return ResponseEntity.ok(new CartDto(id, 1L));
    }

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody @Valid CreateUpdateCartDto cartDto) {
        Long idGeneratedAfterAdding = 1L;
        return ResponseEntity.ok(new CartDto(idGeneratedAfterAdding, cartDto.userId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto>  updateCart(@PathVariable Long id,@RequestBody @Valid CreateUpdateCartDto cartDto) {
        return ResponseEntity.ok(new CartDto(id, cartDto.userId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
