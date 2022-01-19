# Cinema-app

This application is implemented as a ticket service for cinemas. 
It allows you to create cinema sessions, 
add new movies and cinema halls also you can check what the session is available, 
buy ticket and check previous orders.
- --
## Used technologies
- Apache Tomcat(v9.0.55)
- Spring(Web, Security, Core)
- Hibernate
- HQL
- Maven
- --
## Application features
- Two users are created on startup in the class `cinema/util/DataInitializer.java`
- - First user - email: bob@gmail.com, password: 12345, Roles: User, Admin
- - Second user - email: ani@gmail.com, password: qwerty, Roles: User
- Implemented 3 layers architecture: DAO, Service, Controller
- Rest principles
- --
## How to run program
- Create your schema in DB
- Change some data in `resources/db.properties`
```
MySQL properties
db.driver=YOUR_DRIVER
db.url=YOUR_DATABASE_URL
db.user=YOUR_USERNAME
db.password=YOUR_PASSWORD
```
- Configure Tomcat
- Run
- --
## Endpoints
- GET: /cinema-halls, /movies, /movie-sessions/available - USER, ADMIN
- GET: /users/by-email - ADMIN
- GET: /orders, /shopping-carts/by-user - USER
- POST: /register - ALL USERS
- POST: /cinema-halls, /movies, /movie-sessions - ADMIN
- POST: /orders/complete - USER
- PUT: /movie-sessions/{id} - ADMIN
- PUT: /shopping-carts/movie-sessions - USER
- DELETE: /movie-sessions/{id} - ADMIN
