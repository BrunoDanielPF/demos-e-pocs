package com.example.sns.service.demo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.SdkRequest;
import software.amazon.awssdk.core.interceptor.Context;
import software.amazon.awssdk.core.interceptor.ExecutionAttribute;
import software.amazon.awssdk.core.interceptor.ExecutionAttributes;
import software.amazon.awssdk.core.interceptor.ExecutionInterceptor;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class CustomInterceptor implements ExecutionInterceptor {

    Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);
    public static final ExecutionAttribute<String> CORRELATION_ID = new ExecutionAttribute<>("correlation-id");

    @Override
    public void beforeExecution(Context.BeforeExecution context, ExecutionAttributes executionAttributes) {
        try {
            executionAttributes.putAttribute(CORRELATION_ID, generateUUID());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        ExecutionInterceptor.super.beforeExecution(context, executionAttributes);
    }

    @Override
    public SdkRequest modifyRequest(Context.ModifyRequest context, ExecutionAttributes executionAttributes) {
        SdkRequest request = context.request();
        if (request instanceof PublishRequest publishRequest) {
            Map<String, MessageAttributeValue> customHeadersAttributes = new HashMap<>();
            try {
                Map<String, MessageAttributeValue> stringMessageAttributeValueMap = publishRequest.messageAttributes();
                if (!stringMessageAttributeValueMap.containsKey("Correlation-id")) {
                    customHeadersAttributes.put("Correlation-id", MessageAttributeValue.builder()
                            .dataType("String")
                            .stringValue(UUID.randomUUID().toString())
                            .build());
                }
                customHeadersAttributes.putAll(stringMessageAttributeValueMap);

                return publishRequest.toBuilder().messageAttributes(customHeadersAttributes).build();
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        if (!(request instanceof SendMessageRequest)) {
            return context.request();
        }
        return null;
    }

    @Override
    public Optional<InputStream> modifyHttpResponseContent(Context.ModifyHttpResponse context, ExecutionAttributes executionAttributes) {
        return ExecutionInterceptor.super.modifyHttpResponseContent(context, executionAttributes);
    }

    private static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
