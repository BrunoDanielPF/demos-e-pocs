para visualizar as configuracoes publicadas no server:
```shell
  http://localhost:8888/server/default
```

para visualizar a propriedade inserida no server dentro do client
```shell
localhost:8080/value.teste
```
retorno:
```text
hello world 
```
sem derrubar o client e o server agora para executar refresh scope para visualizar a configuração dinamica sem a necessidade de reinicializar a aplicação
mudar o valor da propriedade em config-files e depois atualizar no actuator
```shell
curl --request POST \
  --url http://localhost:8080/actuator/refresh
```
retorno
```text
hello world refresh
```