package br.com.example.cache.configuration;

import br.com.example.cache.cache.CacheStore;
import br.com.example.cache.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreConfiguration {

    @Bean
    public CacheStore<Employee> employeeCacheStore() {
        return new CacheStore<Employee>(120, TimeUnit.SECONDS);
    }
}
