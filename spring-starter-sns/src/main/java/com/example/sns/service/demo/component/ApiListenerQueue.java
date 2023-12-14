package com.example.sns.service.demo.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sns.annotation.handlers.NotificationMessage;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApiListenerQueue {

    Logger logger = LoggerFactory.getLogger(ApiListenerQueue.class);
    @SqsListener("my-queue")
    public void processMessage(@NotificationMessage String message, @Headers Map<String, Object> headers) {
        // Faça o que quiser com a mensagem recebidadsadasd
        headers.containsValue("correlation-id");
        logger.info("Mensagem recebida da fila SQS: ", message);
    }
}
