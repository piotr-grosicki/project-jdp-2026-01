package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.CreateUserDto;
import com.kodilla.ecommercee.dto.UpdateUserDto;
import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.isBlocked(),
                user.getSessionKey(),
                user.getSessionKeyExpiresAt()
        );
    }

    public User mapToUser(CreateUserDto createUserDto) {
        return User.builder()
                .email(createUserDto.email())
                .passwordHash(createUserDto.password())
                .blocked(false)
                .build();
    }

    public User mapToUser(UpdateUserDto updateUserDto, Long id) {
        return User.builder()
                .id(id)
                .email(updateUserDto.email())
                .passwordHash(updateUserDto.password())
                .blocked(updateUserDto.is_blocked())
                .build();
    }
}