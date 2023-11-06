# QuizSpringBootProject
## Introduction
This Spring Boot Quiz Project is designed to help you create and manage quizzes based on a set of questions stored in a PostgreSQL database. Users can specify the quiz category and the number of questions they want in their quiz. The project also allows users to submit their responses, and the service layer calculates and returns the user's score.
## Technologies Used
* Spring Boot
* PostgreSQL (for database storage)
* Lombok (for reducing boilerplate code)
* Spring Web (for building RESTful APIs)
* Postman (for testing API endpoints)
## Getting Started
### Prerequisites
Before you begin, ensure you have the following software and tools installed on your system:

* Java Development Kit (JDK) 8 or higher
* Spring Boot
* PostgreSQL Database
* Postman (for testing)
### Setup
1. Clone the repository to your local machine:
   ```
   git clone <repository-url>
   ```
2. Configure the PostgreSQL database connection in application.properties:
```
spring.datasource.url=jdbc:postgresql://<database-host>:<port>/<database-name>
spring.datasource.username=<database-username>
spring.datasource.password=<database-password>
```
3. Build the project:
```
./mvnw clean package
```
4. Run the application:
```
./mvnw spring-boot:run
```
 Your Spring Boot Quiz Project should now be running on http://localhost:8080.

## Project Structure
The project is organized as follows:
* **'src/main/java/com.rohilla.quizapp'**: Contains the Java source code in three different layers controller, service and dao plus one model package for the entities.
* **'src/main/resources'**: Contains configuration files and resources.
* **'pom.xml'**: Contains project dependencies and build settings.

## Database
The database schema contains a **quiz.question** table (quiz is the schema name) with the following fields:
* **id** (Primary Key) : The unique identifier for each question.
* **category** : The category of the question ( java/python etc)
* **option1** : The option 1 in the answers.
* **option2** : The option 2 in the answers.
* **option3** : The option 3 in the answers.
* **option4** : The option 4 in the answers.
* **question_title** : The question itself.
* **right_answer** : The correct answer to the question.

## API Endpoints
The following API endpoints are available for interaction with the application:

* **GET /question/allQuestions:** Retrieve a list of all questions in the database.
* **GET /question/category/{category}:** Retrieve questions by a specific category.
* **POST /question/add:** To add a new question in the database.
* **DELETE /question/delete/{id}:** Delete a question by id.
* **POST /quiz/create:** Create a quiz by specifying the category and the number of questions.
* **GET /quiz/get/{id}:** Get a quiz and its question by id.
* **POST /quiz/submit/{id}:** Submit quiz responses by an id ( quiz id) and get the user's score.

## Testing with Postman
Postman can be used to test all the endpoint by providing correct url and request body in json format ( in post requests) .
**Example:**
Json format request body for Post query of submitting responses for quiz id: 2 
```
 [
{   "id" : 3 ,
    "userResponse": "null"
     },
     {
          "id" : 6 ,
    "userResponse": "do-while loop"
     }
    ,
    {
         "id" : 2 ,
    "userResponse": "0"
     }
]
```



  
