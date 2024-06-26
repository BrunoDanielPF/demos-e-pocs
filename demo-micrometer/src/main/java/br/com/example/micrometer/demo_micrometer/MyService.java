package br.com.example.micrometer.demo_micrometer;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    private final ObservationRegistry observationRegistry;

    public MyService(ObservationRegistry observationRegistry) {
        this.observationRegistry = observationRegistry;
    }

    public void performAction1() {
        Observation.createNotStarted("my_observation", observationRegistry)
                .observe(() -> {
                    System.out.println("Executando a ação observada!");
                });
    }
}
