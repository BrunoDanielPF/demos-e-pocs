package br.com.core.imutability.dynamodb.sample.controller;

import br.com.core.imutability.dynamodb.sample.entity.Customer;
import br.com.core.imutability.dynamodb.sample.repository.CustomerRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
public class CustomerController {

    CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        repository.create(Customer.builder().accountId(customer.accountId()).subId(customer.subId()).name(customer.name()).build());
        return ResponseEntity.ok().body("criado com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return ResponseEntity.ok().body(repository.getAll());
    }
}
