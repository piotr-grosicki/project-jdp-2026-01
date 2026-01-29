package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mappper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productMapper.mapToProductDtoList(productService.getAllProducts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.getProduct(id)));
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.saveProduct(productMapper.mapToProduct(productDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.ok(productMapper.mapToProductDto(productService.updateProduct(id, productMapper.mapToProduct(productDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
