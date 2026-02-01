package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.UserNotAuthenticatedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserAuthenticator {

    public void authenticate(User user, String userKeyToCheck) throws UserNotAuthenticatedException {
        if (user == null || user.getSessionKey() == null || user.getSessionKeyExpiresAt() == null
                || !user.getSessionKey().equals(userKeyToCheck)
                || LocalDateTime.now().isAfter(user.getSessionKeyExpiresAt())
                || user.isBlocked()) {
            throw new UserNotAuthenticatedException();
        }
    }
}
