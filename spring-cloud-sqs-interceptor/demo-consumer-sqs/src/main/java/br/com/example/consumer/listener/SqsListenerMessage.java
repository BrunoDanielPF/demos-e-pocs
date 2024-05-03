package br.com.example.consumer.listener;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.model.Message;

@Component
public class SqsListenerMessage {

    static final Logger LOGGER = LoggerFactory.getLogger(SqsListener.class.getSimpleName());
    @SqsListener({"${queue.url}"})
    public void listenTwoQueues(Message message) {
        LOGGER.atInfo().addArgument(message);
    }

}
