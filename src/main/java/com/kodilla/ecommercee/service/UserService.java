package com.kodilla.ecommercee.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.kodilla.ecommercee.controller.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private String generatePasswordHash(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public User saveUser(final User user) {
        String password = user.getPasswordHash();
        user.setPasswordHash(generatePasswordHash(password));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(final Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User updateUser(final Long id, final User user) {
        User foundUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        foundUser.setBlocked(user.isBlocked());
        foundUser.getOrders().clear();
        foundUser.getOrders().addAll(user.getOrders());
        String foundUserPasswordHash = foundUser.getPasswordHash();
        String newPasswordHash = generatePasswordHash(user.getPasswordHash());
        if(!foundUserPasswordHash.equals(newPasswordHash)) {
            foundUser.setPasswordHash(newPasswordHash);
        }
        return userRepository.save(foundUser);
    }

    public void blockUser(final Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        foundUser.setBlocked(true);
        userRepository.save(foundUser);
    }

    public void deleteUser(final Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
    }
}
