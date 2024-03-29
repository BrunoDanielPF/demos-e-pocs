## criando aplicação

### dependências necessárias

    implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'
    implementation 'org.springframework.boot:spring-boot-starter-webflux'
    runtimeOnly 'org.postgresql:r2dbc-postgresql'
### configuração necessária

    spring.r2dbc.url=r2dbc:postgresql://localhost:5432/customerdb
    spring.r2dbc.username=admin
    spring.r2dbc.password=admin

### interface repository 

    @Repository
    public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
    }

### exemplo de classe da tabela

    @Data
    @ToString
    @Table(value = "tb_customer")
    public class Customer {
    @Id
    private Integer id;
    private String name;
    private String cpf;
    }
> espera-se que exista um banco disponível durante a execução da aplicação para realizar as operações na tabela de inserção e listagem por ex.
> 
## testando aplicação

executar na pasta `demo-postgres` o comando:

    docker-compose up

para levantar o container do postgresql localmente e depois executar a aplicação.

### criando um novo dado 
![img.png](img.png)

### atualizando dado cadastrado
![img_1.png](img_1.png)

### retornando dados cadastrados 
![img_2.png](img_2.png)