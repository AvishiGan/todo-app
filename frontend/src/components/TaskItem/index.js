import React from "react";
import "./index.css";

const TaskItem = ({ task, onTaskCompleted }) => {
  return (
    <div className="task-item">
      <div className="task-content">
        <h3>{task.title}</h3>
        <p>{task.description}</p>
      </div>
      <button onClick={() => onTaskCompleted(task.id)}>Done</button>
    </div>
  );
};

export default TaskItem;
