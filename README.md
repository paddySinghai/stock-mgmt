# stock-mgmt
Stock Management Case

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a system.

## Prerequisites
Things you need to get the project up and running on a local machine:
* Docker latest version

## Building and Running the application

**Rest Services**
 * Build the jar file using mvn clean install
 * Build the docker image of the same using docker build -f Dockerfile -tstock-mgmt .
 * Run the docker image on Tomcat using docker run -p 8080:8080 -t stock-mgmt .
 * REST services can be accessed on http://localhost:8080/api/stocks


**Front end**
 * Build the docker image of the same using  docker build -t stock-mgmt-fe:prod .
 * Run the docker image on Tomcat using  docker run -p 8000:80 stock-mgmt-fe:prod .
 * Open 'http://localhost:8000/' in the web browser to open the front end of the application
