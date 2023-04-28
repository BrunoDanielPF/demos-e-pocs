package com.example.sqs.demosqspoc.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.example.sqs.demosqspoc.interceptor.SQSHandlerInterceptor;
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

    public void publishEvent(String message) {

        SendMessageResult amazonSQSClientBuilder = AmazonSQSClientBuilder.standard()
                .withRequestHandlers(new SQSHandlerInterceptor())
                .build()
                .sendMessage("http://localhost:4566/000000000000/test_queue", message);
//        MessageChannel messageChannel
//                = new QueueMessageChannel(amazonSqs, "http://localhost:4566/000000000000/test_queue");
//
//        Message<String> msg = MessageBuilder.withPayload(message)
//                .setHeader("sender", "app1")
//                .setHeaderIfAbsent("country", "AE")
//                .build();
//
//        long waitTimeoutMillis = 5000;
//        messageChannel.send(msg,waitTimeoutMillis);
////        LOGGER.info("Generating event : {}", message);
//        SendMessageRequest sendMessageRequest = null;
//        try {
//
//        sendMessageRequest = new SendMessageRequest().withQueueUrl("http://localhost:4566/000000000000/sample-queue.fifo")
//                    .withMessageBody(objectMapper.writeValueAsString(message))
//                    .withMessageGroupId("Sample Message")
//                    .withMessageDeduplicationId(UUID.randomUUID().toString());
//            amazonSQS.sendMessage(sendMessageRequest);
//            LOGGER.info("Event has been published in SQS.");
//        } catch (JsonProcessingException e) {
//            LOGGER.error("JsonProcessingException e : {} and stacktrace : {}", e.getMessage(), e);
//        } catch (Exception e) {
//            LOGGER.error("Exception ocurred while pushing event to sqs : {} and stacktrace ; {}", e.getMessage(), e);
//        }

    }
}
