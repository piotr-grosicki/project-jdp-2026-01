package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.OrderNotFoundException;
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

    public User saveUser(final User user) {
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
        return userRepository.save(user);
    }

    public void blockUser(final Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        foundUser.setBlocked(true);
        userRepository.save(foundUser);
    }

    public void deleteUser(final Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        userRepository.deleteById(id);
    }
}
