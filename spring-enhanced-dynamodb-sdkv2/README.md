# spring-dynamodb sincrono

## como usar
<p>step 1 - docker-compose up para subir o localstack</p>

create User
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

retorno
```json
{
    "id": "4407da11-545d-47a8-a332-5de59d96d55a",
    "name": "teste",
    "age": "24",
    "email": "teste@gmail.com",
    "password": "1234",
    "createdAt": "2023-05-06T02:09:55.170327500Z"
}
```

get users

```shell
curl --location 'localhost:8080/v1/user'
```
