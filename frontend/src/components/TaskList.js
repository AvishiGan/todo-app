import React from "react";
import TaskItem from "./TaskItem";

const TaskList = ({ tasks, onTaskCompleted }) => {
  return (
    <div>
      {tasks.map((task) => (
        <TaskItem key={task.id} task={task} onTaskCompleted={onTaskCompleted} />
      ))}
    </div>
  );
};

export default TaskList;
