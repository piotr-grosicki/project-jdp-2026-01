package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final UserService userService;
    private final ProductMapper productMapper;

    public OrderDto mapToOrderDto(final Order order) {
        List<ProductDto> productDtos = productMapper.mapToProductDtoList(order.getProducts());
         return new OrderDto(order.getId(), order.getUser().getId(), order.getStatus(), productDtos);
    }

    public Order mapToOrder(final OrderDto orderDto) {
        return Order.builder().id(orderDto.id()).user(userService.getUser(orderDto.userId())).build();
    }

    public List<OrderDto> mapToOrderDtoList (final List<Order> orders) {
        return orders.stream().map(this::mapToOrderDto).collect(Collectors.toList());
    }

}
