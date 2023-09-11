package com.example.dynamodb.demodynamooperations.configuration;

import com.example.dynamodb.demodynamooperations.beans.Customer;
import com.example.dynamodb.demodynamooperations.beans.MovieActor;
import com.example.dynamodb.demodynamooperations.beans.ProductCatalog;
import com.example.dynamodb.demodynamooperations.operations.batch.BatchOperationsDynamoDb;
import com.example.dynamodb.demodynamooperations.operations.expressions.ScanExpressionOperationsDynamoDb;
import com.example.dynamodb.demodynamooperations.operations.transact.TransactionOperations;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ResourceInUseException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.logging.Logger;

import static software.amazon.awssdk.enhanced.dynamodb.internal.AttributeValues.numberValue;
import static software.amazon.awssdk.enhanced.dynamodb.internal.AttributeValues.stringValue;

@Service
public class CommandRunnerBeanConfiguration {

    @Autowired
    DynamoDbEnhancedClient dynamoDbEnhancedClient;
    public static Logger logger = Logger.getLogger(CommandLineRunner.class.getSimpleName());

    @PostConstruct
    public void setup() {

        DynamoDbTable<ProductCatalog> productCatalogDynamoDbTable = dynamoDbEnhancedClient.table(ProductCatalog.class.getSimpleName(), TableSchema.fromImmutableClass(ProductCatalog.class));
        DynamoDbTable<MovieActor> movieActorDynamoDbTable = dynamoDbEnhancedClient.table(MovieActor.class.getSimpleName(), TableSchema.fromBean(MovieActor.class));
        DynamoDbTable<Customer> customerDynamoDbTable = dynamoDbEnhancedClient.table(Customer.class.getSimpleName(), TableSchema.fromBean(Customer.class));

        try {
            createTables(productCatalogDynamoDbTable, movieActorDynamoDbTable, customerDynamoDbTable);
        } catch (ResourceInUseException resourceInUseException) {
            logger.info(() -> "resource in use continue execution");
        }
        createItemsMovieActor().forEach(movieActorDynamoDbTable::putItem);
        createItemsCustomer().forEach(customerDynamoDbTable::putItem);
        createProductCatalog().forEach(item -> productCatalogDynamoDbTable.putItem(putEnhanced -> putEnhanced.item(item)));


        Map<String, AttributeValue> expressionValue = Map.of(
                ":school_name1", stringValue("actingschool4"));

        Map<String, String> expressionNames = Map.of(
                "#actingschoolname", "actingschoolname"); // Mapeando o nome do atributo

        List<MovieActor> productCatalogs = movieActorDynamoDbTable.scan(ScanEnhancedRequest.builder()
                .filterExpression(Expression.builder()
                        .expression("#actingschoolname = :school_name1") // Usando o nome mapeado
                        .expressionValues(expressionValue)
                        .expressionNames(expressionNames) // Passando os nomes mapeados
                        .build())
                .build()).items().stream().toList();
        productCatalogs.forEach(System.out::println);


        BatchOperationsDynamoDb.batchWriteItemExample(dynamoDbEnhancedClient, productCatalogDynamoDbTable, movieActorDynamoDbTable);
        BatchOperationsDynamoDb.batchGetItemExample(dynamoDbEnhancedClient, customerDynamoDbTable, movieActorDynamoDbTable);
        ScanExpressionOperationsDynamoDb.scanAsync(productCatalogDynamoDbTable); //from product table
        ScanExpressionOperationsDynamoDb.query(movieActorDynamoDbTable);//from Movie actor table
        TransactionOperations.transactWriteItems(dynamoDbEnhancedClient, productCatalogDynamoDbTable, movieActorDynamoDbTable);
        clearTables(productCatalogDynamoDbTable, movieActorDynamoDbTable, customerDynamoDbTable);
        logger.info("FINALIZOU AS OPERACOES AO DYNAMO");
    }

