package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    private Product productBeforeSaving;

    private Product productSaved;

    @BeforeEach
    public void generateProduct() {
        productBeforeSaving = Product.builder()
                .name("product")
                .description("desc")
                .price(BigDecimal.ONE)
                .build();
        productRepository.save(productBeforeSaving);
        productSaved = productRepository.findById(productBeforeSaving.getId()).orElseThrow();
    }

    @Test
    public void testCreateAndReadProduct() {
        //given
        //when
        //then
        assertEquals(productBeforeSaving.getId(), productSaved.getId());
        assertEquals(productBeforeSaving.getName(), productSaved.getName());
        assertEquals(productBeforeSaving.getPrice(), productSaved.getPrice());
        assertEquals(productBeforeSaving.getDescription(), productSaved.getDescription());
        assertNull(productSaved.getGroup());
    }

    @Test
    public void testUpdateProduct() {
        //given
        final String updatedProductName = "Updated name";
        //when
        productSaved.setName(updatedProductName);
        productRepository.save(productSaved);
        Product productUpdated = productRepository.findById(productSaved.getId()).orElseThrow();
        //then
        assertNotNull(productUpdated);
        assertEquals(updatedProductName, productUpdated.getName());
    }

    @Test
    public void testDeleteProduct() {
        //given
        //when
        productRepository.deleteById(productSaved.getId());
        Optional<Product> productDeleted = productRepository.findById(productSaved.getId());
        //then
        assertTrue(productDeleted.isEmpty());
    }

    @Test
    public void testCreateGroup() {
        //given
        Group group = Group.builder().name("A group").build();
        productSaved.setGroup(group);
        //when
        productRepository.save(productSaved);
        //then
        assertThrows(InvalidDataAccessApiUsageException.class, () -> groupRepository.findById(group.getId()));
    }

    @Nested
    class ReadDeleteGroup {

        private Group savedGroup;

        @BeforeEach
        public void createGroup() {
            Group group = Group.builder().name("A group").build();
            groupRepository.save(group);
            productSaved.setGroup(group);
            productRepository.save(productSaved);
            savedGroup = group;
        }

        @Test
        public void testReadGroup() {
            //given
            //when
            Product product = productRepository.findById(productSaved.getId()).orElseThrow();
            Group groupSaved = productSaved.getGroup();
            //then
            assertNotNull(groupSaved);
        };

        @Test
        public void testDeleteGroup() {
            //given
            //when
            productRepository.deleteById(productSaved.getId());
            Group groupFromDeletedProduct = groupRepository.findById(savedGroup.getId()).orElseThrow();
            //then
            assertNotNull(groupFromDeletedProduct);
        };
    }
}