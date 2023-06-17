package com.example.sqs.demosqspoc.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessageChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SQSEventPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQSEventPublisher.class);

    @Autowired
    private AmazonSQSAsync amazonSqs;

    @Autowired
    private ObjectMapper objectMapper;

    public boolean publishEvent(String message) {
        try {
            MessageChannel messageChannel
                    = new QueueMessageChannel(amazonSqs, "http://localhost:4566/000000000000/test_queue");

            Message<String> msg = MessageBuilder.withPayload(message)
                    .setHeader("sender", "app1")
                    .setHeaderIfAbsent("country", "Brazilian")
                    .build();
            long waitTimeoutMillis = 5000;
            messageChannel.send(msg, waitTimeoutMillis);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}


