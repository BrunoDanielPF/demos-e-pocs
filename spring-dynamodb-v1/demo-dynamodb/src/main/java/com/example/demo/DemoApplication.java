package com.example.demo;

import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.example.demo.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//    public void onapplicationready() {
//        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(Person.class)
//                .withProvisionedThroughput(new ProvisionedThroughput(1l, 1l));
//        TableUtils.createTableIfNotExists(amazonDynamoDB, createTableRequest);
//    }
}
