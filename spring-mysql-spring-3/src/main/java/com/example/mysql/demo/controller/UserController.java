package com.example.mysql.demo.controller;

import com.example.mysql.demo.model.User;
import com.example.mysql.demo.repository.UserRepository;
import jakarta.persistence.Column;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDto dto) {
        return ResponseEntity.ok(repository.save(User.builder()
                .email(dto.email())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .build()));
    }

    record UserDto(
            String firstName,
            String lastName,
            String email) {
    }
}
