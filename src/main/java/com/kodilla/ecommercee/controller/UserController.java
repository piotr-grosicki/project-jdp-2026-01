package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CreateUpdateUserDto;
import com.kodilla.ecommercee.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    //private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(List.of(new UserDto(
                1L,
                "email@example.com",
                false,
                "session_key",
                LocalDateTime.now())));
    };

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid CreateUpdateUserDto createUpdateUserDto) {
        return ResponseEntity.ok(new UserDto(1L,
                createUpdateUserDto.email(),
                createUpdateUserDto.is_blocked(),
                createUpdateUserDto.session_key(),
                createUpdateUserDto.session_key_expires_at()
        ));
    };

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(new UserDto(id,
                "email@example.com",
                false,
                "session_key",
                LocalDateTime.now()
        ));
    };

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid CreateUpdateUserDto createUpdateUserDto) {
        return ResponseEntity.ok(new UserDto(id,
                createUpdateUserDto.email(),
                createUpdateUserDto.is_blocked(),
                createUpdateUserDto.session_key(),
                createUpdateUserDto.session_key_expires_at()
        ));
    };
}

