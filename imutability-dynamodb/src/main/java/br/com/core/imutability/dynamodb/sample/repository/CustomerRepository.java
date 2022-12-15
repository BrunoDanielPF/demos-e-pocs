package br.com.core.imutability.dynamodb.sample.repository;

import br.com.core.imutability.dynamodb.sample.entity.Customer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

@Repository
public class CustomerRepository {

    private final DynamoDbTable<Customer> table;

    public CustomerRepository(DynamoDbEnhancedClient client) {
        table = client.table(Customer.class.getSimpleName(), TableSchema.fromImmutableClass(Customer.class));
    }

    public void create(Customer data) {
        table.putItem(data);
    }

    public List<Customer> getAll() {
        return table.scan().items().stream().toList();
    }
}
