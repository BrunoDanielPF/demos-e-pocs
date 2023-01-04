package br.com.example.demomysql.entity;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Table("tb_customer")
public class Customer {

    @Id
    private Integer id;
    private String name;
}
