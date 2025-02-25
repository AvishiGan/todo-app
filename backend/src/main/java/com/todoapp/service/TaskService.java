package com.todoapp.service;

import com.todoapp.model.Task;
import com.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setCompleted(false);
        task.setCreatedAt(java.time.LocalDateTime.now());
        return taskRepository.save(task);
    }

    public List<Task> getTasks() {
        return taskRepository.findTop5ByCompletedFalseOrderByCreatedAtDesc();
    }

    public Task completeTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        return taskRepository.save(task);
    }
}
