package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.CartNotFoundException;
import com.kodilla.ecommercee.controller.ProductNotFoundException;
import com.kodilla.ecommercee.controller.UserNotFoundException;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.EmptyCartException;
import com.kodilla.ecommercee.exception.UserNotAuthenticatedException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    private final UserAuthenticator authenticator;

    private User authenticateUser(final Long userId, final String sessionKey) throws UserNotAuthenticatedException{
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        authenticator.authenticate(user, sessionKey);
        return user;
    }

    public Cart createEmptyCart(final Long userId, final String sessionKey) {
        User user = authenticateUser (userId, sessionKey);
        if (user.getCart() != null) {
            return user.getCart();
        }
        Cart cart = Cart.builder().user(user).build();
        return cartRepository.save(cart);
    }

    public List<Product> getCartProducts(final Long userId, final String sessionKey) {
        User user = authenticateUser (userId, sessionKey);
        Cart cart = user.getCart();
        if (cart == null) throw new CartNotFoundException();
        return cart.getProducts();
    }

    public Product addProductToCart(final Long cartId, final Long productId, final Long userId, final String sessionKey){
        authenticateUser(userId, sessionKey);
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        cart.getProducts().add(product);
        cartRepository.save(cart);
        return product;
    }

    public void deleteProductFromCart(final Long cartId, final Long productId, final Long userId, final String sessionKey){
        authenticateUser (userId, sessionKey);
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product product =  productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        cart.getProducts().remove(product);
        cartRepository.save(cart);
    }

    public Order addOrderBasedOnCart(final Long cartId, final Long userId, final String sessionKey){
        authenticateUser (userId, sessionKey);
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        if (cart.getProducts().isEmpty()) throw new EmptyCartException();
        Order order = Order.builder()
                .user(cart.getUser())
                .products(new ArrayList<>(cart.getProducts()))
                .status(OrderStatus.NEW).build();
        cart.getProducts().clear();
        cartRepository.save(cart);
        return orderRepository.save(order);

    }

}
