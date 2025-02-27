package com.todoapp.service;

import com.todoapp.model.Task;
import com.todoapp.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        // Arrange
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");

        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
            Task savedTask = invocation.getArgument(0);
            savedTask.setId(1L);
            savedTask.setCreatedAt(LocalDateTime.now());
            return savedTask;
        });

        // Act
        Task createdTask = taskService.createTask(task);

        // Assert
        assertNotNull(createdTask.getId());
        assertEquals("Test Task", createdTask.getTitle());
        assertEquals("Test Description", createdTask.getDescription());
        assertFalse(createdTask.isCompleted());
        assertNotNull(createdTask.getCreatedAt());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testGetTasks() {
        // Arrange
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");

        when(taskRepository.findTop5ByCompletedFalseOrderByCreatedAtAsc()).thenReturn(Arrays.asList(task1, task2));

        // Act
        List<Task> tasks = taskService.getTasks();

        // Assert
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());
        verify(taskRepository, times(1)).findTop5ByCompletedFalseOrderByCreatedAtAsc();
    }

    @Test
    void testCompleteTask() {
        // Arrange
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setCompleted(false);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Task completedTask = taskService.completeTask(1L);

        // Assert
        assertTrue(completedTask.isCompleted());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void testCompleteTask_NotFound() {
        // Arrange
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> taskService.completeTask(1L));
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, never()).save(any(Task.class));
    }
}