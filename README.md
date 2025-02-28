# Todo App
This is a simple to-do task web application that allows users to create and manage tasks efficiently. Users can add new tasks by providing a title and description, and only the most recent five tasks will be displayed in the UI. Tasks can be marked as completed, upon which they will be removed from the visible list.

<div align="center">
  <img src="https://github.com/user-attachments/assets/6755f49d-fb14-49f9-b84c-18d2c3e1d65d" alt="frontend-ui">

  <p><strong>Figure 1: Frontend UI of the To-Do App</strong></p>
</div>

## Technologies Used
- **Frontend**: React
- **Backend**: Spring Boot
- **Database**: PostgreSQL

## Setup

### Backend
1. Clone the repo: `git clone https://github.com/AvishiGan/todo-app.git`
2. Navigate to the backend folder.
3. Build the Spring Boot app: `./mvnw clean install`
4. Run the app: `./mvnw spring-boot:run`

### Frontend
1. Navigate to the frontend folder.
2. Install dependencies: `npm install`
3. Run the app: `npm start`

### Docker Setup
1. Ensure Docker is installed.
2. Build and run all services: `docker-compose up --build`

## Running Tests

### Frontend Unit Tests
- To run the backend unit and integration tests, use the following command: `npm test`

### Backend Unit and Integration Tests
- To run the backend unit and integration tests, use the following command: `./mvnw test`

### Generate Test Coverage Report (Backend)
- To generate a test coverage report using Jacoco, run: `./mvnw test jacoco:report`
- The report will be available in the target/site/jacoco/ directory.
