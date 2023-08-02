package com.example.demo.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerImpl {

    @EventListener
    ReturnedEvent handleUserRemovedEvent(UserRemovedEvent event) {
        System.out.println(String.format("User removed (@EventListerner): %s", event.getName()));
        // Spring will send ReturnedEvent as a new event
        return new ReturnedEvent();
    }

    // Listener to receive the event returned by Spring
    @EventListener
    void handleReturnedEvent(ReturnedEvent event) {
        System.out.println("ReturnedEvent received.");
    }

    @EventListener(condition = "#event.name eq 'reflectoring'")
    void handleConditionalListener(UserRemovedEvent event) {
        System.out.println(String.format("User removed (Conditional): %s", event.getName()));
    }

}