package br.com.example.metric.demo.resources;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Timed(value="test.get.say.hello",description="time to recover",percentiles={0.5,0.9})
    @GetMapping
    public String sayHello() {
        String status = "ok";
        return status;
    }
}
