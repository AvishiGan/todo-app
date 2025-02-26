import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import TaskItem from "./index";

test("renders TaskItem and completes a task", () => {
  const task = {
    id: 1,
    title: "Test Task",
    description: "Test Description",
  };
  const handleTaskCompleted = jest.fn();
  render(<TaskItem task={task} onTaskCompleted={handleTaskCompleted} />);

  // Verify the task is displayed
  expect(screen.getByText("Test Task")).toBeInTheDocument();
  expect(screen.getByText("Test Description")).toBeInTheDocument();

  // Complete the task
  fireEvent.click(screen.getByText("Done"));

  // Verify the callback was called
  expect(handleTaskCompleted).toHaveBeenCalledWith(1);
});
