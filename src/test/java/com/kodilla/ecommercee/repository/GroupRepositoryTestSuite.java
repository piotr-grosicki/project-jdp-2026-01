package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GroupCrudTestSuite {

    @Autowired private GroupRepository groupRepository;
    @Autowired private ProductRepository productRepository;

    private Group group;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        group = Group.builder().name("Electronics").products(List.of()).build();
        product1 = Product.builder().name("Product1").description("desc").price(BigDecimal.TEN).build();
        product2 = Product.builder().name("Product2").description("desc").price(BigDecimal.ONE).build();
    }

    @Test
    void testCreateGroup() {
        //When
        Group saved = groupRepository.saveAndFlush(group);
        //Then
        assertNotNull(saved.getId());
        assertEquals("Electronics", saved.getName());
    }

    @Test
    void testGetGroupById() {
        //Given
        Group saved = groupRepository.saveAndFlush(group);
        //When
        Optional<Group> found = groupRepository.findById(saved.getId());
        //Then
        assertTrue(found.isPresent());
        assertEquals(saved.getId(), found.get().getId());
        assertEquals("Electronics", found.get().getName());
    }

    @Test
    void testGetAllGroups() {
        //Given
        groupRepository.saveAndFlush(group);
        groupRepository.saveAndFlush(Group.builder().name("Books").products(List.of()).build());
        //When
        List<Group> all = groupRepository.findAll();
        //Then
        assertNotNull(all);
        assertTrue(all.size() >= 2);
        boolean hasElectronics = false;
        boolean hasBooks = false;
        for (Group g : all) {
            if ("Electronics".equals(g.getName())) hasElectronics = true;
            if ("Books".equals(g.getName())) hasBooks = true;
        }
        assertTrue(hasElectronics);
        assertTrue(hasBooks);
    }

    @Test
    void testUpdateGroupByChangingName() {
        //Given
        Group saved = groupRepository.saveAndFlush(group);
        //When
        saved.setName("ElectronicsAndGadgets");
        groupRepository.saveAndFlush(saved);
        //Then
        Group reloaded = groupRepository.findById(saved.getId()).orElseThrow();
        assertEquals("ElectronicsAndGadgets", reloaded.getName());
    }

    @Test
    void testDeleteGroupWithoutProducts() {
        //Given
        Group saved = groupRepository.saveAndFlush(group);
        //When
        groupRepository.delete(saved);
        groupRepository.flush();
        //Then
        assertTrue(groupRepository.findById(saved.getId()).isEmpty());
    }

    @Test
    void testDeleteGroupWithProductsShouldThrowExceptionAndNotDeleteProducts() {
        //Given
        Group savedGroup = groupRepository.saveAndFlush(group);
        product1.setGroup(savedGroup);
        product2.setGroup(savedGroup);
        productRepository.saveAllAndFlush(List.of(product1, product2));
        //When & Then
        assertThrows(DataIntegrityViolationException.class, () -> {
            groupRepository.delete(savedGroup);
            groupRepository.flush();
        });
        //Then
        assertTrue(productRepository.findById(product1.getId()).isPresent());
        assertTrue(productRepository.findById(product2.getId()).isPresent());
    }

    @Test
    void testDeleteGroupAfterRemovingProductsFromGroupShouldKeepProducts() {
        //Given
        Group savedGroup = groupRepository.saveAndFlush(group);
        product1.setGroup(savedGroup);
        product2.setGroup(savedGroup);
        productRepository.saveAllAndFlush(List.of(product1, product2));
        //When
        product1.setGroup(null);
        product2.setGroup(null);
        productRepository.saveAllAndFlush(List.of(product1, product2));
        groupRepository.delete(savedGroup);
        groupRepository.flush();
        //Then
        assertTrue(groupRepository.findById(savedGroup.getId()).isEmpty());
        assertNull(productRepository.findById(product1.getId()).orElseThrow().getGroup());
        assertNull(productRepository.findById(product2.getId()).orElseThrow().getGroup());
    }
}
