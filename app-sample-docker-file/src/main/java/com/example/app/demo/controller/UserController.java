package com.example.app.demo.controller;

import com.example.app.demo.model.User;
import com.example.app.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(repository.save(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(repository.findAll());
    }
}
