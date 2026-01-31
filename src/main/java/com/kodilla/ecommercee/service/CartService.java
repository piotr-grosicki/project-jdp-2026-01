package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.CartNotFoundException;
import com.kodilla.ecommercee.controller.ProductNotFoundException;
import com.kodilla.ecommercee.controller.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;

    private final CartMapper cartMapper;

    private final ProductMapper productMapper;

    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    public CartDto createEmptyCart(final CartDto cartDto) {
        User user = userRepository.findById(cartDto.userId()).orElseThrow(UserNotFoundException::new);
        Cart cart = Cart.builder().user(user).build();
        cartRepository.save(cart);
        return cartMapper.mapCartToCartDto(cart);
    }

    public List<ProductDto> getCartProducts(final Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        return productMapper.mapToProductDtoList(cart.getProducts());
    }

    public ProductDto addProductToCart(final Long cartId, final ProductDto productDto) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product product = productRepository.findById(productDto.id()).orElseThrow(ProductNotFoundException::new);
        cart.getProducts().add(product);
        cartRepository.save(cart);
        return productDto;
    }

    public void deleteProductFromCart(final Long cartId, final Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        List<Product> cartProducts = cart.getProducts();
        //https://codingwithharish.com/posts/java-stream-to-mutable-list/
        cartProducts = cartProducts.stream().filter(product -> product.getId() != productId).collect(Collectors.toCollection(ArrayList::new));
        cart.getProducts().clear();
        cart.getProducts().addAll(cartProducts);
        cartRepository.save(cart);
    }

    public OrderDto addOrderBasedOnCart(final Long cartId, final OrderDto orderDto) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Order order = orderMapper.mapToOrder(orderDto);
        List<Product> cartProducts = cart.getProducts();
        order.getProducts().addAll(cartProducts);
        orderRepository.save(order);
        //czyszczeie koszyka po zlozeniu zamowienia (jak nie chcecie czyszczenia to usuncie ta linijke)
        cart.getProducts().clear();
        cartRepository.save(cart);
        return orderDto;
    }
}
