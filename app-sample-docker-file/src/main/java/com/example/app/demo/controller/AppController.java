package com.example.app.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/teste")
    public String getHelloWorld() {
        return " Hello World ";
    }
}
