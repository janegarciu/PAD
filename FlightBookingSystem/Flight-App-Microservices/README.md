Flight Booking System
---------------------

* [List of services](#list-of-services)
* [Endpoints Overview](#endpoints-overview)
* [Docker](#docker)
* [List of technologies to be used](#list-of-technologies)
* [Diagram reflecting the architecture of the system](#architecture-diagram)



### List Of Services

--------

- auth-service

Takes care of user authentication into the system, is a part of API-gateway
- user-service

Saves all authenticated users in the database
- ticket-service

Ticket service returns all available information on a specified ticket id/number stored in the database
- flight-service

Contains all the information on available flights
- gateway-service

Filters all the requests sent by the user and points them to the right service to return the information. Also takes care of authorization/authentication. Hides all the services internal services from user
- eureka-server

Identifies all available services
- booking-service

Allows user to book any flight to create ticket and add it into the database of tickets
- billing-service

Takes cer of user to have a bill to pay for his booking
- caching-service

Stores certain requests responses into in-memory database to not call a certain service twice.


### Endpoints Overview

--------

- user-service

**_Outbound_**:

+ GET `flight-booking-app/get-user` : get user-profile by parameter - id or username
+ POST `flight-booking-app/create-user` : create new user

- ticket-service

**_Outbound_**

+ GET `flight-booking-app/get-ticket` : will return ticket by ticket id, username or user-id

**_Inbound_**

+ GET `flight-service/get-flight` : will get flight info by flight id to show it in the ticket info

- flight-service

**_Outbound_**

+ GET `flight-booking-app/flights/get-flight` : get one flight by parameter - id / info
+ GET `flight-booking-app/flights/get-all-flights` : retrieve all the flights that matches the value of query param
+ GET `flight-booking-app/airports/get-airports` : get a list of airports + parameter to find by airport name
+ POST `flight-booking-app/flights/create-flight` : will create a new flight in the system. Will be restricted only for admin users

**_Inbound_**

Will handle only inbound requests from Ticket service

- booking-service

**_Outbound_**

+ POST `flight-booking-app/book-a-flight ` : will book a flight by flight id and billing details; will return ticket id

**_Inbound_**

+ POST `billing-service/process-payment ` : will route payment to Billing service.
+ POST `ticket-service/create-ticket ` : if payment is successful, will route create-ticket request to Ticket service and return ticket id/number

- billing-service

Will handle only inbound requests from Booking service:

- cache-service

**_Inbound_**

+ GET `flight-booking-app/flights/get-all-flights` : the reason why we need to cache all flights because they don't change so often and it is frequently called.
  The cache will live one hour and will be refreshed after the first request in case of adding of new flights

### Docker

--------

At the moment there are two databases for Ticket service and Flight service. In order to start the docker container go to FlightBookingSystem/Flight-App-Microservices/common/flightApp-docker and in the terminal run docker compose up.

### List Of Technologies

-------

+ JDK 1.8
+ Spring Boot
+ Spring Cloud
+ Spring Data
+ Scala(cache microservice)
+ DB - MySQL for each service except
+ Netflix Eureka Client/Server
+ For cache service used EhCache

### Architecture Diagram

------

![Microservices Architectural Model](docs/MicroservicesDiagram.png)