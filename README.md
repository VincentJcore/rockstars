# Rockstars API

## Local Dev Instructions

In `./docker` start `docker-compose up` voor een geconfigureerde PostgreSQL instantie.

Vanaf `./` start `./mvnw clean spring-boot:run` om de applicatie te starten.

In `./postman` is een Postman collectie te vinden en te importeren met requests om de API handmatig te testen

## TODO
* security
* volledige test suite (WebMvcTesten, Testcontainers of DataJpaTesten)
* Swagger + OA 3 spec na implementatie PathMatchers
* Cascade definities van het database schema nakijken.