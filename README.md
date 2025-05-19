# 👥 Employee Management

A RESTful API for managing employee information, built with **Java 17**, **Spring Boot**, and **JPA**.

---

## 📑 Table of Contents

- [✨ Features](#-features)
- [🏗️ Architecture](#-architecture)
- [👤 Employee Model](#-employee-model)
- [📡 API Endpoints](#-api-endpoints)
   - [🔑 Authentication](#-authentication)
   - [👥 Employee Management](#-employee-management)
- [🧰 Technologies](#-technologies)
- [🚀 Getting Started](#-getting-started)
   - [✅ Prerequisites](#-prerequisites)
   - [📦 Installation](#-installation)
- [📘 API Documentation](#-api-documentation)
- [🧪 Testing](#-testing)
- [🔐 Security](#-security)
- [❗ Exception Handling](#-exception-handling)

---

## ✨ Features

- ✅ CRUD operations for employee management
- 🔐 JWT-based authentication
- 🧱 Hexagonal Architecture (Ports & Adapters)
- 🧠 SOLID principles applied
- ⚠️ Centralized exception handling
- 📘 Swagger/OpenAPI documentation
- 🧪 Unit tests with JUnit 5 & Mockito

---

## 🏗️ Architecture

This project follows the **Hexagonal Architecture** pattern with a clear separation of concerns:

- **Domain Layer**: Business entities and repository interfaces (ports)
- **Application Layer**: Use cases and business logic
- **Infrastructure Layer**: Controllers, repositories, configurations (adapters)

---

## 👤 Employee Model

The `Employee` entity includes the following fields:

- `firstName`
- `middleName`
- `paternalLastName`
- `maternalLastName`
- `age`
- `gender`
- `birthdate` (`dd-MM-yyyy`)
- `position`

---

## 📡 API Endpoints

### 🔑 Authentication

| Method | Endpoint                 | Description              |
|--------|--------------------------|--------------------------|
| POST   | `/api/auth/register`     | Register a new user      |
| POST   | `/api/auth/authenticate` | Get JWT token            |

### 👥 Employee Management

| Method | Endpoint                       | Description                 |
|--------|--------------------------------|-----------------------------|
| GET    | `/api/employees`               | Get all employees           |
| GET    | `/api/employees/{id}`          | Get employee by ID          |
| POST   | `/api/employees`               | Create a new employee       |
| POST   | `/api/employees/batch`         | Create multiple employees   |
| PUT    | `/api/employees/{id}`          | Update an existing employee |
| DELETE | `/api/employees/{id}`          | Delete an employee          |

---

## 🧰 Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT Authentication
- H2 Database (for development)
- JUnit 5 & Mockito
- Swagger / OpenAPI
- Maven

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17+
- Maven 3.6+

### 📦 Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/trujillodevelop/employee-management.git
   cd employee-management

2. **Build the project**:
   `mvn clean install`

3. **Run the application**:
   `mvn spring-boot:run`

The application will start on http://localhost:8080

---
## 📘 API Documentation

Swagger UI is available at: http://localhost:8080/swagger-ui.html

---

## 🧪 Testing

Run the tests with:
`mvn test`
---
## 🔐 Security

The API is secured with JWT. To access protected endpoints:

1. Register a user or authenticate to get a JWT token
2. Include the token in the Authorization header of your requests:
   Authorization: Bearer your_jwt_token
---
## ❗Exception Handling

The application includes global exception handling for:
- Resource didn't find exceptions
- Validation exceptions
- General exceptions
