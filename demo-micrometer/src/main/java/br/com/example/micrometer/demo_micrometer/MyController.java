package br.com.example.micrometer.demo_micrometer;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final ObservationRegistry observationRegistry;

    public MyController(ObservationRegistry observationRegistry) {
        this.observationRegistry = observationRegistry;
    }

    @GetMapping("/observe")
    public String observeAction() {
        return Observation.createNotStarted("http_request", observationRegistry)
                .observe(() ->
                     "Ação observada!"
                );
    }

    @GetMapping("/observe2")
    public String observeAction2() {
        return Observation.createNotStarted("my_observation_histogram", observationRegistry)
                .lowCardinalityKeyValue("key", "value")
                .observe(() ->
                        "Executando a ação observada!"
                );
    }


}