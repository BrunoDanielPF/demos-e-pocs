package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonService() {
    }

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public User getById(String id) {
        return personRepository.getById(id);
    }

    public Iterator<User> getAll() {
        return personRepository.getAll();
    }

    public User create(User user) {
        return personRepository.create(user);
    }

    public User update(User user) {
        return personRepository.update(user);
    }

    public void delete(String id) {
        personRepository.delete(id);
    }
}
