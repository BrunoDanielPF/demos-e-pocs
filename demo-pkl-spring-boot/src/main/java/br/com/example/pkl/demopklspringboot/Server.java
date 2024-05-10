package br.com.example.pkl.demopklspringboot;

import org.springframework.stereotype.Component;

@Component
public class Server {
    private final AppConfig.Server config;

    public Server(AppConfig.Server config) {
        this.config = config;
    }

    public AppConfig.Server getConfig() {
        return config;
    }
}
