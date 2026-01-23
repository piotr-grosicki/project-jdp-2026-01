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

    private Product createProduct() {
        Group group = Group.builder().name("Group").build();
        groupRepository.save(group);
        Product product = Product
                .builder()
                .name("Product")
                .price(new BigDecimal("1.11"))
                .group(group)
                .build();
        return productRepository.save(product);

    };

    @Test
    public void testCreateProduct() {
        //given&when
        Product product = createProduct();
        //then
        assertNotNull(product);
    };

    @Test
    public void testReadProduct() {
        //given
        Product product = createProduct();
        //when
        Optional<Product> productSaved = productRepository.findById(product.getId());
        //then
        assertTrue(productSaved.isPresent());
        Product productSavedOpen = productSaved.get();
        assertEquals(product.getId(), productSavedOpen.getId());
        assertEquals(product.getName(), productSavedOpen.getName());
        assertEquals(product.getGroup().getId(), productSavedOpen.getGroup().getId());
        assertEquals(product.getDescription(), productSavedOpen.getDescription());
    };

    @Test
    public void testUpdateProduct() {
        //given
        Product product = createProduct();
        //when
        product.setName("New name");
        Product productModified = productRepository.save(product);
        //then
        assertNotNull(productModified);
        assertEquals(product.getName(), productModified.getName());
    };

    @Test
    public void testDeleteProduct() {
        //given
        Product product = createProduct();
        Group group = product.getGroup();
        Long productId = product.getId();
        //when
        productRepository.deleteById(product.getId());
        Optional<Product> productDeleted = productRepository.findById(productId);
        Optional<Group> groupFromDeletedProduct = groupRepository.findById(group.getId());
        //then
        assertTrue(productDeleted.isEmpty());
        assertTrue(groupFromDeletedProduct.isPresent());
    };

    @Test
    public void testReadGroup() {
        //given
        Product product = createProduct();
        //when
        Group group = product.getGroup();
        //then
        assertNotNull(group);
    };

}