package com.example.sqs.demosqspoc.ThreadLocal;

public class TraceId {

    private static ThreadLocal<String> traceContext = new ThreadLocal<>();

    public static String getContext() {
        return traceContext.get();
    }

    public static void setTraceContext(String value) {
        traceContext.set(value);
    }
}
