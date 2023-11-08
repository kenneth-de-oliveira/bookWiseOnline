**BookWiseOnline** 
========================================================================
This Microservice is a “fictitious” digital book inventory project proposal developed as a form of training for Minsait "Java Backend Avançado: Criando Microserviços de alta perfomance" training, whose main objective is to offer main services, such as: reserving books, consult reserved books and book cruds and students.

**Some of the resources used**

- Microservice Architecture
- Clean Architecture
- Spring Cloud Gateway
- Service registration and discovery - Eureka
- Spring Cloud OpenFeign
- Clean Code
- Spring Boot 
- RabbitMQ
- Keycloak 
- RestFull
- H2 Database Engine
- JPA - Hibernate
- Spring Data JPA
- OAuth 2.0

# **Instructions**

	First, clone the repository at the address:
	
	https://github.com/kenneth-de-oliveira/bookWiseOnline
	
	After the project is cloned, open the terminal in the cloned directory **bookWiseOnline** 

And use the following commands:

	cd bookWiseOnline
	mvn install

**It is very important to wait for the execution of the above mentioned commands.**

Project configuration
========================================================================
- Java 17
- Maven 3

Example of execution
========================================================================

Create a new student **POST**: localhost:8080/students
```json
{
  "document": "70943418496",
  "name": "kenneth",
  "age": "24"
}
```

Create a new book **POST**: localhost:8080/books
```json
{
  "title": "HTML5 e CSS3: guia prático e visual",
  "authorName": " Elizabeth Castro",
  "text": "Lorem Ipsum"
}
```

Create a new category **POST**: localhost:8080/categories
```json
{
  "description": "Informática",
  "name": "Livros de TI"
}
```

Listing all categories **GET**: ``` localhost:8080/categories```

Find Category by Id **GET**: ``` http://localhost:8080/categories/1 ```

Listing all books **GET**: ``` localhost:8080/books```

Find book by Id **GET**: ``` http://localhost:8080/books/1 ```
