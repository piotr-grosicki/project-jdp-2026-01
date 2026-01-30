package com.kodilla.ecommercee.mappper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final GroupService groupService;

    public ProductDto mapToProductDto(final Product product) {
        return ProductDto.builder().name(product.getName()).groupId(product.getGroup().getId())
                .price(product.getPrice()).description(product.getDescription()).build();
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> products) {
        return products.stream().map(this::mapToProductDto).toList();
    }

    public Product mapToProduct(final ProductDto productDto) {
        Group group = groupService.getGroupById(productDto.groupId());

        return Product.builder().name(productDto.name()).group(group)
                .price(productDto.price()).description(productDto.description()).build();
    }
}

