package com.example.sqs.demosqspoc.interceptor;

import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.handlers.RequestHandler2;
import com.example.sqs.demosqspoc.ThreadLocal.TraceId;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SQSHandlerInterceptor extends RequestHandler2 {


    @Override
    public void beforeRequest(Request<?> request) {
        Map<String, String> headers = request.getHeaders();
        for (Map.Entry<String, String> headersKey : headers.entrySet()) {
            String key = headersKey.getKey();
            if (key.equals("TracingID")) {
                TraceId.setTraceContext(headersKey.getValue());
            }else {
                TraceId.setTraceContext(generateUUID());
            }
        }
        super.beforeRequest(request);
    }



    @Override
    public void afterResponse(Request<?> request, Response<?> response) {

        super.afterResponse(request, response);
    }

    private static String generateUUID() {
        return UUID.randomUUID().toString();
    }

//    @Override
//    public void beforeRequest(Request<?> request) {
//
//
//        super.beforeRequest(request);
//    }

//    @Override
//    public void beforeRequest(Request<?> request) {

//    }

}
