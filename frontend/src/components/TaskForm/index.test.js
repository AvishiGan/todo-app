import React from "react";
import { render, screen, fireEvent, act } from "@testing-library/react";
import TaskForm from "./index";

test("renders TaskForm and submits a task", async () => {
  const handleTaskCreated = jest.fn();
  render(<TaskForm onTaskCreated={handleTaskCreated} />);

  // Fill out the form
  fireEvent.change(screen.getByPlaceholderText("Enter task title"), {
    target: { value: "Test Task" },
  });
  fireEvent.change(screen.getByPlaceholderText("Enter task description"), {
    target: { value: "Test Description" },
  });

  // Submit the form
  await act(async () => {
    fireEvent.click(screen.getByText("Add"));
  });

  // Verify the callback was called
  expect(handleTaskCreated).toHaveBeenCalledWith({
    title: "Test Task",
    description: "Test Description",
  });
});
