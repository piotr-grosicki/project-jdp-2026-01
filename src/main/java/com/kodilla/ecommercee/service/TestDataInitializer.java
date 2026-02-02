package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .email("test@email.com")
                .passwordHash("H12345678")
                .blocked(false)
                .build();
        Cart cart = Cart.builder()
                .user(user)
                .build();

        user.setCart(cart);
        userRepository.save(user);
    }
}
