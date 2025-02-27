import React, { useEffect, useState } from "react";
import TaskForm from "./components/TaskForm/index";
import TaskList from "./components/TaskList/index";
import { completeTask, createTask, getTasks } from "./api";
import "./App.css";

const App = () => {
  const [tasks, setTasks] = useState([]);
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  // Fetch tasks from the backend
  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await getTasks();
        setTasks(response.data);
        setError(null);
      } catch (error) {
        console.error("Error fetching tasks:", error);
        setError("Failed to fetch tasks. Please try again later.");
      }
    };
    fetchTasks();
  }, []);

  // Handle task creation
  const handleTaskCreated = async (newTask) => {
    setIsLoading(true);
    try {
      const response = await createTask(newTask);
      setTasks([response.data, ...tasks].slice(0, 5)); // Keep only the latest 5 tasks
      setError(null);
    } catch (error) {
      console.error("Error creating task:", error);
      setError("Failed to create task. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  // Handle task completion
  const handleTaskCompleted = async (taskId) => {
    setIsLoading(true);
    try {
      await completeTask(taskId);
      setTasks(tasks.filter((task) => task.id !== taskId)); // Remove the completed task from the list
      setError(null);
    } catch (error) {
      console.error("Error completing task:", error);
      setError("Failed to complete task. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="app-container">
      <div className="task-form-list-container">
        <div className="task-form-container">
          {error && (
            <div className="error-message">
              <p>{error}</p>
            </div>
          )}
          <TaskForm onTaskCreated={handleTaskCreated} isLoading={isLoading} />
        </div>
        <div className="task-list-container">
          <TaskList tasks={tasks} onTaskCompleted={handleTaskCompleted} />
        </div>
      </div>
    </div>
  );
};

export default App;
