# Exemplo SQS

Exemplo de propagação de ID entre mensagens

## comandos rápidos:

- awslocal sqs create-queue --queue-name queue-sample-sqs
> cria uma fila sqs dentro do container em execução da localstack.

Exemplo de retorno:

```shell
{
    "QueueUrl": "http://sqs.sa-east-1.localhost.localstack.cloud:4566/000000000000/queue-sample-sqs"
}
```

## Documentações usadas
- https://hub.docker.com/r/localstack/localstack/#
- https://docs.awspring.io/spring-cloud-aws/docs/3.0.0/reference/html/index.html#sqs-integration

