package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.CartNotFoundException;
import com.kodilla.ecommercee.controller.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.CreateUpdateCartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mappper.CartMapper;
import com.kodilla.ecommercee.mappper.OrderMapper;
import com.kodilla.ecommercee.mappper.ProductMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public CartDto createEmptyCart(CreateUpdateCartDto createUpdateCartDto) {
        Cart cart = cartMapper.mapCreateUpdateCartDtoToCart(createUpdateCartDto);
        cartRepository.save(cart);
        return cartMapper.mapCartToCartDto(cart);
    };

    public List<ProductDto> getCartProducts(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        return productMapper.mapToProductDtoList(cart.getProducts());
    };

    public ProductDto addProductToCart(Long cartId, ProductDto productDto) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product product = productRepository.findById(productDto.id()).orElseThrow(ProductNotFoundException::new);
        cart.getProducts().add(product);
        cartRepository.save(cart);
        return productDto;
    };

    public void deleteProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        List<Product> cartProducts = cart.getProducts();
        cartProducts = cartProducts.stream().filter(product -> product.getId() != productId).toList();
        cart.setProducts(cartProducts);
        cartRepository.save(cart);
    };

    public OrderDto addOrderBasedOnCart(Long cartId, OrderDto orderDto) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Order order = orderMapper.mapToOrder(orderDto);
        List<Product> cartProducts = cart.getProducts();
        order.getProducts().addAll(cartProducts);
        orderRepository.save(order);
        //czyszczeie koszyka po zlozeniu zamowienia (jak nie chcecie czyszczenia to usuncie ta linijke)
        cart.setProducts(List.of());
        cartRepository.save(cart);
        return orderDto;
    }
}
