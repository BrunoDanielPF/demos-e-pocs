package br.com.example.demopostgres.controller;

import br.com.example.demopostgres.entity.Customer;
import br.com.example.demopostgres.repository.CustomerRepository;
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

    @PutMapping("{id}")
    public Mono<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        return repository.findById(id).flatMap( p -> {
            p.setCpf(customer.getCpf());
            p.setName(customer.getName());
            return repository.save(p);
        }).switchIfEmpty(repository.save(customer));
    }
}
