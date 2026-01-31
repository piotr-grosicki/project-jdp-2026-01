package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartDto mapCartToCartDto(Cart cart) {
        return new CartDto(cart.getId(), cart.getUser().getId());
    }
}
