package br.com.core.imutability.dynamodb.sample.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbImmutable(builder = Customer.Builder.class)
public class Customer {

    private final String accountId;
    private final int subId;
    private final String name;

    public Customer(Builder b) {
        this.accountId = b.accountId;
        this.subId = b.subId;
        this.name = b.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    @DynamoDbPartitionKey
    public String accountId() {
        return this.accountId;
    }

    @DynamoDbSortKey
    public int subId() {
        return this.subId;
    }

    public String name() {
        return this.name;
    }

    public static final class Builder {
        private String accountId;
        private int subId;
        private String name;

        private Builder() {
        }

        public Builder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder subId(int subId) {
            this.subId = subId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
