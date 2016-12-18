# Rest Bank

This little app has the following services:
- Registration
- Login
- Add transactions (deposit or withdraw) for a user
- List transactions for a user

## Based on
https://spring.io/guides/gs/rest-service/

## Prerequisites
- JDK 1.7 or later
- Maven 3 or later

## Stack
- Spring Security
- Spring Boot
- Spring Data JPA
- Maven
- JSP
- HSQL

## Run
```mvn clean spring-boot:run```

## Examples
- To register a user:

GET: ```http://localhost:8080/registration?username=borjafpa&password=12345678&passwordConfirm=12345678```

- To Login a user:

GET: ```http://localhost:8080/login?username=borjafpa&password=12345678```

- To show user transactions:

GET: ```http://localhost:8080/transactions```

- To add a user transaction:

(Deposit) GET ```http://localhost:8080/transaction?type=Deposit&amount=4```

(Withdraw) GET ```http://localhost:8080/transaction?type=Withdraw&amount=4```

## Tests
```mvn clean compile test```
