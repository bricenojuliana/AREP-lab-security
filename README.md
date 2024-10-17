[Deploy-security.webm](https://github.com/user-attachments/assets/80eadeb8-bb62-4ba8-a08e-db8cd7e6ae02)# Property Management App

This project is a web-based application for managing properties, featuring secure user authentication and CRUD operations for properties. The architecture is designed for scalability, using separate Amazon EC2 instances for the frontend, backend, and database components.

## Table of Contents
1. [Application Architecture](#application-architecture)
2. [Deployment Instructions](#deployment-instructions)
3. [Security Implementation](#security-implementation)
4. [Requirements](#requirements)
5. [Demonstration Videos](#demonstration-videos)

## Application Architecture

The application architecture is designed with a distributed approach, leveraging multiple AWS EC2 instances for different components to enhance scalability and security. The major components and their interactions are described below:

### Component Diagram

The architecture consists of three main layers:

1. **Client Layer (Frontend)**:
   - **Description**: The user accesses the client interface, a static HTML+JavaScript application served by an Apache web server. The client interacts with the backend services through asynchronous requests.
   - **Deployment**: Hosted on an Amazon EC2 instance with Apache configured to serve the client files.
   - **TLS Configuration**: The Apache server is configured with TLS using Let's Encrypt certificates to secure client-server communication.

2. **Application Layer (Backend)**:
   - **Description**: The backend consists of a Java Spring Boot application responsible for handling requests. There are two controllers:
     - **Login Controller**: Manages user authentication and security.
     - **Property Controller**: Manages CRUD operations for property records.
   - **Deployment**: Deployed on a separate Amazon EC2 instance.
   - **TLS Configuration**: Configured to handle HTTPS requests securely using a Let's Encrypt certificate.

3. **Database Layer**:
   - **Description**: A MySQL database stores user credentials (with hashed passwords) and property records.
   - **Deployment**: The database runs on another Amazon EC2 instance with Ubuntu.
   - **Access Control**: Access is restricted to the backend server for security.

The architecture diagram below illustrates the relationships between the client, backend, and database components:

![image](https://github.com/user-attachments/assets/cb19446c-b79f-4d47-91d6-9be75d183663)





### Class Diagram Overview

This section provides an overview of the class diagram for our Property Management Application. The diagram illustrates the key classes and their relationships, helping to understand the application's architecture and data flow.


![image](https://github.com/user-attachments/assets/0aeee553-f274-437c-9919-472958b18fac)


#### Key Classes

1. **Customer**
   - **Purpose**: Represents a customer in the application. This class contains attributes such as `firstName`, `lastName`, and `id`, which uniquely identifies each customer.
   - **Functionality**: Includes methods to retrieve customer details and to generate a string representation of the customer object.

2. **CustomerRepository**
   - **Purpose**: Serves as an interface for performing database operations related to customers.
   - **Functionality**: Provides methods like `findByLastName` to search for customers based on their last name and `findById` to retrieve a customer by their unique identifier.

3. **User**
   - **Purpose**: Represents a user in the system, primarily for authentication and authorization purposes.
   - **Functionality**: Contains attributes like `username` and `passwordHash`, along with methods to manage these attributes, ensuring secure handling of user credentials.

4. **UserRepository**
   - **Purpose**: Facilitates interaction with the user data in the database.
   - **Functionality**: Includes methods for user management, such as `findByUsername` for locating a user by their username and `findById` for fetching a user by their unique ID.

5. **UserController**
   - **Purpose**: Acts as the intermediary between the User model and the user interface, handling HTTP requests related to user operations.
   - **Functionality**: Provides endpoints for user registration and login, ensuring that appropriate responses are returned based on the operation's success or failure.

6. **Property**
   - **Purpose**: Represents a property in the application, encompassing details like `address`, `description`, `price`, and `size`.
   - **Functionality**: Offers methods to access property attributes and a string representation for easy display.

7. **PropertyRepository**
   - **Purpose**: Manages database interactions for properties.
   - **Functionality**: Provides methods for retrieving all properties and finding specific properties by their unique identifiers.

8. **PropertyController**
   - **Purpose**: Serves as the interface for handling property-related HTTP requests.
   - **Functionality**: Contains endpoints for retrieving properties, updating them, saving new properties, and deleting existing ones, facilitating CRUD operations.

9. **AccessingDataJpaApplication**
   - **Purpose**: The main application class responsible for bootstrapping the application.
   - **Functionality**: Contains methods for initializing demo data and starting the application.

10. **WebConfig**
    - **Purpose**: Configures application-wide settings, such as CORS (Cross-Origin Resource Sharing).
    - **Functionality**: Ensures that the application adheres to security policies for cross-origin requests.

#### Relationships

- **Dependency**: 
  - The `UserController` and `PropertyController` classes depend on their respective repositories (`UserRepository` and `PropertyRepository`) for data management. This allows for a clear separation of concerns, with controllers focused on handling HTTP requests and repositories dedicated to database interactions.
  
- **Association**: 
  - The `CustomerRepository` and `UserRepository` are linked to their respective entity classes (`Customer` and `User`), indicating that these repositories manage the lifecycle of their entities.



## Deployment Instructions

### 1. Frontend Deployment
   - **Environment**: Deploy the static HTML and JavaScript files on an Amazon EC2 instance running Apache.
   - **Steps**:
     1. Install Apache on the instance.
     2. Place the frontend files in the web server's document root (`/var/www/html`).
     3. Configure TLS with Let's Encrypt:
        - Install Certbot.
        - Generate and install the certificate.
        - Verify that HTTPS is enabled for the domain.

### 2. Backend Deployment
   - **Environment**: Deploy the Spring Boot application on a separate EC2 instance with .
   - **Steps**:
     1. Install Java 17, Maven 3.8.4 and Git on the instance.
     2. Clone the repository
        
        ```
        git clone https://github.com/bricenojuliana/AREP-lab-security
        ```
        
     4. Compile the application
        ```
        mvn clean install
        ```
     6. Run the backend application (`.jar` file).
        ```
        sudo java -jar target/jpa-0.0.1-SNAPSHOT.jar
        ```
     7. Set up TLS using Let's Encrypt:
        - Install Certbot.
        - Use Certbot to create and install certificates for the backend.

### 3. Database Deployment
   - **Environment**: Use a separate Amazon EC2 instance with Ubuntu.
   - **Steps**:
     1. Install MySQL server.
     2. Configure the database schema for user authentication and property management.
     3. Secure the MySQL instance by allowing access with a special user.

## Security Implementation

1. **TLS Configuration**:
   - Both the frontend and backend are configured with HTTPS using Let's Encrypt certificates.
   - TLS ensures encrypted communication between the client and server, as well as between the backend and the database.

2. **Password Security**:
   - User passwords are stored as securely hashed values (using SHA-256).
   - The authentication module verifies the hashes during login attempts.

3. **Access Control**:
   - The backend restricts access to the database to authorized operations only.

## Requirements

- **Amazon EC2 Instances**: Three instances for the frontend, backend, and database.
- **Software Dependencies**:
  - Apache (for serving the frontend).
  - Java 17 (backend).
  - MySQL (database).
  - Certbot (for TLS configuration with Let's Encrypt).


## Demonstration Videos

To provide a better understanding of the deployment and application functionality, you can watch the following videos:

1. **Deployment Video**:

 [Deploy-security.webm](https://github.com/user-attachments/assets/d199f1fe-bab9-4884-91f1-33c94be4e9df)


2. **Demo Video**:
   
[Demo-security.webm](https://github.com/user-attachments/assets/3dc4adbf-66fd-4a1d-b805-ce3533996e84)

