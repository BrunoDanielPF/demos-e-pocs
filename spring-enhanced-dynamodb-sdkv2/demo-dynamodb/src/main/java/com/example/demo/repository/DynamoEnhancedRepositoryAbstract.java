package com.example.demo.repository;

import com.example.demo.repository.annotation.DynamoEnhancedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.DescribeTableEnhancedResponse;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public abstract class DynamoEnhancedRepositoryAbstract<E> {


    private DynamoDbTable<E> table;
    @Autowired
    private DynamoDbEnhancedClient client;

    @PostConstruct
    public void setupClient() {
        DynamoEnhancedRepository annotation = this.getClass().getDeclaredAnnotation(DynamoEnhancedRepository.class);
        Class<E> aClass = (Class<E>) annotation.DynamoBeanClass();
        this.table = client.table(annotation.tableName(), TableSchema.fromBean(aClass));
    }

    public Optional<E> getById(String id) {
        QueryConditional queryConditional = QueryConditional.keyEqualTo(keyBuilderId(id));
        return table.query(request -> request.queryConditional(queryConditional).build()).items().stream().findFirst();
    }

    public Set<E> getAll() {
        return table.scan().items().stream().collect(Collectors.toSet());
    }

    public void create(E entity) {
        table.putItem(request -> request.item(entity));
    }

    public E update(E entity) {
        return table.updateItem(request -> request.item(entity));
    }

    public void delete(String id) {
        table.deleteItem(request -> request.key(keyBuilderId(id)));
    }

    public DescribeTableEnhancedResponse describeTable() {
        return table.describeTable();
    }

    private Key keyBuilderId(String key) {
        return Key.builder().partitionValue(key).build();
    }

    private Key keyBuilderId(String key, Number composeKey) {
        return Key.builder().partitionValue(key).sortValue(composeKey).build();
    }

}
