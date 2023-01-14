package com.example.demo.configs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@ConditionalOnProperty(value = "application.localstack", havingValue = "true")
@Configuration
class AwsConfigLocal {

    private static final URI URI_LOCALSTACK = URI.create("http://localhost:4566");
    private static final StaticCredentialsProvider STATIC_CREDENTIALS = StaticCredentialsProvider.create(AwsBasicCredentials.create("none", "none"));

    @Bean
    public DynamoDbClient  dynamoDbClient() {
        return DynamoDbClient.builder()
                .credentialsProvider(STATIC_CREDENTIALS)
                .endpointOverride(URI_LOCALSTACK)
                .region(Region.SA_EAST_1)
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient client) {
       return DynamoDbEnhancedClient.builder().dynamoDbClient(client).build();
    }

}
