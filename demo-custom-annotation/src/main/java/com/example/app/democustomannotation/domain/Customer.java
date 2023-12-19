package com.example.app.democustomannotation.domain;

import com.example.app.democustomannotation.validator.ValidaEmail;


public class Customer {
    @ValidaEmail(message = "email errado burro!")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
