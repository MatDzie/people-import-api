# people-import-api
Task performed as part of technical recruitment: REST API that allows import people from swapi.dev

Requirements:

As part of the task, you need to create a REST API in a layered architecture,
add an endpoint to import characters from swapi (https://swapi.dev) into the database - which will take a people id -> GET https://swapi.dev/api/people/{id}/

- Add an endpoint for individual characters from the database,
- Add an endpoint for searching by name in the database - a part of the name is enough, case insensitive,
- Add a validator that will filter out a character shorter than the value in application.properties

- In the database we only store:
"name"
"height"
"mass"

- Required technologies:
Java
Spring / Spring Boot
Swagger UI
any database, it can even be in memory H2
