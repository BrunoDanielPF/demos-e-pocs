package com.example.demo.configs;

import com.example.demo.entity.User;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.ResourceInUseException;

import javax.annotation.PostConstruct;

@Configuration
public class LocalStackConfigure {

    private final DynamoDbEnhancedClient dynamoEnhancedDbClient;

    public LocalStackConfigure(DynamoDbEnhancedClient dynamoEnhancedDbClient) {
        this.dynamoEnhancedDbClient = dynamoEnhancedDbClient;
    }

    @PostConstruct
    void init() {
        initDynamoEnhanced();
    }

    void initDynamoEnhanced() {
        try {
            dynamoEnhancedDbClient.table(User.class.getSimpleName(), TableSchema.fromBean(User.class)).createTable();
        } catch (ResourceInUseException ignored) {

        }
    }
}
