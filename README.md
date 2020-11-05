# spring-boot-scraper

This is the demo apllication which uses spring-batch and scrapes web page.

To run:
1. pull application.
2. docker-compose up --build

It expose two endpoints.
1. One to list all persons.
curl -X GET http://localhost:8080/api/v1/persons
Another to invoke the batch job.
curl -X POST http://localhost:8080/api/v1/invoke

