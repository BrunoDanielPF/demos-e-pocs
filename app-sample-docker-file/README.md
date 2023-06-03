gerar jar

```
gradle assemble
```

publicar localmente no docker

```shell
docker build -t app-sample-docker-file .
```

para executar

```shell
docker compose up
```
```roomsql
DROP TABLE IF EXISTS tb_user;

CREATE TABLE tb_user (
    id bigint not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);

INSERT INTO tb_user VALUES(1, "name teste")
```

criar usuario no banco de dados
```shell
curl --request POST \
  --url http://localhost:8080/users/ \
  --header 'Content-Type: application/json' \
  --data '{
	"name":"teste name"
}'
```

listar usuarios no banco de dados 

```shell
curl --request GET \
  --url http://localhost:8080/users/
```
 