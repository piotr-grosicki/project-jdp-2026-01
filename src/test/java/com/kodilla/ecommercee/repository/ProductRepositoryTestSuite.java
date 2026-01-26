package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductRepositoryTestSuite {

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

    //create i read wygladaja tak samo, bo nie da sie przetestowac
    //czytania bez dodawania ani dodawania bez pozniejszego odczytywania (czy jest w bazie)

    @Test
    public void testCreateProduct() {
        //given
        //when
        //then
        assertNotNull(productSaved);
        assertEquals(productBeforeSaving.getId(), productSaved.getId());
        assertEquals(productBeforeSaving.getName(), productSaved.getName());
        assertEquals(productBeforeSaving.getPrice(), productSaved.getPrice());
        assertEquals(productBeforeSaving.getDescription(), productSaved.getDescription());
        assertNull(productSaved.getGroup());
    }

    @Test
    public void testReadProduct() {
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

    @Nested
    class GroupTests {

        private Group groupBeforeSaving;

        private Group groupSaved;

        @BeforeEach
        public void generateGroup() {
            groupBeforeSaving = Group.builder()
                    .name("group")
                    .build();
            productSaved.setGroup(groupBeforeSaving);
            productRepository.save(productSaved);
            productSaved = productRepository.findById(productSaved.getId()).orElseThrow();
            groupSaved = productSaved.getGroup();
        }

        //ta sama sytuacja co wyzej

        @Test
        public void testCreateGroup() {
            //given
            //when
            //then
            assertNotNull(groupSaved);
            assertEquals(groupBeforeSaving.getId(), groupSaved.getId());
            assertEquals(groupBeforeSaving.getName(), groupSaved.getName());
        }

        @Test
        public void testReadGroup() {
            //given
            //when
            //then
            assertNotNull(groupSaved);
            assertEquals(groupBeforeSaving.getId(), groupSaved.getId());
            assertEquals(groupBeforeSaving.getName(), groupSaved.getName());
        }

        @Test
        public void testUpdateGroup() {
            //given
            final String updatedGroupName = "New group";
            //when
            groupSaved.setName(updatedGroupName);
            productSaved.setGroup(groupSaved);
            Product product = productRepository.findById(productSaved.getId()).orElseThrow();
            Group groupUpdated = product.getGroup();
            //then
            assertNotNull(groupUpdated);
            assertEquals(updatedGroupName, groupUpdated.getName());
        }

        @Test
        public void testCascadeDeleteGroup() {
            //given
            //when
            productRepository.deleteById(productSaved.getId());
            Group group = groupRepository.findById(groupSaved.getId()).orElseThrow();
            //then
            assertNotNull(group);
        }

    }

}