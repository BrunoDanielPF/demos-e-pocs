package br.com.example.demomysql.repository;

import br.com.example.demomysql.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
