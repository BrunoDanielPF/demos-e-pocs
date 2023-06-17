package com.example.demo.controller;

import com.example.demo.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

import java.io.IOException;

@RestController
@RequestMapping("v1")
public class S3Controller {

    private final S3Service service;

    public S3Controller(S3Service service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity listBucketsResponse() {
        return ResponseEntity.ok().body(service.listBuckets());
    }

    @PostMapping("/{key}")
    public ResponseEntity  uploudObject(@PathVariable String key) throws IOException {
        return ResponseEntity.ok().body(service.uploudObject(key));
    }
}

