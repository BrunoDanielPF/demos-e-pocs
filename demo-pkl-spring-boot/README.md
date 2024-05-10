# Configuration as code(pkl)

documentação: https://pkl-lang.org/index.html

> infelizmente ainda não existe uma maneira nativa de compilar o projeto usando PKL no WINDOWS, necessário instalar o WSL

## como executar

### requisitos obrigatorios
- jdk 11
- wsl instalado

### para usuário de Windows

primeiro passo: 
faça a instalação do wsl no windown
```shell
wsl --install
```
> talvez seja necessario configurar a jdk no wsl, nao reconheceu minhas variáveis então precisei fazer o seguinte:
> - sudo apt update
> - sudo apt install default-jre
> - sudo apt install default-jdk
> - voalá, pronto!

segundo passo:
para gerar as as classes do schema pkl execute a task gradle abaixo:

```shell
.gradlew configClasses
```

Com as classes geradas ( lembra muito o proto ) execute a aplicação abaixo:

```shell
.gradlew bootRun
```

vai retornar o seguinte output 
```shell
2024-05-09 23:58:46.326  INFO 4274 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2024-05-09 23:58:46.353  INFO 4274 --- [           main] b.c.e.p.d.DemoPklSpringBootApplication   : Started DemoPklSpringBootApplication in 17.497 seconds (JVM running for 18.516)
Server {
  endpoints = [Endpoint {
    name = endpoint1
    port = 1234
  }, Endpoint {
    name = endpoint2
    port = 5678
  }]
}

```