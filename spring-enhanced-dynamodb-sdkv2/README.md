# spring-dynamodb sincrono

##como usar
<p>step 1 - docker-compose up para subir o localstack</p>

create User without ID
```shell
curl --location 'localhost:8080/v1/user' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "teste2",
"age":"24",
"email":"teste@gmail.com",
"password":"1234"
}'

```

get users

```shell
curl --location 'localhost:8080/v1/user'
```
