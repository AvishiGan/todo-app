import React from "react";
import "./TaskItem.css";

const TaskItem = ({ task, onTaskCompleted }) => {
  return (
    <div className="task-item">
      <h3>{task.title}</h3>
      <p>{task.description}</p>
      <button onClick={() => onTaskCompleted(task.id)}>Done</button>
    </div>
  );
};

export default TaskItem;
