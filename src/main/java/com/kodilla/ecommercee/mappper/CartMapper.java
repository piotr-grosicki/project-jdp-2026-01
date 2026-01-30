package com.kodilla.ecommercee.mappper;

import com.kodilla.ecommercee.controller.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.CreateUpdateCartDto;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    @Autowired
    private UserRepository userRepository;

    public Cart mapCreateUpdateCartDtoToCart(CreateUpdateCartDto createUpdateCartDto) {
        User user = userRepository.findById(createUpdateCartDto.userId()).orElseThrow(UserNotFoundException::new);
        return Cart.builder().user(user).build();
    };

    public CartDto mapCartToCartDto(Cart cart) {
        return new CartDto(cart.getId(), cart.getUser().getId());
    }
}
