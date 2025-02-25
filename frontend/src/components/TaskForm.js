import React, { useState } from "react";
import PropTypes from "prop-types";
import "./TaskForm.css"; // Import the CSS file

const TaskForm = ({ onTaskCreated }) => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (title.trim() === "") return;

    setIsLoading(true); // Start loading
    try {
      await onTaskCreated({ title, description });
      setTitle("");
      setDescription("");
    } catch (error) {
      console.error("Error creating task:", error);
    } finally {
      setIsLoading(false); // Stop loading
    }
  };

  return (
    <div className="task-form">
      <h2>Add a Task</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Title</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="Enter task title"
            required
            aria-label="Task title"
          />
        </div>
        <div>
          <label>Description</label>
          <textarea
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            placeholder="Enter task description"
            aria-label="Task description"
          />
        </div>
        <button type="submit" disabled={isLoading}>
          {isLoading ? "Adding..." : "Add"}
        </button>
      </form>
    </div>
  );
};

TaskForm.propTypes = {
  onTaskCreated: PropTypes.func.isRequired,
};

export default TaskForm;
