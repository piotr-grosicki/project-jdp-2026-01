package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    private User userBeforeSaving;
    private User userSaved;

    @BeforeEach
    void setUp() {
        userBeforeSaving = User.builder()
                .email("test@example.com")
                .passwordHash("pass1234")
                .blocked(false)
                .build();

        userSaved = userRepository.save(userBeforeSaving);
    }

    @Test
    void testCreateAndReadUser() {
        assertNotNull(userSaved);
        assertEquals(userBeforeSaving.getEmail(), userSaved.getEmail());
        assertEquals(userBeforeSaving.getPasswordHash(), userSaved.getPasswordHash());
        assertFalse(userSaved.isBlocked());
    }

    @Test
    void testUpdateUser() {
        userSaved.setBlocked(true);
        userRepository.save(userSaved);
        User updatedUser = userRepository.findById(userSaved.getId()).orElseThrow();
        assertTrue(updatedUser.isBlocked());
    }

    @Test
    void testDeleteUser() {
        userRepository.deleteById(userSaved.getId());
        Optional<User> deletedUser = userRepository.findById(userSaved.getId());
        assertTrue(deletedUser.isEmpty());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
    }
}
