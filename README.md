# spring-boot-scraper

This is the demo apllication which uses spring-batch and scrapes web page.

To run simply call docker-compose up --build

It expose two endpoints.
One to list all persons.
curl -X GET http://localhost:8080/api/v1/persons
Another to invoke the batch job.
curl -X POST http://localhost:8080/api/v1/invoke

