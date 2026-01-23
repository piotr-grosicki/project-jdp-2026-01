package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    private User createUser() {
        User user = new User(
                1L,
                "mail@example.com",
                "hash",
                false,
                "session-key",
                LocalDateTime.now().plusHours(1));
        return userRepository.save(user);
    };

    private void cleanUpUser(Long id) {
        userRepository.deleteById(id);
    };

    @Test
    public void testAddUserAndGetUser() {
        //given
        User user = createUser();
        //when
        Optional<User> userOptional = userRepository.findById(user.getId());
        //then
        assertTrue(userOptional.isPresent());
        User userSaved = userOptional.get();
        assertEquals(user.getId(), userSaved.getId());
        assertEquals(user.getEmail(), userSaved.getEmail());
        assertEquals(user.getPasswordHash(), userSaved.getPasswordHash());
        assertEquals(user.getSessionKey(), userSaved.getSessionKey());
        assertEquals(user.getSessionKeyExpiresAt().toString().substring(0, 19),
                userSaved.getSessionKeyExpiresAt().toString().substring(0, 19));
        //cleanup
        cleanUpUser(user.getId());
    };

    @Test
    public void testUpdateUser() {
        //given
        User user = createUser();
        //when
        user.setEmail("newEmail@example.com");
        User userUpdated = userRepository.save(user);
        //then
        assertNotNull(userUpdated);
        assertEquals(user.getEmail(), userUpdated.getEmail());
        //cleanup
        cleanUpUser(user.getId());
    };

    @Test
    public void testDeleteUser() {
        //given
        User user = createUser();
        //when
        userRepository.deleteById(user.getId());
        Optional<User> userDeleted = userRepository.findById(user.getId());
        //then
        assertTrue(userDeleted.isEmpty());
    };
}
