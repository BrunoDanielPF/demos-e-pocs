package br.com.example.consumer.thread;

import org.slf4j.MDC;

public class Correlation {

    public static final String CORRELATION_ID = "correlationID";
    public static void set(String correlationValue) {
        MDC.put(CORRELATION_ID, correlationValue);
    }

    public static String get() {
        return MDC.get(CORRELATION_ID);
    }
}
