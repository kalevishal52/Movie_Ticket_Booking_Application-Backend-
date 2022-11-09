# Movie Ticket Booking Application API 

This is an Individual project, I have worked on the rest API service for Movie Ticket Booking, and this project is done in around 5 days. This API service provides users to see the Movies and Book the seats of the show, multiple users can use these functionalities.

## Entity Relationship Diagram

[![ER Diagram](https://github.com/kalevishal52/Movie_Ticket_Booking_Application-Backend-/blob/main/images/Movie_Booking_ER.png?raw=true)](https://github.com/kalevishal52/Movie_Ticket_Booking_Application-Backend-/blob/main/images/Movie_Booking_ER.png?raw=true)


# Functionalities
-   User can register/login
-   See all Movies
-   Get shows for Movies 
-   Get available seats, Book multiple seats
-   Conform booking


## Backend Work
-   User Authentication of signUp and Login using Spring security
-  Proper Exception Handling
-  Proper Input Validation
-   Data Stored in the database(mySQL)

## Installation and Run
-  You can clone this repo and start the serve on localhost
-   Before running the API server, we should update the database config inside the application.properties file.
-   Update the port number, username and password as per our local database config.
    -   server.port=8888
    -   spring.datasource.url=jdbc:mysql://localhost:3306/moviesdb
    -   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    -   spring.datasource.username=**mysql username**
    -   spring.datasource.password=**YourPassword**
    -   spring.jpa.hibernate.ddl-auto=update

# Tech Stacks

-   Java Core
-   Spring Data JPA
-   Spring Security
-   Spring Boot
-   Hibernate
-   MySQL
