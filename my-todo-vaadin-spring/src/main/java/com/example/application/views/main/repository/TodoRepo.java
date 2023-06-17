package com.example.application.views.main.repository;

import com.example.application.views.main.model.Todo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<Todo, Long> {

    @Transactional
    void deleteByDone(boolean done);
}
