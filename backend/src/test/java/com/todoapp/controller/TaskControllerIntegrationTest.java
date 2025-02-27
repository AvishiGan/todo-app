package com.todoapp.controller;

import com.todoapp.model.Task;
import com.todoapp.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
    }

    @Test
    void testCreateTask() throws Exception {
        String taskJson = "{\"title\":\"Test Task\",\"description\":\"Test Description\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void testGetTasks() throws Exception {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setCreatedAt(LocalDateTime.now());
        taskRepository.save(task);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Task"))
                .andExpect(jsonPath("$[0].description").value("Test Description"));
    }

    @Test
    void testCompleteTask() throws Exception {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setCompleted(false);
        task = taskRepository.save(task);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/tasks/" + task.getId() + "/complete")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed").value(true));
    }
}