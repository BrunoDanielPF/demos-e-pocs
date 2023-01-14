package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Iterator;

@Service
public class PersonRepository {

    private final DynamoDbTable<User> table;

    public PersonRepository(DynamoDbEnhancedClient client) {
        table = client.table(User.class.getSimpleName(), TableSchema.fromBean(User.class));
    }

    public User getById(String id) {
        return table.getItem(getBuild(id));
    }


    private Key getKeyBuild(String key) {
        return Key.builder().partitionValue(key).build();
    }

    public Iterator<User> getAll() {
        return table.scan().items().iterator();
    }

    public User create(User user) {

        return table.updateItem(user);
    }

    public User update(User user) {
        return table.updateItem(user);
    }

    public void delete(String id) {
        table.deleteItem(getBuild(id));
    }

    public DynamoDbTable<User> getTable() {
        return table;
    }

    private Key getBuild(String id) {
        return getKeyBuild(id);
    }
}
