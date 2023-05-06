package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.DynamoEnhancedRepositoryAbstract;
import com.example.demo.repository.annotation.DynamoEnhancedRepository;
import org.springframework.stereotype.Service;

@Service
@DynamoEnhancedRepository(tableName = "User", DynamoBeanClass = User.class)
public class PersonRepository extends DynamoEnhancedRepositoryAbstract<User> {
}
