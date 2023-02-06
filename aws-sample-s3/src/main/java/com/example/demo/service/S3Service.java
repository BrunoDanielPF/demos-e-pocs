package com.example.demo.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client client;

    public S3Service(S3Client client) {
        this.client = client;
    }

    public List<S3Object> listBuckets() {
        List<Bucket> list = new ArrayList<>();
        ListObjectsRequest listObjects = ListObjectsRequest
                .builder()
                .bucket("my-test-bucket")
                .build();
        ListObjectsResponse listBucketsResponse = client.listObjects(listObjects);
        List<S3Object> contents = listBucketsResponse.contents();
        return contents;
    }

    public String uploudObject(String key) throws IOException {

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket("my-test-bucket-2")
                .key(key)
                .build();
        client.putObject(objectRequest, RequestBody.fromString(UUID.randomUUID().toString()));

        return "CREATED OBJECT";
    }

    private static ByteBuffer getRandomByteBuffer(int size) throws IOException {
        byte[] b = new byte[size];
        new Random().nextBytes(b);
        return ByteBuffer.wrap(b);
    }
}
