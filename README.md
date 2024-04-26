# User Management API

The User Management API provides a RESTful interface for managing user data. 
This API allows you to create, retrieve, update, and delete user records. 
Users are represented by a JSON object with properties such as `firstName`, `lastName`, `dateOfBirth`, and `address`.

## Technologies Used

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- SQLite (in-memory database)
- Hibernate with SQLite dialect

## Database Configuration

This project uses an in-memory SQLite database for development and testing purposes. 
By default, Spring Data JPA doesn't provide out-of-the-box support for SQLite. 
To enable SQLite support, we've included the following dependencies in the `pom.xml` file:


<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.43.2.1</version>
</dependency>
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.3.1.Final</version>
</dependency>
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-community-dialects</artifactId>
    <version>6.3.1.Final</version>
</dependency>


Additionally, we've configured the SQLite dialect in the application.properties file:

codespring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect


This dialect provides Hibernate with the necessary support for working with SQLite databases.
Running the Application

Clone the repository: git clone https://github.com/your-repo/user-management-api.git
Navigate to the project directory: cd user-management-api
Build the project: mvn clean install
Run the application: mvn spring-boot:run

The application will start, and the in-memory SQLite database will be initialized.


API Documentation
The API documentation is available in https://documenter.getpostman.com/view/27595365/2sA3Bt2VS1. 
It includes detailed information about the available endpoints, request and response formats, and examples.
