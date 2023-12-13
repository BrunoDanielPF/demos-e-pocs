package com.example.sns.service.demo;

import com.example.sns.service.demo.configuration.CustomInterceptor;
import io.awspring.cloud.sns.annotation.endpoint.NotificationMessageMapping;
import io.awspring.cloud.sns.annotation.handlers.NotificationMessage;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.SnsClientBuilder;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;

import java.net.URI;
import java.util.logging.Logger;

@SpringBootApplication
public class DemoStarterSnsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoStarterSnsApplication.class, args);
    }

    @Configuration
    static class CustomConfigurationSNS {

        @Bean
        public SnsClient snsClientBuilder() {
            SnsClientBuilder builder = SnsClient.builder();
            builder.credentialsProvider(DefaultCredentialsProvider.create());
            builder.endpointOverride(URI.create("http://localhost:4566/"));
            return builder
                    .overrideConfiguration(ClientOverrideConfiguration.builder()
                    .addExecutionInterceptor(new CustomInterceptor()).build())
                    .build();
        }
    }

    @RestController
    static class ControllerSns {
        Logger logger = Logger.getLogger(ControllerSns.class.getName());
        private final SnsService service;

        public ControllerSns(SnsService service) {
            this.service = service;
        }

        @GetMapping("/sns/{message}")
        public ResponseEntity<Boolean> sendMessage(@PathVariable String message) {
            boolean response = service.sendMessage(message);
            service.subscribeSns();
            return ResponseEntity.ok(response);
        }


        @NotificationMessageMapping(path = "/sns/receive")
        public void notificationMessageHandle(@NotificationMessage String message) {
            logger.info(String.format("Mensagem enviada do SNS: {0}", message));
        }
    }

    @Service
    static class SnsService {

        static final String TOPIC_NAME = "topic-demo";
        private final SnsTemplate snsTemplate;
        private final SnsClient client;

        public SnsService(SnsTemplate snsTemplate, SnsClient client) {
            this.snsTemplate = snsTemplate;
            this.client = client;
        }

        public boolean sendMessage(String message) {
            try {
                snsTemplate.convertAndSend(TOPIC_NAME, message);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public int subscribeSns() {
            SubscribeRequest subscribeRequest = SubscribeRequest.builder().endpoint("http://host.docker.internal:4566/sns/receive").protocol("http").topicArn("arn:aws:sns:us-east-2:000000000000:topic-demo").build();
            SubscribeResponse subscribe = client.subscribe(subscribeRequest);
            return subscribe.sdkHttpResponse().statusCode();
        }
    }

    record MessageModel(String message) {
    }
}