    private static void clearTables(DynamoDbTable<ProductCatalog> productCatalogDynamoDbTable, DynamoDbTable<MovieActor> movieActorDynamoDbTable, DynamoDbTable<Customer> customerDynamoDbTable) {
        customerDynamoDbTable.deleteTable();
        productCatalogDynamoDbTable.deleteTable();
        movieActorDynamoDbTable.deleteTable();
    }

    private static void createTables(DynamoDbTable<ProductCatalog> productCatalogDynamoDbTable, DynamoDbTable<MovieActor> movieActorDynamoDbTable, DynamoDbTable<Customer> customerDynamoDbTable) {
        customerDynamoDbTable.createTable();
        productCatalogDynamoDbTable.createTable();
        movieActorDynamoDbTable.createTable();
    }

    public List<MovieActor> createItemsMovieActor() {
        return List.of(
                new MovieActor("movie01", "actor0", "actingaward0", 2001, "null"),
                new MovieActor("movie01", "actor1", "actingaward1", 2001, "actingschool1"),
                new MovieActor("movie01", "actor2", "actingaward2", 2001, "actingschool2"),
                new MovieActor("movie01", "actor3", "actingaward3", 2001, "null"),
                new MovieActor("movie01", "actor4", "actingaward4", 2001, "actingschool4"),
                new MovieActor("movie02", "actor0", "actingaward0", 2002, "null"),
                new MovieActor("movie02", "actor1", "actingaward1", 2002, "actingschool1"),
                new MovieActor("movie02", "actor2", "actingaward2", 2002, "actingschool2"),
                new MovieActor("movie02", "actor3", "actingaward3", 2002, "null"),
                new MovieActor("movie02", "actor4", "actingaward4", 2002, "actingschool4"),
                new MovieActor("movie03", "actor0", "actingaward0", 2003, "null"),
                new MovieActor("movie03", "actor1", "actingaward1", 2003, "actingschool1"),
                new MovieActor("movie03", "actor2", "actingaward2", 2003, "actingschool2"),
                new MovieActor("movie03", "actor3", "actingaward3", 2003, "null"),
                new MovieActor("movie03", "actor4", "actingaward4", 2003, "actingschool4"),
                new MovieActor("Tar","Cate Blanchett", "Best Actress", 2022, "National Institute of Dramatic Art")
                );
    }

    public List<Customer> createItemsCustomer() {
        return List.of(
                new Customer("1", "CustName1", "cust1@example.org", Instant.now()),
                new Customer("2", "CustName2", "cust2@example.org", Instant.now()),
                new Customer("3", "CustName3", "cust3@example.org", Instant.now()),
                new Customer("4", "CustName4", "cust4@example.org", Instant.now()),
                new Customer("5", "CustName5", "cust5@example.org", Instant.now())
        );
    }

    public List<ProductCatalog> createProductCatalog() {
        return List.of(
                ProductCatalog.builder().id(1).title("Title 1").isbn("orig_isbn").authors(Set.of("b", "g")).price(BigDecimal.valueOf(10)).build(),
                ProductCatalog.builder()
                        .id(2)
                        .isbn("1-565-85698")
                        .authors(new HashSet<>(Arrays.asList("a 1", "b 1")))
                        .price(BigDecimal.valueOf(1.22))
                        .title("Title 55")
                        .build(),
                ProductCatalog.builder()
                        .id(2)
                        .isbn("1-565-85698")
                        .authors(new HashSet<>(Arrays.asList("a 30 ", "b 30")))
                        .price(BigDecimal.valueOf(30.22))
                        .title("Title 55")
                        .build(),
                ProductCatalog.builder()
                        .id(2)
                        .isbn("1-565-85698")
                        .authors(new HashSet<>(Arrays.asList("a 50", "b 50")))
                        .price(BigDecimal.valueOf(50.22))
                        .title("Title 55")
                        .build(),
                ProductCatalog.builder()
                        .id(2)
                        .isbn("1-565-85698")
                        .authors(new HashSet<>(Arrays.asList("a 70", "b 70")))
                        .price(BigDecimal.valueOf(70.22))
                        .title("Title 55")
                        .build()
        );
    }
}
