# RecipyManagementSystem

<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-3.0.5-brightgreen.svg" />
</a>
<a href="Spring Boot url" >
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.0.6-brightgreen.svg" />
</a>
</p>
This is a backend API built using the Spring framework that handles various HTTP requests for an Recipe management system API. The API allows users to create accounts, add products, create orders and manage addresses

---
<br>

## Framework Used
* Spring Boot

---
<br>

## Dependencies
The following dependencies are required to run the project:

* Spring Boot Dev Tools
* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok

<br>

## Database Configuration
To connect to a MySQL database, update the application.properties file with the appropriate database URL, username, and password. The following properties need to be updated:
```
spring.datasource.url=jdbc:mysql://localhost:3306/<databasename>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

```
<br>

## Language Used
* Java

---
<br>

## Data Model

The Job data model is defined in the Job class, which has the following attributes:
<br>

* User Model
```
userId:integer
userName:string
userEmail:string
userPassword:string
userPhoneNumber:string
```

* Recipe Model
```
recipeId:integer 
recipeName:string
recipeIngredients:integer
recipeInstructions:string
```
* Comment Model
```
commentId:integer    
comment:string
```
* AuthenticationToken Model
```
tokenId:integer
token:string
tokenCreationDate:LocalDate
```

## Data Flow

1. The user at client side sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in service class.

4. The method in service class builds logic and retrieves or modifies data from the database, which is in turn given to controller class
5. The controller method returns a response to the API.
6. The API sends the response back to the user.

---

<br>


## API End Points 

The following endpoints are available in the API:

```
/users/signup (POST): Create a new user
/users/signin (POST): login user
/users/users/all (GET): Get user by user ID (request param)
/users/recipe/{name} (POST): deleteRecipe
/users/recipe/comment/{RecipeName} (POST): add comment
/users/recipe/{recipeId}/comment/{commentId} (DELETE): Delete a comment
/user/recipe/{name} (PUT): updatr recipe
/recipe/getAll (GET): get all recipies
/recipe/{name} (GET): get recipe by name
comment/{recipeName} (POST) : add comment
comment/ (GET) : get all coments
```
<br>

## DataBase Used
* SQL database
```
We have used Persistent database to implement CRUD Operations.
```
---
<br>

## Project Summary

This API provides basic functionality for an Recipe management system, allowing users to create recipies, add recipes, search recipe and manage recipies. 
The project can be extended to include additional features as required.


## Author

👤 **Sourabh Mane**

* GitHub: [Sourabh1028](https://github.com/Sourabh1028)

* LinkedIn: [Sourabh Mane](https://www.linkedin.com/in/sourabh-mane-b0a79021a/)
    
---

## 🤝 Contributing

Contributions, issues and feature requests are welcome!<br />Feel free to check [issues page]("url").
    
---
    
## Show your support

Give a ⭐️ if this project helped you!
    
---
    
## 📝 License

Copyright © 2023 [Sourabh Mane](https://github.com/Sourabh1028).<br />

This project is [MIT]("url") licensed.
    
---
