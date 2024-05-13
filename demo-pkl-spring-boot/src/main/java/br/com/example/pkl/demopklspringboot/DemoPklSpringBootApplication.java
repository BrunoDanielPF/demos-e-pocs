package br.com.example.pkl.demopklspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ConfigurationPropertiesScan
@RestController
public class DemoPklSpringBootApplication {

	public static void main(String[] args) {
		new SpringApplication(DemoPklSpringBootApplication.class).run(args);
	}


	@Bean
	@SuppressWarnings("unused")
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			var config = ctx.getBean(AppConfig.class);
			System.out.println(config.toString());
		};
	}

	@Autowired
	AppConfig appConfig;

	@GetMapping("/teste")
	public ResponseEntity<?> teste() {
		return ResponseEntity.ok(appConfig);
	}
}
