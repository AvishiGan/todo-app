package com.todoapp.controller;

import com.todoapp.model.Task;
import com.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @PutMapping("/{id}/complete")
    public Task completeTask(@PathVariable Long id) {
        return taskService.completeTask(id);
    }
}
