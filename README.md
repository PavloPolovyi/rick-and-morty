# Rick&Morty characters Wiki

<h2>📣 Project description 📣</h2>
This is a simple REST API that exposes two endoints: <b>/random</b> - to get info about random Rick&Morty characters and <b>/by-name?nameLike={query}</b> - to find info about characters whose name is like query pattern. The data is dynamically synchronized using an external API (https://rickandmortyapi.com/). This application follows SOLID principles and is built up using N-tier architecture.

<h2>Features</h2>

* Dynamical data synchronization using built in Spring Boot feature

* Saving data to the database

* 3-layer architecture: Controllers, Services and Repositories

* All endpoints are described using OpenAPI specification

* Sending requests to external API

* Using latest stable Java version and Spring Boot 3

* Project includes tests for Repository, Service and Controller layers


<h2>:bricks:Project structure:bricks:</h2>
- Controllers - take main part in request/response cycle, receive data from users and invoke business logic of services 
to process it and store in database. Send back data to users, when they request it.<br>
- Services - this layer coordinates work of all application, process commands and performs data sync.<br>
- Repositories - here information is stored and retrieved.<br>

## <h2>Technologies</h2>
* Java 17
* Spring Boot
* Spring Data JPA
* Spring MVC
* PostgreSQL
* Hibernate
* Docker
* Liquibase
* OpenAPI 3.0
* JUnit, Mockito, Testcontainers, Spring Boot Testing
* FeignClient for sending requests to external API


## <h2>:bomb:Instructions for launching the project:bomb:</h2>
<h4>To run this project locally, follow these steps:</h4>

1️⃣ You should install <a href="https://docs.docker.com/get-docker/">Docker</a> for easy launching

2️⃣  Clone this project from GitHub
```bash
https://github.com/PavloPolovyi/rick-and-morty
```
3️⃣ Navigate to project folder in terminal and run following command:
```bash
./mvnw clean package -DskipTests
```
if it has failed, install Maven and run following command:
```bash
mvn package -DskipTests
```
4️⃣ Then run and wait while images are building:
```bash
docker compose build
```
5️⃣ And finally run application. Start takes a while, because of initial data synchronization:
```bash
docker compose up
```
:six: Go to the browser and use the following url to test the application and see OpenAPI documentation. 
```bash
http://localhost:8081/swagger-ui.html
```
:seven: If port 8081 is busy on your machine, just change SPRING_LOCAL_PORT value in the .env file.

