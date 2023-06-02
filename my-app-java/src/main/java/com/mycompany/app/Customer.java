package com.mycompany.app;

public class Customer {

    private String idade;

    static class Builder {

        private String idade;

        public Builder idade(String idade) {
            this.idade = idade;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    public Customer(Builder builder){
        this.idade= builder.idade;
    }
}
