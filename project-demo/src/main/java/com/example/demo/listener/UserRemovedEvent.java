package com.example.demo.listener;

public class UserRemovedEvent {

    public String name;

    UserRemovedEvent(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }
}
