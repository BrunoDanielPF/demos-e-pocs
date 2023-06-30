package br.com.example.metric.demo;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Controller {

    final Environment environment;

    public Controller(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("{value}")
    public String x(@PathVariable String value) {
        return Optional.ofNullable(environment.getProperty(value)).orElse("not found propertied");
    }
}
