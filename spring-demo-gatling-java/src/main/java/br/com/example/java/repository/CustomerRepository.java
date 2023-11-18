package br.com.example.java.repository;

import br.com.example.java.DemoGatlingJavaSpringApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<DemoGatlingJavaSpringApplication.Customer, Long> {
}
