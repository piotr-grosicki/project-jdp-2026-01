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

    private Product createProduct(Group group) {
        Product product = new Product(1L, "Some product", "Description", new BigDecimal("1.35"), group);
        return productRepository.save(product);
    };

    private Group createGroup() {
        Group group = new Group("Some group");
        return groupRepository.save(group);
    }

    private void cleanUpProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    private void cleanUpGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    };

    @Test
    public void testAddProductAndGetProduct() {
        //given
        Product product = createProduct(null);
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
        //cleanup
        cleanUpProduct(product.getId());
    }

    @Test
    public void testGetGroup() {
        //given
        Group group = createGroup();
        Product product = createProduct(group);
        //when
        Product productSaved = productRepository.findById(product.getId()).get();
        //then
        assertNotNull(productSaved.getGroup());
        Group groupSaved = productSaved.getGroup();
        assertEquals(group.getId(), groupSaved.getId());
        assertEquals(group.getName(), groupSaved.getName());
        //cleanup
        cleanUpProduct(product.getId());
        cleanUpGroup(group.getId());
    };

    @Test
    public void testUpdateProduct() {
        //given
        Product product = createProduct(null);
        //when
        product.setName("Some new name");
        productRepository.save(product);
        Product productUpdatedSaved = productRepository.findById(product.getId()).get();
        //then
        assertEquals(product.getId(), productUpdatedSaved.getId());
        assertEquals(product.getName(), productUpdatedSaved.getName());
        //cleanup
        cleanUpProduct(product.getId());
    };

    @Test
    public void testDeleteProduct() {
        //given
        Product product = createProduct(null);
        //when
        productRepository.deleteById(product.getId());
        Optional<Product> productDeleted = productRepository.findById(product.getId());
        //then
        assertTrue(productDeleted.isEmpty());
    };
}
