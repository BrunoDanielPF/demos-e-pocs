package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private final PersonService service;

    public UserController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    ResponseEntity getById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping
    ResponseEntity<Iterator<User>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    ResponseEntity create(@RequestBody User user) {
        User newUser = service.create(user);
        return ResponseEntity.ok().body(newUser);
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
