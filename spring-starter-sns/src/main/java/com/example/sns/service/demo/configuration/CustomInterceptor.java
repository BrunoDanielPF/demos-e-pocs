package com.example.sns.service.demo.configuration;

import software.amazon.awssdk.core.SdkRequest;
import software.amazon.awssdk.core.interceptor.Context;
import software.amazon.awssdk.core.interceptor.ExecutionAttribute;
import software.amazon.awssdk.core.interceptor.ExecutionAttributes;
import software.amazon.awssdk.core.interceptor.ExecutionInterceptor;
import software.amazon.awssdk.http.SdkHttpRequest;
import software.amazon.awssdk.http.SdkHttpResponse;

import java.util.UUID;

public class CustomInterceptor implements ExecutionInterceptor {


    @Override
    public SdkHttpRequest modifyHttpRequest(Context.ModifyHttpRequest context, ExecutionAttributes executionAttributes) {
        SdkHttpRequest originalRequest = context.httpRequest();
        // Crie uma cópia modificada do SdkHttpRequest
        SdkHttpRequest.Builder modifiedRequestBuilder = originalRequest.toBuilder();
        // Adicione os headers personalizados
        modifiedRequestBuilder.putHeader("X-Correlation-ID", generateCorrelationId());
        // Construa o novo SdkHttpRequest modificado
        return modifiedRequestBuilder.build();
    }

    @Override
    public SdkHttpResponse modifyHttpResponse(Context.ModifyHttpResponse context, ExecutionAttributes executionAttributes) {
        SdkHttpResponse sdkHttpResponse = context.httpResponse();
        // Crie uma cópia modificada do SdkHttpRequest
        SdkHttpResponse.Builder modifiedResponseBuilder = sdkHttpResponse.toBuilder();
        // Adicione os headers personalizados
        modifiedResponseBuilder.putHeader("X-Correlation-ID", generateCorrelationId());
        // Construa o novo SdkHttpRequest modificado
        return modifiedResponseBuilder.build();
    }


    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
