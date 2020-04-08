# Stock-Management-Module
The core function of the module is to implement RESTful Java based backend application to allow a user to input data about a list of stocks and also track changes in a stocks price when the user updates it.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a system.


### Built With:
1. Framework - SpringBoot
2. Dependency Management - Maven
3. Version Control - Git
4. Building images and running containers - Docker
5. Event Monitoring - Prometheus

### Installing

Clone the git repository in a local directory using git clone<repository_name> command.


### Deployment on Local Machine
1. Build the jar file using the command: mvn clean install.
2. Build the docker image of the same using the command: <B> docker build -f Dockerfile -tstock-mgmt .</B>
3. Image details can be viewed using <B> docker image ls </B>
4. Run the docker image using the command: <B> docker run -p 8080:8080 -t stock-mgmt . </B>
5. Service to create new Stock:: Request Type: POST || URL: http://localhost:8080/api/stocks 
6. Update the price of a single stock:: Request Type: PUT || URL: http://localhost:8080/api/stocks/{id}
7. Return the historical price changes of a stock:: Request Type: GET || URL: http://localhost:8080/api/stocks/{id}/history 
8. Return one stock from the list :: Request Type: GET || URL: http://localhost:8080/api/stocks/{id}
9. Return a list of stocks :: Request Type: GET || URL: http://localhost:8080/api/stocks
10. H2 console URL: http://localhost:8080/stockMgmt/h2/
11. Swagger UI URL: http://localhost:8080/swagger-ui.html
12. Prometheus URL: http://localhost:8080/actuator/prometheus 
