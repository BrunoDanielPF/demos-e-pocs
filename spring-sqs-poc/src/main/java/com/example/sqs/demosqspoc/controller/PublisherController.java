package com.example.sqs.demosqspoc.controller;

import com.example.sqs.demosqspoc.service.SQSEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PublisherController {

    @Autowired
    private SQSEventPublisher sqsEventPublisher;

    @PostMapping("/sendMessage")
    public ResponseEntity sendMessage(@RequestBody String data) {
        boolean event = sqsEventPublisher.publishEvent(data);
        return ResponseEntity.ok().body(event);
    }

    @GetMapping("/teste")
    public String teste() {
        return "TESTE OK";
    }
}
