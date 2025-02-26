import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import TaskList from "./index";

test("renders TaskList with tasks", () => {
  const tasks = [
    { id: 1, title: "Task 1", description: "Description 1" },
    { id: 2, title: "Task 2", description: "Description 2" },
  ];
  const handleTaskCompleted = jest.fn();
  render(<TaskList tasks={tasks} onTaskCompleted={handleTaskCompleted} />);

  // Verify tasks are displayed
  expect(screen.getByText("Task 1")).toBeInTheDocument();
  expect(screen.getByText("Task 2")).toBeInTheDocument();
});
