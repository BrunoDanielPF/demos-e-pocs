package br.com.example.consumer.interceptor;

import br.com.example.consumer.thread.Correlation;
import io.awspring.cloud.sqs.listener.interceptor.MessageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Collection;
import java.util.Objects;

import static br.com.example.consumer.thread.Correlation.CORRELATION_ID;

@Configuration
public class MessageInterceptorImpl  {

    @Bean
    public MessageInterceptor<Object> messageInterceptor() {

        return new MessageInterceptor<Object>() {
            @Override
            public Message<Object> intercept(Message<Object> message) {
                MessageHeaders headers = message.getHeaders();
                Object headerCorrelation = headers.get(CORRELATION_ID);
                if (!Objects.isNull(headerCorrelation) && Correlation.get() == null)
                    Correlation.set((String) headerCorrelation);
                return MessageInterceptor.super.intercept(message);
            }
        };
    }
}
