import axios from "axios";

const API_BASE_URL =
  process.env.REACT_APP_API_BASE_URL || "http://localhost:8080/api/tasks";

export const getTasks = () => axios.get(API_BASE_URL);
export const createTask = (task) => axios.post(API_BASE_URL, task);
export const completeTask = (id) => axios.put(`${API_BASE_URL}/${id}/complete`);
