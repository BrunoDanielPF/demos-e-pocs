package br.com.core.imutability.dynamodb.sample.config;

import br.com.core.imutability.dynamodb.sample.entity.Customer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.ResourceInUseException;

@Configuration
public class TableLocalConfig {

    private static final Logger log = LoggerFactory.getLogger(TableLocalConfig.class);
    private final DynamoDbEnhancedClient dynamoEnhancedDbClient;

    public TableLocalConfig(DynamoDbEnhancedClient dynamoEnhancedDbClient) {
        this.dynamoEnhancedDbClient = dynamoEnhancedDbClient;
    }

    @PostConstruct
    public void initDynamoEnhanced() {
        try {
            dynamoEnhancedDbClient.table(Customer.class.getSimpleName(), TableSchema.fromImmutableClass(Customer.class)).createTable();
            log.info("criando tabela de usuario");
        } catch (ResourceInUseException ignored) {
            log.info("table exists, continue execution ...");
        }
    }
}
