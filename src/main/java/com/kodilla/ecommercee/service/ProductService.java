package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.controller.GroupNotFoundException;
import com.kodilla.ecommercee.controller.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final GroupRepository groupRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product createProduct(ProductDto productDto) {
        Group group = null;
        if(productDto.groupId() != null){
            group = groupRepository.findById(productDto.groupId())
                    .orElseThrow(GroupNotFoundException::new);
        }
        Product product = new Product();
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        product.setGroup(group);
        return productRepository.save(product);
    }
    public Product updateProduct(long id, ProductDto productDto) {
        Product product = getProduct(id);
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        if(productDto.groupId() != null){
            Group group = groupRepository.findById(productDto.groupId())
                    .orElseThrow(GroupNotFoundException::new);
            product.setGroup(group);
        }
        return productRepository.save(product);
    }
    public void deleteProduct(long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }

}
