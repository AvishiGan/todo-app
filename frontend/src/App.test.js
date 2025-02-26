import React from "react";
import { render, screen, act } from "@testing-library/react";
import axios from "axios";
import App from "./App";

// Mock axios
jest.mock("axios");

test("renders To-Do App title", async () => {
  // Mock the getTasks API call
  axios.get.mockResolvedValue({
    data: [
      {
        id: 1,
        title: "Task 1",
        description: "Description 1",
        completed: false,
      },
      {
        id: 2,
        title: "Task 2",
        description: "Description 2",
        completed: false,
      },
    ],
  });

  // Render the App component inside act()
  await act(async () => {
    render(<App />);
  });
});
