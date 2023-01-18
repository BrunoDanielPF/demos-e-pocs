package br.com.example.demopostgres.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Table(value = "tb_customer")
public class Customer {
    @Id
    private Integer id;
    private String name;
    private String cpf;
}
