package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserMapper {
    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.id(),
                userDto.email(),
                userDto.passwordHash(),
                userDto.isBlocked(),
                userDto.sessionKey(),
                userDto.sessionKeyExpiresAt()

        );
    }
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPasswordHash(),
                user.isBlocked(),
                user.getSessionKey(),
                user.getSessionKeyExpiresAt()
        );
    }

    public static List<UserDto> mapToUserDto(List<User> users) {
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }
}
