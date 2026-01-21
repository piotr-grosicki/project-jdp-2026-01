package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductRepositoryTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testAddProductAndGetProduct() {
        //given
        Product product = new Product(1L, "Some product", "Description", new BigDecimal("1.35"), null);
        //when
        productRepository.save(product);
        Optional<Product> productOptional = productRepository.findById(product.getId());
        //then
        assertTrue(productOptional.isPresent());
        Product productSaved = productOptional.get();
        assertEquals(product.getId(), productSaved.getId());
        assertEquals(product.getDescription(), productSaved.getDescription());
        assertEquals(product.getGroup(), productSaved.getGroup());
        assertEquals(product.getName(), productSaved.getName());
        assertEquals(product.getPrice(), productSaved.getPrice());
    }

    @Test
    public void testAddGroupandGetGroup() {
        //given
        Group group = new Group("Some group");
        groupRepository.save(group);
        Product product = new Product(1L, "Some product", "Description", new BigDecimal("1.35"), group);
        //when
        productRepository.save(product);
        Product productSaved = productRepository.findById(product.getId()).get();
        //then
        assertNotNull(productSaved.getGroup());
        Group groupSaved = productSaved.getGroup();
        assertEquals(group.getId(), groupSaved.getId());
        assertEquals(group.getName(), groupSaved.getName());
    };
}
