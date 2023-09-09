package com.example.dynamodb.demodynamooperations.operations.batch;

import com.example.dynamodb.demodynamooperations.beans.Customer;
import com.example.dynamodb.demodynamooperations.beans.MovieActor;
import com.example.dynamodb.demodynamooperations.beans.ProductCatalog;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.model.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Logger;

@Service
public class BatchOperationsDynamoDb {

    static Logger logger = Logger.getLogger(BatchOperationsDynamoDb.class.getSimpleName());
    // Criar um logger estático para registrar mensagens.

    public static void batchGetItemExample(DynamoDbEnhancedClient enhancedClient,
                                           DynamoDbTable<Customer> customerTable,
                                           DynamoDbTable<MovieActor> movieActorTable) {

        Customer customer2 = new Customer();
        customer2.setId("2");
        customer2.setEmail("cust2@example.org");

        // 1. Construir um lote para ler da tabela de Clientes (Customer).
        ReadBatch customerBatch = ReadBatch.builder(Customer.class)
                .mappedTableResource(customerTable)
                // 1a. Especificar os valores da chave primária para o item.
                .addGetItem(b -> b.key(k -> k.partitionValue("1").sortValue("cust1@orgname.org")))
                // 1b. Alternativamente, fornecer instâncias de classes de dados para fornecer os valores da chave primária.
                .addGetItem(customer2)
                .build();

        // 2. Construir um lote para ler da tabela de MovieActor.
        ReadBatch moveActorBatch = ReadBatch.builder(MovieActor.class)
                .mappedTableResource(movieActorTable)
                // 2a. Chamar consistentRead(Boolean.TRUE) para cada item da mesma tabela.
                .addGetItem(b -> b.key(k -> k.partitionValue("movie01").sortValue("actor1")).consistentRead(Boolean.TRUE))
                .addGetItem(b -> b.key(k -> k.partitionValue("movie01").sortValue("actor4")).consistentRead(Boolean.TRUE))
                .build();

        // 3. Adicionar objetos ReadBatch à solicitação.
        BatchGetResultPageIterable resultPages = enhancedClient.batchGetItem(b -> b.readBatches(customerBatch, moveActorBatch));

        // 4. Recuperar os itens solicitados com sucesso de cada tabela.
        resultPages.resultsForTable(customerTable).forEach(item -> logger.info(item.toString()));
        resultPages.resultsForTable(movieActorTable).forEach(item -> logger.info(item.toString()));

        // 5. Recuperar as chaves dos itens solicitados, mas não processados pelo serviço.
        resultPages.forEach((BatchGetResultPage pageResult) -> {
            pageResult.unprocessedKeysForTable(customerTable).forEach(key -> logger.info("Chave do item não processado: " + key.toString()));
            pageResult.unprocessedKeysForTable(movieActorTable).forEach(key -> logger.info("Chave do item não processado: " + key.toString()));
        });
    }

    public static void batchWriteItemExample(DynamoDbEnhancedClient enhancedClient,
                                             DynamoDbTable<ProductCatalog> catalogTable,
                                             DynamoDbTable<MovieActor> movieActorTable) {

        // 1. Construir um lote para escrever na tabela ProductCatalog.
        WriteBatch produtos = WriteBatch.builder(ProductCatalog.class)
                .mappedTableResource(catalogTable)
                .addPutItem(b -> b.item(getProductCatItem1()))
                .addDeleteItem(b -> b.key(k -> k
                        .partitionValue(getProductCatItem2().id())
                        .sortValue(getProductCatItem2().title())))
                .build();

        // 2. Construir um lote para escrever na tabela MovieActor.
        WriteBatch filmes = WriteBatch.builder(MovieActor.class)
                .mappedTableResource(movieActorTable)
                .addPutItem(getMovieActorYeoh())
                .addPutItem(getMovieActorBlanchettPartial())
                .addDeleteItem(b -> b.key(k -> k
                        .partitionValue(getMovieActorStreep().getMovieName())
                        .sortValue(getMovieActorStreep().getActorName())))
                .build();

        // 3. Adicionar objetos WriteBatch à solicitação.
        BatchWriteResult resultadoBatchWrite = enhancedClient.batchWriteItem(b -> b.writeBatches(produtos, filmes));

        // 4. Recuperar chaves para itens que o serviço não processou.
        // 4a. 'unprocessedDeleteItemsForTable()' retorna chaves para solicitações de exclusão que não foram processadas.
        if (resultadoBatchWrite.unprocessedDeleteItemsForTable(movieActorTable).size() > 0) {
            resultadoBatchWrite.unprocessedDeleteItemsForTable(movieActorTable).forEach(key ->
                    logger.info(key.toString()));
        }
        // 4b. 'unprocessedPutItemsForTable()' retorna chaves para solicitações de inserção que não foram processadas.
        if (resultadoBatchWrite.unprocessedPutItemsForTable(catalogTable).size() > 0) {
            resultadoBatchWrite.unprocessedPutItemsForTable(catalogTable).forEach(key ->
                    logger.info(key.toString()));
        }
    }

    public static ProductCatalog getProductCatItem1() {
        return ProductCatalog.builder()
                .id(2)
                .isbn("1-565-85698")
                .authors(new HashSet<>(Arrays.asList("a", "b")))
                .price(BigDecimal.valueOf(30.22))
                .title("Title 55")
                .build();
    }

    public static ProductCatalog getProductCatItem2() {
        return ProductCatalog.builder()
                .id(4)
                .price(BigDecimal.valueOf(40.00))
                .title("Title 1")
                .build();
    }

    public static MovieActor getMovieActorBlanchettPartial() {
        MovieActor movieActor = new MovieActor();
        movieActor.setActorName("Cate Blanchett");
        movieActor.setMovieName("Blue Jasmine");
        movieActor.setActingYear(2023);
        movieActor.setActingAward("Best Actress");
        return movieActor;
    }

    public static MovieActor getMovieActorStreep() {
        MovieActor movieActor = new MovieActor();
        movieActor.setActorName("Meryl Streep");
        movieActor.setMovieName("Sophie's Choice");
        movieActor.setActingYear(1982);
        movieActor.setActingAward("Best Actress");
        movieActor.setActingSchoolName("Yale School of Drama");
        return movieActor;
    }

    public static MovieActor getMovieActorYeoh(){
        MovieActor movieActor = new MovieActor();
        movieActor.setActorName("Michelle Yeoh");
        movieActor.setMovieName("Everything Everywhere All at Once");
        movieActor.setActingYear(2023);
        movieActor.setActingAward("Best Actress");
        movieActor.setActingSchoolName("Royal Academy of Dance");
        return movieActor;
    }
}
