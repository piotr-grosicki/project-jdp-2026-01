package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(final Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(final Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product updateProduct(final Long id, final Product product) {
        Product oldProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setGroup(product.getGroup());
        return productRepository.save(oldProduct);
    }

    public void deleteProduct(final Long id) {
        if(!productRepository.existsById(id) ) {
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(id);
    }

}