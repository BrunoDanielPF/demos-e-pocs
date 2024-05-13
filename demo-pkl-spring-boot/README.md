# Configuration as code(pkl)

documentação: https://pkl-lang.org/index.html

> infelizmente ainda não existe uma maneira nativa de compilar o projeto usando PKL no WINDOWS, necessário instalar o WSL

## Beneficios
- Possibilida de de tratar propriedades com condições caso sejam nulas(`obtidas de fontes externas`).
- Interpolação sobre propriedades do tipo texto.
- Possibilidade de obtenção de fontes externas(`Variaveis, Arquivos, http, módulo do classpath`).
- Capacidade de estourar exceção no momento da obtenção, garantindo rápida identificação de problemas, antes mesmo de gerar eventos na aplicação e causar nullpointer por causa da ausencia de propriedade.

## Desvantagens

## como executar

### requisitos obrigatorios
- jdk 11
- wsl instalado

### para usuário de Windows

primeiro passo: 

faça a instalação do wsl no windows
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


## debug remoto

para debugar, como está no WSL precisa debugar remotamente no intellij.

> ative o application REMOTE JVM DEBUG no intellij


em `build/libs` execute o comando abaixo:

```shell
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar demo-pkl-spring-boot-0.0.1-SNAPSHOT.jar
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
