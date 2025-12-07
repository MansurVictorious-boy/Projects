Online Bookstore – Spring Boot & Microservices
This project is a comprehensive Online Bookstore platform built using Spring Boot and a Microservices architecture. The system is designed to allow users to securely register, browse a book catalog, manage their shopping cart, place orders, and leave reviews.





🏗️ Architecture Overview
The application is decomposed into independent microservices that communicate via Feign Client and are managed through a centralized API Gateway and Eureka Discovery Server.



Microservices

Authentication Service: Manages user registration (email/phone), secure login, password encryption (BCrypt), and JWT token generation for system-wide access.





Book Service: Handles the complete lifecycle of books, including catalog management, advanced search (title, author, genre), and stock validation via integration with the Inventory Service.





Customer Service: Manages user profiles, saved addresses, and validates JWT tokens for profile access.



Cart Service: Manages the user's shopping experience, including adding/removing items, quantity updates, and real-time cost calculation.



Order Service: Orchestrates the purchasing flow, tracking order status (Placed, Packed, Shipped), and managing order history.



Rating & Review Service: Allows authenticated users to submit reviews and calculates average ratings for books.


Infrastructure Components

API Gateway: Acts as the unified entry point, handling routing, load balancing, and global JWT filtering.


Eureka Discovery Server: Maintains a registry of all active microservices for dynamic discovery.


🛠️ Technology Stack
The project utilizes a modern Java-based stack with robust DevOps and Observability tools.

Core & Frameworks
Language: Java


Framework: Spring Boot 


Architecture: Microservices, REST API 



Security: Spring Security, BCrypt, JWT (JSON Web Tokens) 



Inter-service Communication: Feign Client 


Resilience: Resilience4j (Circuit Breaker) 

Data & Persistence

Databases: MySQL, Oracle, MongoDB 




ORM: Spring Data JPA 


DevOps & CI/CD

Containerization: Docker 


Orchestration/Deployment: AWS EC2, Kubernetes 



CI/CD Pipeline: Jenkins 


Version Control: Git / Bitbucket 

Observability & Logging

Distributed Tracing: Zipkin, Sleuth 


Logging: Log4J2, Splunk, AWS CloudWatch 



Monitoring: Grafana, Spring Boot Admin 


Testing & Documentation

Testing: JUnit5, Mockito, JaCoCo (Code Coverage) 



API Documentation: Swagger / OpenAPI 



🚀 Key Features

Secure Onboarding: Validates input details (email/password strength) and encrypts passwords before database storage.


Catalog Management: Admins can add, update, or remove books, while users can filter by price, rating, and language.



User Profiles: Supports multiple address management (home, office) and secure profile updates.


Smart Cart: Continuously calculates costs and fetches real-time book data.


Order Workflow: Coordinates between Cart, Inventory, and Payment services to process orders and track history.



Feedback System: Users can add, modify, or remove reviews for specific books.

🧪 Testing Strategy
The application maintains high code quality through rigorous testing standards:


Unit Testing: Implemented using JUnit5 and Mockito for all service layers.




Code Coverage: Monitored using JaCoCo to ensure high test coverage.



Integration: Services are integrated with the Notification Service and Inventory Service using REST/Feign.


📦 Deployment
The project follows a CI/CD approach for seamless deployment:

Source code is managed in Git/Bitbucket.


Jenkins automates the build and deployment pipeline.

Microservices are containerized using Docker and deployed to AWS EC2 or Kubernetes clusters.


Service health is monitored via Spring Boot Admin dashboards.

📝 API Documentation
All microservices are documented using Swagger, providing visibility into available endpoints and route mappings.
