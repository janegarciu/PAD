Flight Booking System
---------------------


* [List of services](#LIST-OF-SERVICES)
* [Description of outbound API endpoints](#ENDPOINT-SOVERVIEW)
* [Description of inbound API endpoints](#ENDPOINT-SOVERVIEW)
* [List of technologies to be used](#LIST-OF-TECHNOLOGIES)
* [Diagram reflecting the architecture of the system](#ARCHITECTURE-DIAGRAM)


LIST OF SERVICES

--------
- account-service
- user-service
- auth-service
- ticket-service
- flight-service
- gateway-service
- booking-service
- billing-service
- caching-service(not sure if it should be as a separate service)

ENDPOINTS OVERVIEW

(As I have not understood properly the concept of outbound
and inbound APIs I have decided to describe them in one group for now)

---------

**_Account service_**
+ This service will be calling User service
+ POST `account-service/users/sign-up/ + params` 
+ POST `account-service/users/sign-in/ + params` 

**_User service_**
+ This service will handle connection to the Auth service
when a new user signs up.
+ GET `user-service/user-profiles/id` : get user-profile by id
+ GET `user-service/user-profiles/username` : get user-profile by username
+ POST `user-service/user-profiles/user` : create new user

**_Auth service_**
+ Will support communication with User service. As I assume for now, there will be no endpoint.
This will be handled by Spring Security Framework.

**_Ticket service_**
+ GET `ticket-service/tickets/username` : will return tickets by username
+ GET `ticket-service/tickets/user-id` : will return tickets by user-id
+ GET `ticket-service/tickets/id` : will return ticket by ticket id
+ Possible variations of parameters to be added

**_Flight service_**
+ GET `flight-service/flights/id` : get one flight by id / info
+ GET `flight-service/flights + params` : retrieve all the flights that matches the value of query param
+ GET `flight-service/airports` : get a list of airports
+ GET `flight-service/airports/{airport-name}` : list of flights from this airport
+ Search flight by name
+ Search flight by code

**_Gateway service_**
+ Will handle routing of the requests sent between one service to another.
+ Here will be Round Robin Routing logic
+ Circuit Breaker

**_Booking service_**
+ User can book a flight ticket and fill the personal information - billing information
+ POST `booking-service/flights/{flight-id}/booking ` : will book a flight by flight id
+ Get booking details

**_Billing service_**
+ User can pay the flight order
+ GET `billing-service/flights/{flight-id}/payment` : will pay for the flight specified
+ Make payment(handle payment errors: payment authorization timeout and invalid credit card info )


**_Caching service_**
+ Will implement keep-alive connection for other services
+ Will handle multiple simultaneous connections

**LIST OF TECHNOLOGIES**

-------
+ JDK 11.0.10
+ Spring Boot
+ Spring Cloud
+ Spring Data
+ DBs which will be used, have not decided yet(Possibly PostgreSQL and MongoDB)

**ARCHITECTURE DIAGRAM**

------

 *_Will be added soon_*