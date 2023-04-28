package com.example.sqs.demosqspoc.interceptor;

import com.example.sqs.demosqspoc.ThreadLocal.TraceId;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

@Component
public class CorrelationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
            Iterator<String> stringIterator = headerNames.asIterator();
            while (stringIterator.hasNext()){
                String header = stringIterator.next();
                if (header.equals("TraceID")){
                    String headerValue = httpRequest.getHeader(header);
                    TraceId.setTraceContext(headerValue);
                }
            }

    }
}
