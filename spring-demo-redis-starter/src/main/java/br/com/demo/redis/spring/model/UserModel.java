package br.com.demo.redis.spring.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class UserModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -931532168837293074L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public UserModel() {
    }

    public UserModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
