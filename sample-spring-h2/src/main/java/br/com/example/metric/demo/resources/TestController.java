package br.com.example.metric.demo.resources;

import br.com.example.metric.demo.entity.Customer;
import br.com.example.metric.demo.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class TestController {

    private CustomerRepository repository;

    public TestController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String sayHello() {
        return "ok";
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @GetMapping("/cadastrados")
    public List<Customer> getCustomers() {
        return repository.findAll();
    }
}
