# Microservices Project README

This project demonstrates the creation and deployment of microservices using Spring Boot, Spring Data, Spring Cloud Gateway, and Netflix Eureka Server. It includes the following steps:

## 1. Customer Service

1. **Create the Customer Service Microservice:**
   - Create the Customer entity.
   - Create the CustomerRepository interface based on Spring Data.
   - Deploy the RESTful API of the microservice using Spring Data Rest.
   - Test the microservice.

## 2. Inventory Service

2. **Create the Inventory Service Microservice:**
   - Create the Product entity.
   - Create the ProductRepository interface based on Spring Data.
   - Deploy the RESTful API of the microservice using Spring Data Rest.
   - Test the microservice.

## 3. Gateway Service

3. **Create the Gateway Service using Spring Cloud Gateway:**

   ### Static Configuration Testing
   1. Test the service proxy using a static configuration based on the `application.yml` file.
   2. Test the service proxy using a static configuration based on a Java configuration.

## 4. Registry Service

4. **Create the Registry Service based on Netflix Eureka Server.**

   ### Dynamic Routing Configuration Testing
   5. Test the proxy using a dynamic route management configuration to the microservices registered in the Eureka Server.

## 6. Billing Service

6. **Create the Billing Service using Open Feign to communicate with Customer Service and Inventory Service.**

---

The project is organized into multiple microservices, each with specific functionalities. Follow the respective steps to set up, deploy, and test each microservice. Ensure you have the necessary dependencies and configurations in place for a successful deployment and testing.
