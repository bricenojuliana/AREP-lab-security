# Property Management App

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

![image](https://github.com/user-attachments/assets/64565e13-d276-415c-822c-8220493141ac)


The diagram depicts:
- User interaction with the client (HTML+JS), served by Apache.
- Secure asynchronous communication with the backend (Spring Boot).
- Database access limited to the backend for added security.

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
   - **Environment**: Deploy the Spring Boot application on a separate EC2 instance.
   - **Steps**:
     1. Install Java, Maven and Git on the instance.
     2. Clone the repository
        
        ```
        git clone https://github.com/bricenojuliana/AREP-lab-security
        ```
        
     4. Compile the application
        ```
        mvn clean install
        ```
     6. Deploy the backend application (`.jar` file).
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
  - Java Spring Boot (backend).
  - MySQL (database).
  - Certbot (for TLS configuration with Let's Encrypt).


## Demonstration Videos

To provide a better understanding of the deployment and application functionality, you can watch the following videos:

1. **Deployment Video**:
 

2. **Demo Video**:
   
[Demo-security.webm](https://github.com/user-attachments/assets/3dc4adbf-66fd-4a1d-b805-ce3533996e84)

