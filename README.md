This is a simple Smart Contact Manager project built using Spring Boot, Thymeleaf, and MySQL. The application allows users to manage their contacts efficiently.

## Features

- User authentication: Users can sign up, log in, and log out securely.
- CRUD operations: Users can perform basic CRUD operations (Create, Read, Update, Delete) on their contacts.
- Search functionality: Users can search for contacts based on name, email, or phone number.
 
## Technologies Used

- Spring Boot: For building the backend RESTful API.
- Thymeleaf: As the templating engine for server-side rendering.
- MySQL: As the database to store user and contact information.

## Prerequisites

Before running this application, make sure you have the following installed:

- Java Development Kit (JDK)
- Maven
- MySQL Server

## Navigate to the project directory:

  Set up MySQL database:

- Create a database named smart_contact_manager.
  Update application.properties file with your MySQL username and password.
  
- Build and run the application using Maven:
  mvn spring-boot:run
  
- Access the application in your web browser:
  http://localhost:8282
  
## Usage

- Sign up for a new account if you're a new user, or log in if you're an existing user.
- Once logged in, you can add, view, edit, or delete contacts.
- Use the search functionality to find specific contacts by name, email, or phone number.
  
## Contributing

- Contributions are welcome! If you have any suggestions, improvements, or issues, feel free to open an issue or create a pull request.

