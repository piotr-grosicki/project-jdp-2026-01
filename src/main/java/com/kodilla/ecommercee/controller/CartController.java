package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.CartRequestDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<CartDto> createEmptyCart(@RequestBody CartRequestDto cartRequestDto,
                                                   @RequestHeader("X-Session-Key") String sessionKey) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cartMapper.mapToCartDto(cartService.createEmptyCart(cartRequestDto.userId(), sessionKey)));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getCartProducts(@RequestParam Long userId,
                                                            @RequestHeader("X-Session-Key") String sessionKey) {
        return ResponseEntity.ok(productMapper.mapToProductDtoList(cartService.getCartProducts(userId, sessionKey)));
    }

    @PostMapping("/{cartId}/products/{productId}")
    public ResponseEntity<ProductDto> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId,
                                                       @RequestBody @Valid CartRequestDto cartRequestDto,
                                                       @RequestHeader("X-Session-Key") String sessionKey) {
        Product product = cartService.addProductToCart(cartId, productId, cartRequestDto.userId(), sessionKey);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productMapper.mapToProductDto(product));
    }

    @PostMapping("/{cartId}/orders")
    public ResponseEntity<OrderDto> addOrderBasedOnCart(@PathVariable Long cartId,
                                                        @RequestBody @Valid CartRequestDto cartRequestDto,
                                                        @RequestHeader("X-Session-Key") String sessionKey) {
        Order order = cartService.addOrderBasedOnCart(cartId, cartRequestDto.userId(), sessionKey);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderMapper.mapToOrderDto(order));
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable Long cartId, @PathVariable Long productId,
                                                      @RequestParam Long userId, @RequestHeader("X-Session-Key") String sessionKey) {
        cartService.deleteProductFromCart(cartId, productId, userId, sessionKey);
        return ResponseEntity.noContent().build();
    }

}
