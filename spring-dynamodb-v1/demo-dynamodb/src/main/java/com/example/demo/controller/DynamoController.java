package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.entity.PrimaryKey;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class DynamoController {

    @Autowired
    PersonRepository personRepository;

    public DynamoController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/person/{id}")
    public ResponseEntity getPerson(@PathVariable("id") String id) {
        return getByPrimaryKey(id);
    }

    @PostMapping("/person")
    public ResponseEntity addPerson(@RequestBody Person person) {
        return ResponseEntity.ok(personRepository.save(person));
    }

    private ResponseEntity<?> getByPrimaryKey(String id) {
        PrimaryKey primaryKey = new PrimaryKey();
        primaryKey.setId(id);

        Optional<Person> mayBePerson = personRepository.findById(primaryKey);

        if (mayBePerson.isPresent()) {
            return ResponseEntity.ok(mayBePerson.get());
        }
        return ResponseEntity.notFound()
                .build();
    }
}
