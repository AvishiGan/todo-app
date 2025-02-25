package com.todoapp.repository;

import com.todoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTop5ByCompletedFalseOrderByCreatedAtDesc();
}