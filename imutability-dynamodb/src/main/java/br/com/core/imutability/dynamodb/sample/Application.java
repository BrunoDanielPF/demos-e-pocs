package br.com.core.imutability.dynamodb.sample;

import br.com.core.imutability.dynamodb.sample.entity.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
