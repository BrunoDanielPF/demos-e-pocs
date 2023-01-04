package br.com.example.demomysql.controller;

import br.com.example.demomysql.entity.Customer;
import br.com.example.demomysql.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("cliente")
public class CustomerController {
    CustomerRepository repository;
    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }
    @PostMapping
    public Mono<Customer> createCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }
    @GetMapping
    public Flux<Customer> getAll() {
        return repository.findAll();
    }
}
