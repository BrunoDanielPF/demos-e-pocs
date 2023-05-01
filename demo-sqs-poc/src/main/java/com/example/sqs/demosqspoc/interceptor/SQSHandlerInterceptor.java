package com.example.sqs.demosqspoc.interceptor;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.handlers.RequestHandler2;
import com.example.sqs.demosqspoc.ThreadLocal.TraceId;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class SQSHandlerInterceptor extends RequestHandler2 {

    @Override
    public AmazonWebServiceRequest beforeExecution(AmazonWebServiceRequest request) {
        Map<String, String> headers = request.getCustomRequestHeaders();
        Set<Map.Entry<String, String>> entrySet = headers.entrySet();
        for (Map.Entry<String, String> headersKey : entrySet) {
            String key = headersKey.getKey();
            if (key.equals("TracingID")) {
                TraceId.setTraceContext(headersKey.getValue());
            }
        }
        if (!headers.containsKey("TracingID")) {
            headers.putIfAbsent("TracingID", Optional.ofNullable(TraceId.getContext()).orElse(UUID.randomUUID().toString()));
        }
        return super.beforeExecution(request);
    }


    @Override
    public void afterResponse(Request<?> request, Response<?> response) {

        super.afterResponse(request, response);
    }

    private static String generateUUID() {
        return UUID.randomUUID().toString();
    }

}
