package com.example.dynamodb.demodynamooperations.operations.expressions;

import com.example.dynamodb.demodynamooperations.beans.MovieActor;
import com.example.dynamodb.demodynamooperations.beans.ProductCatalog;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Comparator;
import java.util.Map;
import java.util.logging.Logger;

import static software.amazon.awssdk.enhanced.dynamodb.internal.AttributeValues.numberValue;

@Service
public class ScanExpressionOperationsDynamoDb {

    public static Logger logger = Logger.getLogger(ScanExpressionOperationsDynamoDb.class.getSimpleName());
    // Método para realizar uma operação de varredura (scan) no DynamoDB.
    public static void scanAsync(DynamoDbTable<ProductCatalog> productCatalog) {
        Map<String, AttributeValue> expressionValues = Map.of(
                ":min_value", numberValue(8.00),
                ":max_value", numberValue(80.00));

        // Configuração da solicitação de varredura (scan).
        ScanEnhancedRequest request = ScanEnhancedRequest.builder()
                .consistentRead(true)
                // 1. O método 'attributesToProject()' permite especificar quais valores deseja que sejam retornados.
                .attributesToProject("id", "title", "authors", "price")
                // 2. A expressão de filtro limita os itens retornados que correspondem aos critérios fornecidos.
                .filterExpression(Expression.builder()
                        .expression("price >= :min_value AND price <= :max_value")
                        .expressionValues(expressionValues)
                        .build())
                .build();

        // 3. Um objeto PageIterable é retornado pelo método de varredura.
        PageIterable<ProductCatalog> pagedResults = productCatalog.scan(request);
        logger.info("Número de páginas: " + pagedResults.stream().count());

        // 4. Registra os itens ProductCatalog retornados usando duas variações.
        // 4a. Esta versão classifica e registra os itens de cada página.
        pagedResults.stream().forEach(p -> p.items().stream()
                .sorted(Comparator.comparing(ProductCatalog::price))
                .forEach(
                        item -> logger.info(item.toString())
                ));
        // 4b. Esta versão classifica e registra todos os itens de todas as páginas.
        pagedResults.items().stream()
                .sorted(Comparator.comparing(ProductCatalog::price))
                .forEach(
                        item -> logger.info(item.toString())
                );
    }

    public static void query(DynamoDbTable<MovieActor> movieActorTable) {

        // 1. Define uma instância QueryConditional para retornar itens que correspondam a um valor de partição.
        QueryConditional chaveIgual = QueryConditional.keyEqualTo(b -> b.partitionValue("movie01"));
        // 1a. Define uma QueryConditional que adiciona um critério de chave de classificação ao critério de valor de partição.
//        QueryConditional classificacaoMaiorOuIgualA = QueryConditional.sortGreaterThanOrEqualTo(b -> b.partitionValue("movie01").sortValue("actor2"));
        // 2. Define uma expressão de filtro que filtra os itens cujo valor de atributo seja nulo.
        final Expression filtrarItensSemNomeEscolaDeAtuacao = Expression.builder().expression("attribute_exists(actingschoolname)").build();

        // 3. Constrói a solicitação de consulta.
//        A saída exibe itens com movieNamevalor movie01 e não exibe nenhum item actingSchoolNameigual a null.
        QueryEnhancedRequest consultaTabela = QueryEnhancedRequest.builder()
                .queryConditional(chaveIgual)
                .filterExpression(filtrarItensSemNomeEscolaDeAtuacao)
                .build();
        // 4. Execute a consulta.
        PageIterable<MovieActor> pagedResults = movieActorTable.query(consultaTabela);
        logger.info("Contagem de páginas: {}" + pagedResults.stream().count()); // Registra o número de páginas.

        pagedResults.items().stream()
                .sorted()
                .forEach(
                        item -> logger.info(item.toString()) // Registra a lista de itens ordenada.
                );
    }
}
