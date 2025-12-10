# phasezero-catalog-service-backend
This project is a simple Product Catalog Service built with Spring Boot. It lets you add products, view them, update details, and delete them. No database needed — everything runs in memory only.

1. How to Run the Project
Step 1: Clone the repository
git clone https://github.com/your-username/phasezero-catalog-service.git
cd phasezero-catalog-service

Step 2: Run the project
1.mvn spring-boot:run
2.select the project right click on it then click run after click on java application.


The application will start at this port number:
http://localhost:8080
2. Technologies Used
* Java 17
* Spring Boot 3
* Spring Web
* Spring Validation
* Maven

3. this is s simple overview of this Project Structure:
controller/      → All API endpoints
service/         → Business logic
model/           → Product model
dto/             → Request body classes

4. API Endpoints
👉 Get all products
   GET /products

👉 Add a product
    POST /products
Example body:

{
  "partNumber": "P2001",
  "partName": "Hydraulic pump",
  "category": "Engine",
  "price": 2500.50,
  "stock": 100
}


👉 Get a product by part number

GET /products/{partNumber}

👉 Update a product

PUT /products/{partNumber}

👉 Delete a product

DELETE /products/{partNumber}

5. How to Test
Using Postman:

Open Postman

Choose the HTTP method

Enter URL (for example: http://localhost:8080/products)

For POST/PUT → Add JSON in “Body → raw → JSON”

Send request

Using curl:

Add a product:
