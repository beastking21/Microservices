# Hotel Booking Microservices Platform – Java Spring Boot

A **distributed microservices-based backend platform** built using **Java, Spring Boot, and Spring Cloud**, demonstrating real-world backend architecture patterns such as **service discovery, API gateway routing, authentication, fault tolerance, and polyglot persistence**.

This project focuses on **scalability, resilience, and loose coupling**, similar to production-grade systems used in banking and product-based environments.

---

## Architecture Overview

The system follows a modular microservices architecture where each service is independently deployable and owns its own data.

### Core Components

* **Service Registry (Eureka)**
  Enables dynamic service discovery and load-balanced inter-service communication.

* **Config Server**
  Centralized configuration management for all microservices.

* **API Gateway (Spring Cloud Gateway)**
  Acts as a single entry point for clients, handling:

  * Request routing
  * JWT authentication
  * Rate limiting
  * Cross-cutting concerns

* **User Service**
  Manages user information and authentication logic.

* **Hotel Service**
  Handles hotel-related data and business logic.

* **Rating Service**
  Manages hotel ratings and reviews.

Each service communicates using **REST APIs** and is registered with Eureka for discovery.

---

## Security

* Implemented **JWT-based authentication and authorization**.
* Tokens are validated at the **API Gateway** before forwarding requests to downstream services.
* Role-based access control enforced at service level.

---

## Resilience & Fault Tolerance

To prevent cascading failures in a distributed system, the following patterns are implemented using **Resilience4j**:

* **Circuit Breaker** – Stops repeated calls to failing services
* **Rate Limiter** – Controls request throughput to protect services
* **Fallback mechanisms** – Graceful degradation during service failures

These patterns improve overall system stability and reliability.

---

## Databases (Polyglot Persistence)

Each microservice owns its own database to ensure loose coupling:

* **User Service** – MySQL
* **Hotel Service** – PostgreSQL
* **Rating Service** – MongoDB

This design allows choosing the right database based on data access patterns and scalability needs.

---

## Tech Stack

* **Language:** Java
* **Frameworks:** Spring Boot, Spring Cloud
* **Service Discovery:** Eureka
* **API Gateway:** Spring Cloud Gateway
* **Security:** JWT
* **Resilience:** Resilience4j (Circuit Breaker, Rate Limiter)
* **Databases:** MySQL, PostgreSQL, MongoDB
* **Build Tool:** Maven
* **Testing:** Postman (API testing)

---

## How to Run (Local)

### Prerequisites

* JDK 17+
* Maven
* MySQL, PostgreSQL, MongoDB running locally

### Steps

1. Clone the repository

   ```bash
   git clone https://github.com/beastking21/Microservices.git
   ```

2. Start Config Server

   ```bash
   cd Config-Server
   mvn spring-boot:run
   ```

3. Start Service Registry

   ```bash
   cd ServiceRegistry
   mvn spring-boot:run
   ```

4. Start API Gateway and individual services

   ```bash
   mvn spring-boot:run
   ```

5. Test APIs using Postman via Gateway endpoints

---

## Key Learnings

* Designing and decomposing systems into independent microservices
* Implementing centralized routing and authentication
* Applying resilience patterns in distributed systems
* Managing multiple databases across services
* Understanding real-world backend scalability challenges

---

## Future Improvements

* Docker and Docker Compose support
* Kubernetes deployment
* Centralized monitoring (Prometheus/Grafana)
* Distributed tracing (Zipkin)

---

## Repository

[https://github.com/beastking21/Microservices](https://github.com/beastking21/Microservices)
