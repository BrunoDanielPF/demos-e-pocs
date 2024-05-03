package br.com.example.producer;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoProducerSqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProducerSqsApplication.class, args);
	}


	@RestController
	static
	class
	ControllerSqs {
		@Value(value = "${queue.url}")
		String URL;
		@Autowired
		SqsTemplate sqsTemplate;
		@PostMapping("{message}")
		public String sendMessage(
				@PathVariable(name = "message") String message) {
			sqsTemplate.send(URL, message);
			return "Message send with success!";
		}
	}
}
