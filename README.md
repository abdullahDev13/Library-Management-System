# 📚 Library Management System

A RESTful backend application built using **Spring Boot**, **Spring Data JPA**, **Hibernate**, and **PostgreSQL** for managing library operations such as book management, user management, borrowing and returning books, library management, and address management.

## 🚀 Features

### 👤 User Management

* Create new users
* Update user information (full and partial updates)
* Associate users with addresses
* Borrow books from the library
* Return borrowed books

### 📖 Book Management

* Add new books
* Search books by author
* Search books by title
* Search books by author and title
* Track book availability status

### 🏢 Library Management

* Create libraries
* Associate libraries with addresses
* Add books to libraries

### 📍 Address Management

* Create addresses
* Retrieve address details
* Update addresses
* Delete addresses
* Fetch all addresses

### ⚠️ Exception Handling

* Global exception handling using `@ControllerAdvice`
* Custom exceptions for:

  * Invalid User ID
  * Invalid Book ID
  * Invalid Address ID
  * Book Borrowing Errors
  * Library Book Assignment Errors

### 📄 API Documentation

* Swagger/OpenAPI integration for API documentation and testing

---

## 🛠️ Tech Stack

* Java 17/21
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Swagger/OpenAPI
* ModelMapper
* Postman

---

## 🏗️ Project Architecture

The application follows a layered architecture:

```text
Controller Layer
       ↓
Service Layer
       ↓
Repository Layer
       ↓
PostgreSQL Database
```

### Package Structure

```text
com.lms
│
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
├── serviceimplementation
└── util
```

---

## 📚 API Endpoints

### Address APIs

| Method | Endpoint      | Description            |
| ------ | ------------- | ---------------------- |
| POST   | /address      | Create Address         |
| GET    | /address/{id} | Get Address by ID      |
| GET    | /address      | Get All Addresses      |
| PUT    | /address      | Update Address         |
| PATCH  | /address/{id} | Partial Address Update |
| DELETE | /address/{id} | Delete Address         |

### User APIs

| Method | Endpoint                       | Description         |
| ------ | ------------------------------ | ------------------- |
| POST   | /user/{addressId}              | Create User         |
| PUT    | /user                          | Update User         |
| PATCH  | /user/{userId}                 | Partial User Update |
| PUT    | /user/borrow/{userId}/{bookId} | Borrow Book         |
| POST   | /user/return/{bookId}          | Return Book         |

### Book APIs

| Method | Endpoint                            | Description                |
| ------ | ----------------------------------- | -------------------------- |
| POST   | /book                               | Add Book                   |
| GET    | /book/author/{author}               | Search by Author           |
| GET    | /book/title/{title}                 | Search by Title            |
| GET    | /book/author/{author}/title/{title} | Search by Author and Title |

### Library APIs

| Method | Endpoint                      | Description         |
| ------ | ----------------------------- | ------------------- |
| POST   | /library/{addressId}          | Create Library      |
| PUT    | /library/{libraryId}/{bookId} | Add Book to Library |

---

## 🔄 Book Borrowing Workflow

1. User requests a book.
2. System validates:

   * User exists.
   * Book exists.
   * Book is not already borrowed.
3. Book is assigned to the user.
4. Borrow timestamp is recorded.
5. Book status changes to borrowed.

### Return Book Workflow

1. User returns a book.
2. Book-user association is removed.
3. Return timestamp is recorded.
4. Book availability status is updated.

---

## ▶️ Running the Application

### Clone Repository

```bash
git clone https://github.com/abdullahDev13/library-management-system.git
```

### Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/librarydb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Run Application

Application will start at:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

---

## 🎯 Learning Outcomes

* Developed RESTful APIs using Spring Boot.
* Implemented database operations using Spring Data JPA and Hibernate.
* Designed entity relationships and managed data persistence.
* Applied DTO pattern and ModelMapper for object mapping.
* Implemented custom exception handling and API response wrapping.
* Documented APIs using Swagger/OpenAPI.
* Tested APIs using Postman.

---

## 👨‍💻 Author

**Abdullah Ansari**

B.Tech (CSE - AI/ML)

Spring Boot Backend Developer | Java Developer
