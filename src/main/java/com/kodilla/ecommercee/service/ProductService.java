package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(final Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(final Long id, final Product product) {
        Product foundProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        foundProduct.setName(product.getName());
        foundProduct.setDescription(product.getDescription());
        foundProduct.setPrice(product.getPrice());
        foundProduct.setGroup(product.getGroup());
        return productRepository.save(foundProduct);
    }

    public void deleteProduct(final Long id) {
        Product foundProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.deleteById(id);
    }
}
