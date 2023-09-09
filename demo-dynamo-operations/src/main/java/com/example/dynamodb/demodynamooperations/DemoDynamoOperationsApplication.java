package com.example.dynamodb.demodynamooperations;

import com.example.dynamodb.demodynamooperations.operations.batch.BatchOperationsDynamoDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@SpringBootApplication
public class DemoDynamoOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDynamoOperationsApplication.class, args);
	}

}
