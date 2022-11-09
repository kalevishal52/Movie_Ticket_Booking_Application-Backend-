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
-   Update the port number, username and password as per your local database config.
```
   server.port=8888
   spring.datasource.url=jdbc:mysql://localhost:3306/moviesdb
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.username=**mysql username**
   spring.datasource.password=**YourPassword**
   spring.jpa.hibernate.ddl-auto=update
```

# Tech Stacks

-   Java Core
-   Spring Data JPA
-   Spring Security
-   Spring Boot
-   Hibernate
-   MySQL


# Backend

## User Controller

#### POST : /users
```
{
  "email": "test@gmail.com",
  "mobileNumber": "7999712978",
  "name": "Test",
  "password": "test123",
  "role" : "NORMAL"
}
                            **Response**
{
    "userId": 90,
    "name": "Test",
    "mobileNumber": "7999712978",
    "password": "$2a$10$qe2Ic/vH2JFM1cYLfx6tXObbu4ts3VNMaAWDYhLCW2v/e/aJOuNjO",
    "email": "test@gmail.com",
    "role": "ROLE_NORMAL"
}
```

## Movie Controller

#### GET : /movies?movieName=Brahmastra Part 2
```
**Response**
[
  {
    "movieId": 91,
    "name": "Brahmastra Part 2",
    "genre": "Adventure",
    "duration": "2Hr30Min",
    "releaseDate": "2023-01-20",
    "casts": [
      {
        "castId": 92,
        "role": "Hero",
        "name": "Ranbir Kapoor"
      },
      {
        "castId": 93,
        "role": "Director",
        "name": "Ayan Mukerji"
      }
    ],
    "ratingInfo": {
      "ratingId": 0,
      "rating": 0,
      "numberOfPeoples": 0
    },
    "posterURL": "https://www.imdb.com/title/tt6277462/mediaviewer/rm3309497345/?ref_=tt_ov_i",
    "in2D": true,
    "in3d": true
  }
]
```

## Booking Controller

#### Post : /bookings/?userId=1&showId=94"
```
[
  21,21,22,23   // Seats Id to Book
]
**Response
{
  "bookingId": 99,
  "userId": 1,
  "shows": {
    "showId": 94,
    "showTiming": "2023-01-20T12:00:00",
    "durationInSeconds": 9000,
    "screen": {
      "screenId": 14,
      "name": "screen2",
      "noOfSeats": 50,
      "theatre": {
        "theatreId": 12,
        "name": "Ganesh talkies",
        "address": {
          "addressId": 11,
          "streetName": "Tembhurni Road",
          "city": "Kurduwadi",
          "pincode": "413208"
        }
      },
      "seatRows": 10,
      "seatCols": 5,
      "seatPrice": 80
    },
    "movie": {
      "movieId": 91,
      "name": "Brahmastra Part 2",
      "genre": "Adventure",
      "duration": "2Hr30Min",
      "releaseDate": "2023-01-20",
      "casts": [
        {
          "castId": 92,
          "role": "Hero",
          "name": "Ranbir Kapoor"
        },
        {
          "castId": 93,
          "role": "Director",
          "name": "Ayan Mukerji"
        }
      ],
      "ratingInfo": {
        "ratingId": 0,
        "rating": 0,
        "numberOfPeoples": 0
      },
      "posterURL": "https://www.imdb.com/title/tt6277462/mediaviewer/rm3309497345/?ref_=tt_ov_i",
      "in2D": true,
      "in3d": true
    },
    "totalSeats": 50,
    "availableSeats": 46
  },
  "seatsId": [],
  "bookingStatus": "Created",       // booking status is "Created"
  "totalBillAmount": 320
}
```
**In the above booking booking status is "Created" and now you nave to conform booking in 5 minutes or your choosen seat will be available for others.**

#### Post : /bookings/conform?bookingId=99&userId=1
```
**Response
{
  "bookingId": 99,
  "userId": 1,
  "shows": {
    "showId": 94,
    "showTiming": "2023-01-20T12:00:00",
    "durationInSeconds": 9000,
    "screen": {
      "screenId": 14,
      "name": "screen2",
      "noOfSeats": 50,
      "theatre": {
        "theatreId": 12,
        "name": "Ganesh talkies",
        "address": {
          "addressId": 11,
          "streetName": "Tembhurni Road",
          "city": "Kurduwadi",
          "pincode": "413208"
        }
      },
      "seatRows": 10,
      "seatCols": 5,
      "seatPrice": 80
    },
    "movie": {
      "movieId": 91,
      "name": "Brahmastra Part 2",
      "genre": "Adventure",
      "duration": "2Hr30Min",
      "releaseDate": "2023-01-20",
      "casts": [
        {
          "castId": 92,
          "role": "Hero",
          "name": "Ranbir Kapoor"
        },
        {
          "castId": 93,
          "role": "Director",
          "name": "Ayan Mukerji"
        }
      ],
      "ratingInfo": {
        "ratingId": 0,
        "rating": 0,
        "numberOfPeoples": 0
      },
      "posterURL": "https://www.imdb.com/title/tt6277462/mediaviewer/rm3309497345/?ref_=tt_ov_i",
      "in2D": true,
      "in3d": true
    },
    "totalSeats": 50,
    "availableSeats": 46
  },
  "seatsId": [                      // Seats Booked
    21,
    21,
    22,
    23
  ],
  "bookingStatus": "Conformed",     // Your Booking is "Conformed"
  "totalBillAmount": 320
}
```





## Swagger UI
## Screen & Shows Controller
[![SwaggerUI](https://github.com/kalevishal52/Movie_Ticket_Booking_Application-Backend-/blob/main/images/Sw_1.png?raw=true)](https://github.com/Anantk05/temporary-partner-4254/blob/main/images/Swagger.png?raw=true)


## Theatre & User Controller
[![SwaggerUI](https://github.com/kalevishal52/Movie_Ticket_Booking_Application-Backend-/blob/main/images/Sw_2.png?raw=true)](https://github.com/kalevishal52/Movie_Ticket_Booking_Application-Backend-/blob/main/images/Sw_2.png?raw=true)

## Bookiing & Movie Controller
[![SwaggerUI](https://github.com/kalevishal52/Movie_Ticket_Booking_Application-Backend-/blob/main/images/Sw_3.png?raw=true)](https://github.com/kalevishal52/Movie_Ticket_Booking_Application-Backend-/blob/main/images/Sw_3.png?raw=true)

# Now You booking is Conformed You can Chill!
[![SwaggerUI](https://user-images.githubusercontent.com/97676470/193455146-6d60ca42-0811-46b0-ad3f-0c49a1296fa5.jpg)](https://user-images.githubusercontent.com/97676470/193455146-6d60ca42-0811-46b0-ad3f-0c49a1296fa5.jpg)

