# Employee Management

A RESTful API for managing employee information, built with Java 17, Spring Boot, and JPA.

## Features

- CRUD operations for employee management
- JWT Authentication
- Hexagonal Architecture
- SOLID principles
- Exception handling
- Swagger documentation
- Unit tests with JUnit and Mockito

## Architecture

This project follows the Hexagonal Architecture (Ports and Adapters) pattern with a clear separation of concerns:

- **Domain Layer**: Contains the business entities and repository interfaces (ports)
- **Application Layer**: Contains the use cases and business logic
- **Infrastructure Layer**: Contains the adapters for the repositories, controllers, and configuration

## Employee Model

The employee model includes the following fields:
- First name
- Middle name
- Paternal last name
- Maternal last name
- Age
- Gender
- Birth date (dd-mm-yyyy)
- Position

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/authenticate` - Authenticate and get JWT token

### Employee Management
- `GET /api/employees` - Get all employees
- `GET /api/employees/{id}` - Get employee by ID
- `POST /api/employees` - Create a new employee
- `POST /api/employees/batch` - Create multiple employees in a single request
- `PUT /api/employees/{id}` - Update an employee
- `DELETE /api/employees/{id}` - Delete an employee

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT Authentication
- H2 Database (for development)
- JUnit 5 & Mockito
- Swagger/OpenAPI
- Maven

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/trujillodevelop/employee-management.git 
   cd EmployeeManagement

2. Build the project:
   `mvn clean install`

3. Run the application:
   `mvn spring-boot:run`

The application will start on http://localhost:8080

### API Documentation

Swagger UI is available at: http://localhost:8080/swagger-ui.html

## Testing

Run the tests with:
`mvn test`

## Security

The API is secured with JWT. To access protected endpoints:

1. Register a user or authenticate to get a JWT token
2. Include the token in the Authorization header of your requests:
   \" Authorization: Bearer your_jwt_token \"

## Exception Handling

The application includes global exception handling for:
- Resource not found exceptions
- Validation exceptions
- General exceptions
