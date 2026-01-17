package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mappper.ProductMapper;
import com.kodilla.ecommercee.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    //private final DbService dbService;
    //private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(new ProductDto(id,"P1", "D1", new BigDecimal(0), 1L));
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(new ProductDto(1L, "P1", "D1", new BigDecimal(0), 1L));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(new ProductDto(1L, "P1", "D1", new BigDecimal(0), 1L));
    }

    @DeleteMapping(value={"{id}"})
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity.ok().build();
    }





}
