criar queue no sqs com localstack e aws cli:
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name test_queue



https://awslabs.github.io/aws-lambda-powertools-java/utilities/sqs_large_message_handling/
https://nuvalence.io/insights/propagating-request-context-via-sqs/
https://lobster1234.github.io/2017/04/05/working-with-localstack-command-line/