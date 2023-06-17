package com.example.aop.demoaop;

import com.example.aop.demoaop.services.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AspectLogAopApplication {
	public static void main(String[] args) {
		SpringApplication.run(AspectLogAopApplication.class, args);
	}

}
