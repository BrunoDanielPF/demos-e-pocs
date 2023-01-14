package com.example.demo.repository;

import com.example.demo.entity.Person;
import com.example.demo.entity.PrimaryKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface PersonRepository extends CrudRepository<Person, PrimaryKey> {

    List<Person> findById(String id);
}
