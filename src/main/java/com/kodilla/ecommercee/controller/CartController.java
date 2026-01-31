package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.CreateUpdateCartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createEmptyCart(@RequestBody @Valid CreateUpdateCartDto cartDto) {
        return ResponseEntity.ok(cartService.createEmptyCart(cartDto));
    }

    @GetMapping("/{cartId}/products")
    public ResponseEntity<List<ProductDto>> getCartProducts(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCartProducts(cartId));
    }

    @PostMapping("/{cartId}/products")
    public ResponseEntity<ProductDto> addProductToCart(@PathVariable Long cartId, @RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.ok(cartService.addProductToCart(cartId, productDto));
    }

    @PostMapping("/{cartId}/order")
    public ResponseEntity<OrderDto> addOrderBasedOnCart(@PathVariable Long cartId, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(cartService.addOrderBasedOnCart(cartId, orderDto));
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.deleteProductFromCart(cartId, productId);
        return ResponseEntity.ok().build();
    }

}
