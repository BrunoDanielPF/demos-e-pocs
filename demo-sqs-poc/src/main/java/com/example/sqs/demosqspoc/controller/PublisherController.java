package com.example.sqs.demosqspoc.controller;

import com.example.sqs.demosqspoc.service.SQSEventPublisher;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {


    private final SQSEventPublisher sqsEventPublisher;

    public PublisherController(SQSEventPublisher sqsEventPublisher) {
        this.sqsEventPublisher = sqsEventPublisher;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity sendMessage(@RequestBody String jsonNode) {
        sqsEventPublisher.publishEvent(jsonNode);
        return ResponseEntity.ok().build();
    }
}
