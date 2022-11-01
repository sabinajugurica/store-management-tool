# Task: [Store Management Tool](https://github.com/sabinajugurica/store-management-tool) 

## Introduction

This project represents an API that acts as a store management tool. 

## Main functionalities:

- View all products
- View product by id 
- Create product 
- Change product's price (using query params)
- Change product's category (using body)
- Delete product

## Architecture

From architectural point of view, the app is a clasic API project. It has:
- [Controller](https://github.com/sabinajugurica/store-management-tool/blob/main/store/src/main/java/api/store/StoreController.java) - contains the structure for the main functionalities from the app 
- [Service](https://github.com/sabinajugurica/store-management-tool/blob/main/store/src/main/java/api/store/services/StoreService.java) - contains the logic of each functionality

In this project, I used JPA repository. Also, I used PostgreSQL for storing data. 
All the functionalities were tested using Postman. 

## [JUnit tests](https://github.com/sabinajugurica/store-management-tool/blob/main/store/src/test/java/api/store/StoreApplicationTests.java)

The tests are written for the StoreController class and they are covering the main functionalities. 

## Logs

The logs are stored in the Store.log file, inside the project. 
