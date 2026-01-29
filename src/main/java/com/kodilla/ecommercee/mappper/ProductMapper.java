package com.kodilla.ecommercee.mappper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final GroupService groupService;

    public Product mapToProduct(final ProductDto dto) {
        Group group = null;
        if (dto.groupId() != null) {
            group = groupService.getGroupById(dto.groupId());
        }
        return Product.builder()
                .id(dto.id())
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .group(group)
                .build();
    }

    public ProductDto mapToProductDto(final Product product) {
        Long groupId = product.getGroup() != null ? product.getGroup().getId() : null;
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .groupId(groupId)
                .build();
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> products) {
        return products.stream().map(this::mapToProductDto).collect(Collectors.toList());
    }
}
