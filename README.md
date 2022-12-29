# Technical task for Wisercat company

This project was created as technical project for Wisercat Estonia in 2022 year. 
Project includes a back-end and front-end. 
In this repository consists only back-end.
The front-end can be found in another repository.

# Issues and workflow

Initially started to develop back-end and this issue occurred during the design process:
1. What the project architecture should look like?
- I decided to make an architecture divided horizontally into the main components: 
controller, service, database
- Vertically the architecture has been divided into use cases (Get, Add, Edit, Delete)
- Horizontally separated components have been connected together using interfaces (Controller with Service, Service with Repository)
- Use cases are independent of each other

Next question was about development process, I decided to try TDD for the first time (Due to lack of time you can see TDD only for Get and Add use cases)

Spent time: **~35 hours**

Lastly started to develop front-end and there are no special features in it, so there were no difficulties.

Spent time: **~15 hours**

# How to start application

```java -jar jar/best-friend-{version}.jar```