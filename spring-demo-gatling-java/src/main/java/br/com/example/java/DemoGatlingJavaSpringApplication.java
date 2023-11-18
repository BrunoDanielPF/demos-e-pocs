package br.com.example.java;

import br.com.example.java.exception.CustomerNotFoundException;
import br.com.example.java.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.Optional;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class DemoGatlingJavaSpringApplication {

    @Autowired
    CustomerRepository customerRepository;

    public static final Logger LOGGER = Logger.getLogger("Api Controller");

    public static void main(String[] args) {
        SpringApplication.run(DemoGatlingJavaSpringApplication.class, args);
    }

    @GetMapping("/customer")
    public String helloWorld(@RequestHeader(name = "id") Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("customer not found: " + id));
        LOGGER.info("retornou customer:" + customer);
        return "Hello world";
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        if (customer != null) {
            LOGGER.info("customer created:" + customer);
            return ResponseEntity.ok(customerRepository.save(customer));
        } else {
            throw new CustomerNotFoundException("Customer body not found");
        }
    }

    @Entity
    @Table(name = "tb_customer")
    public static class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column
        private String name;

        public Customer() {
        }

        public Customer(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
