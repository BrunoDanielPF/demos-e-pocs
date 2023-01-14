package com.example.demo.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;

public class PrimaryKey {

    @DynamoDBHashKey
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}