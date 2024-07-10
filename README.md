# Springboot Devices Microservice 
Senior Software Developer Technical Assessment PART I. 

This project is composed by:
- BE  -> Spring Boot (this)
- [BFF and FE -> NestJS app and ReactJS app](https://github.com/WalterBarcelos/iotdevices_bff_fe) respectively

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Flow](#Flow)

## Installation

Make sure you have Docker running. Download to your project directory, go to your root project dir and run:
```
docker-compose build
docker-compose up
```

It will download and start all the necessary services to run this project, which are:
- MongoDB
- Keycloak
- Springboot

## Usage

You should have the following urls available:

- Springboot application: `http://localhost:8081`
- MongoDB: `http://localhost:27017`
- Keycloak: `http://localhost:8085` (default login: admin/admin)

When running the docker compose commands, we get automatically in keycloak a realm named `myrealm`, a client with id `myclient` and a user with username `testuser`and password `password`, so we can easily test it.

The following endpoint should work correctly by using any http client:

##### Public URL
- POST   `http://localhost:8085/auth/realms/myrealm/protocol/openid-connect/token` (Get JWT token), with body in x-www-form-urlencoded format using:
```
FieldName     | Value
------------- | -------------
cliend_id     | myclient
username      | testuser
password      | password
grant_type    | password
```

It will return a JWT token, wich has to be used as `Bearer Token` for the private urls.    

##### Private URLs
- GET  `http://localhost:8081/api/devices` (Get all devices)
- POST `http://localhost:8081/api/devices` (Create device), with Body in JSON format:
```
{
    "name":"Device 1",
    "mobileNumber":"622334455",
    "lastConnection":"2024-07-04 11:22:12",
    "latitude":"36.53998",
    "longitude":"-4.62473"
}
```
- GET `http://localhost:8081/api/devices/{id}` (Get a device by id)
- DELETE `http://localhost:8081/api/devices/{id}` (Delete a device by id) 


If you use Postman, the file [IOTDEVICES_BE.postman_collection.json](IOTDEVICES_BE.postman_collection.json) has these endopints ready to use. Just change the data to use it.\
Here you can see instructions about [how to import postman collections](https://learning.postman.com/docs/getting-started/importing-and-exporting/importing-data/).

![Alt text](/swagger.png)

## Flow

![Alt text](/diagram.jpeg)

1. User Login in FE (ReactJS):

The user enters their credentials (username and password) in a login form.
The credentials are sent to Keycloak to perform authentication.
Upon successful authentication, Keycloak returns a JWT token to the frontend.

2. Using JWT Token in FE:

The frontend (ReactJS) stores the JWT token (in localStorage).
The frontend uses this JWT token to make authenticated requests to the BFF (Backend For Frontend) NestJS application.

3. Handling JWT Token in BFF (NestJS):

The BFF receives the JWT token from the frontend in the authorization header of each request.
The BFF validates the JWT token to ensure it is valid.
After validation, the BFF uses the same JWT token to make requests to the Spring Boot backend API.

4. BE (Spring Boot) Checks Permissions:

The Spring Boot backend receives the request with the JWT token from the BFF.
The Spring Boot backend checks the token against Keycloak to validate the user’s role and permissions.
Based on the permissions, the Spring Boot backend processes the request and returns the appropriate response. In this simple example, we only check if the user is valid as we have no permissions defined.

5. BFF Processes Response and Serves via GraphQL:

The BFF processes the response from the Spring Boot backend.
The BFF uses GraphQL to serve the processed data to the frontend.

6. FE Displays Data to User:

The frontend (ReactJS) receives the data via GraphQL from the BFF.
The data is displayed to the user.