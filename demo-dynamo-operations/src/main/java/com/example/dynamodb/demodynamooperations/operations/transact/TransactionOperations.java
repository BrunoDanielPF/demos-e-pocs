package com.example.dynamodb.demodynamooperations.operations.transact;

import com.example.dynamodb.demodynamooperations.beans.MovieActor;
import com.example.dynamodb.demodynamooperations.beans.ProductCatalog;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.model.TransactPutItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.TransactUpdateItemEnhancedRequest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

public class TransactionOperations {

    public static void transactWriteItems(DynamoDbEnhancedClient enhancedClient,
                                          DynamoDbTable<ProductCatalog> catalogTable,
                                          DynamoDbTable<MovieActor> movieActorTable) {

        enhancedClient.transactWriteItems(b -> b
                // 1. Variação mais simples da solicitação de colocação de item.
                .addPutItem(catalogTable, getProductCatId2())
                // 2. Variação da solicitação de colocação de item que acomoda expressões de condição.
                .addPutItem(movieActorTable, TransactPutItemEnhancedRequest.builder(MovieActor.class)
                        .item(getMovieActorStreep())
                        .conditionExpression(Expression.builder().expression("attribute_not_exists (movie)").build())
                        .build())
                // 3. Solicitação de atualização que não remove valores de atributo na tabela se o valor do objeto de dados for nulo.
                .addUpdateItem(catalogTable, TransactUpdateItemEnhancedRequest.builder(ProductCatalog.class)
                        .item(getProductCatId4ForUpdate())
                        .ignoreNulls(Boolean.TRUE)
                        .build())
                // 4. Variação da solicitação de exclusão que aceita um objeto de dados. Os valores das chaves são extraídos para a solicitação.
                .addDeleteItem(movieActorTable, getMovieActorBlanchett())
        );
    }

    public static ProductCatalog getProductCatId2() {
        return ProductCatalog.builder()
                .id(2)
                .isbn("1-565-85698")
                .authors(new HashSet<>(Arrays.asList("a", "b")))
                .price(BigDecimal.valueOf(30.22))
                .title("Title 55")
                .build();
    }

    public static ProductCatalog getProductCatId4ForUpdate() {
        return ProductCatalog.builder()
                .id(4)
                .price(BigDecimal.valueOf(40.00))
                .title("Title 1")
                .build();
    }

    public static MovieActor getMovieActorBlanchett() {
        MovieActor movieActor = new MovieActor();
        movieActor.setActorName("Cate Blanchett");
        movieActor.setMovieName("Tar");
        movieActor.setActingYear(2022);
        movieActor.setActingAward("Best Actress");
        movieActor.setActingSchoolName("National Institute of Dramatic Art");
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
}
