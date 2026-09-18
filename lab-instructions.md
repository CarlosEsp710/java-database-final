::page{title="Final Project: Retail Management Backend System"}

<img src="https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/IBM-CD0241EN-SkillsNetwork/images/IDSN-logo.png" width="200"> <br>

**Estimated time:** 90 minutes

Welcome to the Retail Inventory Backend Final Project development environment. This project is your opportunity to demonstrate your understanding of building a structured backend for a retail management system using Java, Spring Boot, and MySQL. The frontend has already been developed. In this final project, your job is to complete the backend components that support it.

## Objectives

- Design a proper domain model for retail inventory
- Implement Spring Boot models, repositories, services, and controllers
- Integrate and validate data using JPA and Hibernate
- Ensure the backend compiles without errors

All of your work on the final project should be completed from this environment.

::page{title="Important Security Information"}

Welcome to the Cloud IDE with Docker. This is where all of your development will take place. It has all of the tools you will need to use Docker.

It is important to understand that the lab environment is **ephemeral**. It only lives for a short while and then it will be destroyed. This makes it imperative that you push all changes made to your own GitHub repository so that it can be recreated in a new lab environment any time it is needed.

Also note that this environment is shared and therefore not secure. You should not store any personal information, usernames, passwords, or access tokens in this environment for any purpose.

## Your Task

1. If you haven&#39;t generated a **GitHub Personal Access Token** you should do so now. You will need it to push code back to your repository. It should have `repo` and `write` permissions, and set to expire in `60` days. When Git prompts you for a password in the Cloud IDE environment, use your Personal Access Token instead.

1. The environment may be recreated at any time so you may find that you have to perform the **Initialize Development Environment** step each time the environment is created.

1. Create a repository from the GitHub template provided for this lab in the next step.

::page{title="Create your own GitHub Repository"}

You will need your own repository in GitHub to complete the final project. We have provided a GitHub Template repository to create your own repository in your own GitHub account. No need to &#34;fork&#34; it as it has been set up as a Template. This will avoid confusion when making Pull Requests in the future.

## Your Task

1. In a browser, visit this GitHub repository:
	https://github.com/ibm-developer-skills-network/nxmpl-java-database-final-template

1. From the GitHub **Code** tab, click the green **Use this template** drop-down menu.


1. Select **Create a new repository** from the dropdown menu to create your own repository from this template.

	![template-create-new.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/uXSK8pJSdLEGyq4BGSF0Gw/template-create-new.png)

<br>

On the next screen, fill out these prompts following the steps and screenshot below:

1. Select your GitHub account from the dropdown list

1. Name the new repository: `java-database-final`. It is important that you use this name for some of the commands to work in this lab.

1. (Optional) Add a nice description to let people know what this repo is for

1. Make the repo **Public** so that others can see if (and grade it)

1. Click **Create repository** to create the repository in the GitHub account you have selected.

	![template-name.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/NhXrSRedE7JkHsooG2ITfw/template-name.png)


### Clone the repository
The next step is to clone the repository using the GitHub token you created previously. 
1. On the right-hand side of the page, click the green `Code` drop-down menu, and on the `Local` tab, click the `Copy url to clipboard` button to the right of the `https` URL.

	![template-forked-https.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/poIE1VGLsly4M2pTDAK5ow/template-forked-https.png)

2. Switch back to the **Skills Network Labs** browser tab.
3. Open a new terminal using the menu `Terminal` -> `New Terminal`.
4. In the terminal, type in the `git clone` command, followed by **a space**, then paste in the URL you just copied to the clipboard.

It should look something like the following:
```bash
git clone <url copied in previous step>
```

5. In the terminal, run the command below to export PROJECT_HOME to your github repository:

```bash
export PROJECT_HOME=/home/project/java-database-final
```

::page{title="Final Project Scenario"}

You have been asked by the inventory operations manager at your company to develop a Retail Inventory Backend microservice to support a multi-store retail business. The frontend user interface (UI) has already been developed by another team and will be used by internal administrators to manage store locations, inventory levels, products, and order tracking.

Since this is a backend microservice, it is expected to expose a well-defined REST API that the frontend UI and other microservices can call. Your service will enable features such as creating and managing physical store locations, registering and updating product information, tracking stock availability across stores, and managing incoming and outgoing orders.

You have also been informed that someone else has started on this project and has already scaffolded the Spring Boot structure and created a sample endpoint. You will receive a partially completed backend in your GitHub repository and will be expected to build out the rest of the functionality.

### Final Lab Phases

1. Part 1: Configure the project to work with databases
You will configure the backend to use MySQL for structured data (e.g., store and product details) and MongoDB for unstructured or flexible data (e.g., order logs, tracking metadata).

2. Part 2: Build the models
You will define the domain models (Store, Product, Customer, Inventory, Review, and other Order related models) using JPA annotations, including field constraints and relationships.

3. Part 3: Build the repositories and services
You will create JPA repositories and service classes to handle the business logic for CRUD operations and backend processing.

4. Part 4: Build the controllers for the endpoints
You will expose REST endpoints to support the frontend&#39;s needs, such as creating stores, listing products, updating inventory, and retrieving order details.

5. Part 5: Load sample data
You will write SQL and MongoDB scripts to populate the database with initial sample data.

6. Part 6: Build stored procedures
You will create create stored procedures for common queries (e.g., checking stock availability across stores).

7. Part 7: Integrate with frontend and run the project
You will wire up the backend with the pre-built frontend and run the application end-to-end to verify functionality.



::page{title="Part 1: Configure Project"}

Ensure you have cloned your fork of the template repository. The first step is to start the databases and populate the tables.

### Start MySQL Database

1. The first step is to create the MySQL instance in the IDE environment. Click the **Skills Network Toolbox** icon at the bottom of the left-hand panel. This will bring up another panel on the left. 
2. Expand `DATABASES`, and select `MySQL` in the databases category. This will open a new tab. 
3. Click `Create`.

	![mysql-create.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/HmCKIbnwB-1SqgjI7LJc1w/mysql-create.png)

4. The MySQL Database will start in a few minutes. Once it has started, click `MySQL CLI` in the `Summary` tab. This will open the MySQL CLI terminal at the bottom of the screen.

	![mysql-cli.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/C--UlvytyXBlkEIizmPt6Q/mysql-cli.png)

5. You can now enter all the **MySQL-based** commands in this lab in this terminal. This example shows the command to list all the databases.

	![mysql-terminal.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/_6y_5JSo6TwsSYRp2CLsNA/mysql-terminal.png)

6. You can find the username, password, and connection URL information in the `Connection Information` tab if you need to connect to this database outside the IDE environment.

	![mysql-connection-info.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/PVK_m1xOIi5DG9S7f0kF2Q/mysql-connection-info.png)


### Start the MongoDB Database

1. The next step is to create the MongoDB instance in the IDE environment. In the **Skills Network Toolbox** panel, under `DATABASES`, select `MongoDB`. This will open a new tab.
2. Click `Create`.

	![mongodb-create.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/YIheio7qP6z2fKYig5MMzA/mongodb-create.png)

3. The MongoDB Database will start in a few minutes. Once it has started, click `MongoDB CLI` in the `Summary` tab. This will open the MongoDB CLI terminal at the bottom of the screen.

	![mongodb-cli.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/CCfFusZqGK2lxxDCgOgcHg/mongodb-cli.png)

4. You can now enter any **MongoDB-based** commands in this terminal. This example shows the command to list all the databases.

	![mongodb-terminal.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/w4rLhDyxuHzuOxTpj-soNQ/mongodb-terminal.png)

5. Click the `Connection Information` tab. Here, you can find the username, password, and connection URL information if you need to connect to this database from outside the IDE environment.

	![mongodb-connection-info.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/zEMIq-WKuHsPsMggdlmGAg/mongodb-connection-info.png)

6. On this `Connection Information` tab, make note of the URL that starts with `mongodb://root` under the `MongoDB CLI Command` section of the page. (You could write it down manually, or copy it to Notepad for example).

	![mongodb-url.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/_O2vGUPFDOdEycnZYRZA0w/mongodb-url.png)


## Configure application.properties

Next you need to fill out the database properties in the `application.properties` file located in the **/home/project/java-database-final/back-end/src/main/resources/** directory.

1. Click the button below to open the `application.properties` file.

::openFile{path="/home/project/java-database-final/back-end/src/main/resources/application.properties"}

2. Add the following lines of code to the `application.properties` file, replacing the code that is already there.

```
spring.application.name=code

spring.datasource.url=jdbc:mysql://{MYSQL_HOST}/inventory?usessl=false
spring.datasource.username=root

spring.datasource.password={MY_SQL_PASSWORD}
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false

spring.data.mongodb.uri=mongodb://root:{MONGODB_PASSWORD}@{MONGODB_HOST}/reviews?authSource=admin

management.endpoint.health.show-details=always
management.health.db.enabled=true

 
```

3. Replace the values in the curly brackets `{}` with the real values from the `Connection Information` sections of the `MongoDB` and `MySQL` tabs as discussed in the labs.

Next, you need to add a dependency to the `pom.xml` file located in the **/home/project/java-database-final/back-end/** directory.

1. Use the button below to open the `pom.xml` file

::openFile{path="/home/project/java-database-final/back-end/pom.xml"}

2. Add the following lines of code to the `pom.xml` file. Add them within the `<dependencies>` section after the last `<dependency>` entry. 

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

::page{title="Part 2: Create Models"}

We are providing some models for this application. You are tasked to build out the following models based on the specifications provided here:


### Customer Model
1. Open the `Customer.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Model/Customer.java"}

2. Add the following attributes along with getters and setters:
- **id**: `private long`; Auto increment 
-   **name**: `private String`; cannot be empty
    
-   **email**: `private String`; cannot be empty
    
-   **phone**: `private String`; cannot be empty

3. A customer can have multiple orders. Use the `@OneToMany` annotation.

	Hint:
	- Use `@Id` and `@GeneratedValue(strategy  =  GenerationType.IDENTITY)` for id to auto increment it and set as primary key
	- Use the `@NotNull` annotation for the `name`, `email`, and `phone` fields. Example: `@NotNull(message = "Email cannot be null")`.
	- Use `@OneToMany` annotation. Example: `@OneToMany (mappedBy = "customer", fetch = FetchType.EAGER)` to create a one-to-many relationship with orders.
	-   Apply the `@JsonManagedReference` annotation to ensure proper JSON serialization.
	- Add `@Entity` annotation above class name

### Inventory Model
1. Open the `Inventory.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Model/Inventory.java"}

2.  Add the following attributes along with getters and setters:
-   **id**: `private long`; represents the product id in the inventory

-   **product**: `private Product`; represents the product in the inventory
    
-   **store**: `private Store`; represents the store where the inventory is stored
    
-   **stockLevel**: `private Integer`; represents the current stock level of the product at the store

	Hint: 
	- Use `@Id` and `@GeneratedValue(strategy  =  GenerationType.IDENTITY)` for id to auto increment it and set it as primary key

3. Set up relationships:

-   **Product**: An inventory entry is associated with one product. Use the `@ManyToOne` annotation and link it to the `Product` entity.  
   
	Hint:
	-   Use the `@JsonBackReference("inventory-product")` to handle the bidirectional relationship correctly and prevent circular references during JSON serialization.
    
-   **Store**: An inventory entry is also associated with one store. Use the `@ManyToOne` annotation and link it to the `Store` entity.  

	Hint:
	-   Use `@JsonBackReference("inventory-store")` to manage the relationship with the store.
    


4. Use the `@JoinColumn` annotation to specify the foreign key column names:

-   For the `product` field, use `@JoinColumn(name = "product_id")`.
    
-   For the `store` field, use `@JoinColumn(name = "store_id")`.

	Hint:
	-   Create a constructor that takes in a `Product`, `Store`, and `Integer stockLevel` to initialize the `Inventory` object.
	- Add `@Entity` annotation above class name


### Product Model

1. Open the `Product.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Model/Product.java"}

2. Add the following attributes along with getters and setters:

-   **id**: `private long`; auto increment

-   **name**: `private String`; cannot be empty
    
-   **category**: `private String`; cannot be empty
    
-   **price**: `private Double`; cannot be empty
    
-   **sku**: `private String`; cannot be empty, must be unique

	Hint: 
    - Use `@Id` and `@GeneratedValue(strategy  =  GenerationType.IDENTITY)` for id to auto increment it and set it as primary key
    - Use `@NotNull` for the `name`, `category`, `price`, and `sku` fields to ensure they are not null when creating a `Product` object.
    - Use the `@Table` annotation with a `uniqueConstraints` attribute to enforce uniqueness on the `sku` column. Example: `@Table(name  =  "product",
uniqueConstraints  = @UniqueConstraint(columnNames  =  "sku"))`
    -  Add `@Entity` annotation above class name

3. Set up relationships:

-   **Inventory**: A product can have multiple inventory entries. Use the `@OneToMany` annotation to reflect this relationship.  
	
	Hint:
    
    -   Use the `mappedBy` attribute to indicate the relationship with the `Inventory` class.
        
    -   Apply `@JsonManagedReference("inventory-product")` to handle the bidirectional relationship and prevent circular references during JSON serialization.

    
### Store Model

1. Open the `Store.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Model/Store.java"}

2. Add the following attributes along with getters and setters:

-   **id**: `private long`; auto increment

-   **name**: `private String`; cannot be empty
    
-   **address**: `private String`; cannot be empty
 
	Hint: 
    - Use `@Id` and `@GeneratedValue(strategy  =  GenerationType.IDENTITY)` for id to auto increment it and set it as primary key

3. Set up relationships:

-   **Inventory**: A store can have multiple inventory entries. Use the `@OneToMany` annotation to reflect this relationship.  
	
	Hint:
    
    -   Use the `mappedBy` attribute to indicate the relationship with the `Inventory` class.
        
    -   Apply `@JsonManagedReference("inventory-store")` to handle the bidirectional relationship and prevent circular references during JSON serialization.

4. Add validation to ensure that the `name` and `address` are not null or blank:  

	Hint:
    -   Use `@NotNull` to ensure the fields are not null.
    -   Use `@NotBlank` to ensure the fields are not empty or just whitespace.
5. Add a constructor to initialize the `Store` object with `name` and `address` values.  

	Hint:
    -   Create a constructor that initializes the `name` and `address` fields for the `Store` object.
   
    -    Add `@Entity` annotation above class name

### OrderDetails Model


1.  Open the `OrderDetails.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Model/OrderDetails.java"}

2.  Add the following attributes along with getters and setters:
    

-   **id**: `private Long`; auto increment
    
-   **customer**: `private Customer`; maps to the customer who placed the order
    
-   **store**: `private Store`; maps to the store from where the order was placed
    
-   **totalPrice**: `private Double`; total price of the order
    
-   **date**: `private LocalDateTime`; date and time when the order was placed
    
-   **orderItems**: `private List<OrderItem>`; list of items in the order
    
    Hint:
    
    -   Use `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)` for `id` to auto increment it and set it as the primary key
        
    -   Use `@Entity` annotation above the class name to declare it as a JPA entity
        
    -   Use `@ManyToOne` with `@JoinColumn(name = "customer_id")` for the `customer` field to define the foreign key and apply `@JsonManagedReference` to handle JSON serialization
        
    -   Use `@ManyToOne` with `@JoinColumn(name = "store_id")` for the `store` field and apply `@JsonManagedReference`
        
    -   Use `@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)` for the `orderItems` field to establish the relationship with the `OrderItem` class, and apply `@JsonManagedReference` to prevent circular references during JSON serialization
        

3.  Add the necessary constructors:
    
-   A no-argument constructor
    
-   A parameterized constructor that takes `Customer`, `Store`, `Double totalPrice`, and `LocalDateTime date` as parameters


### OrderItem Model

1.  Open the `OrderItem.java` file   

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Model/OrderItem.java"}

2.  Add the following attributes along with getters and setters:
    

-   **id**: `private Long`; auto increment
    
-   **order**: `private OrderDetails`; refers to the order this item belongs to
    
-   **product**: `private Product`; refers to the product in the order
    
-   **quantity**: `private Integer`; quantity of the product in the order
    
-   **price**: `private Double`; price of the product at the time of the order
    
    Hint:
    -   Use `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)` for `id` to auto increment it and set it as the primary key
    -   Use `@Entity` annotation above the class name to declare it as a JPA entity
    -   Use `@ManyToOne` with `@JoinColumn(name = "order_id")` for the `order` field and apply `@JsonManagedReference` to manage bidirectional serialization
    -   Use `@ManyToOne` with `@JoinColumn(name = "product_id")` for the `product` field and apply `@JsonManagedReference` to prevent circular references
        

3.  Add the necessary constructors:
    
-   A no-argument constructor
    
-   A parameterized constructor that takes `OrderDetails order`, `Product product`, `Integer quantity`, and `Double price` as parameters

### Review Model

1. Open the `Review.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Model/Review.java"}

2. Add the following attributes along with getters and setters:

-   **customerId**: `private Long`; represents the customer who created the review, cannot be empty
    
-   **productId**: `private Long`; represents the product being reviewed, cannot be empty
    
-   **storeId**: `private Long`; represents the store associated with the product, cannot be empty
    
-   **rating**: `private Integer`; represents the rating given to the product (out of 5), cannot be empty
    
-   **comment**: `private String`; an optional comment on the product


3. Set up validation:

-   Apply `@NotNull` annotation to the `customerId`, `productId`, `storeId`, and `rating` fields to ensure they cannot be null.  
    
	Hint:
    -   Example: `@NotNull(message = "Customer cannot be null")`
    
4. Use the `@Document` annotation to indicate that this class represents a MongoDB document.  

	Hint:
    -   Annotate the class with `@Document(collection = "reviews")` to specify the MongoDB collection for the `Review` entity.
    
5. Define a constructor to initialize a `Review` object with `customerId`, `productId`, `storeId`, `rating`, and an optional `comment`.  
  
	Hint:
	-    The constructor should accept values for all required fields (`customerId`, `productId`, `storeId`, `rating`) and optionally for `comment`.

6. Ensure the `id` field is the unique identifier used by MongoDB:

-   The `id` field is of type `String` and will be automatically generated by MongoDB. Use `@Id` annotation to mark it as the primary key.

::page{title="Part 3: Create Repositories and Services"}

You are going to create repositories and services in this layer. Repositories handle direct communication with the database, providing methods to fetch, save, update, and delete data, often using JPA. Services sit above repositories and contain the business logic, coordinating between repositories and controllers to process data and enforce rules before returning results.

You will also notice the use of Data Transfer Objects or DTOs. These are classes that are used to transfer data between different layers of the application, such as between the service and controller layers. In the repository layer, DTOs are often used in custom queries to fetch specific fields efficiently, improving performance and security. This approach reduces memory usage, keeps APIs clean, and separates internal entity design from external data structures.

### Customer Repository 

1. Open the `CustomerRepository.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Repo/CustomerRepository.java"}

2. Create a repository for the `Customer` model by extending `JpaRepository`. This will allow for basic CRUD operations without needing to implement the methods manually.

3. Add the following methods:

  -    **findByEmail**: Find a customer by their email address.
    
          -   Return type: `Customer`
        
          -   Parameter: `String email`
        
   -   **findById**: Find a customer by their ID.
    
       -   Return type: `Customer`
        
       -   Parameter: `Long id`
        
Hint:
- Extend `JpaRepository<Customer, Long>` to inherit basic CRUD functionality.
-   Declare custom query methods like `findByEmail` and `findById` for additional queries.
 
 For Example: 
```Customer  findByEmail(String  email);```
    


### Inventory Repository 

1. Open the `InventoryRepository.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Repo/InventoryRepository.java"}

2. Add the following methods:

-   **findByProductIdandStoreId**: Find an inventory record by its product ID and store ID.
    
    -   Return type: `Inventory`
        
    -   Parameters: `Long productId`, `Long storeId`
        
    -   Query: `"SELECT i FROM Inventory i WHERE i.product.id = :productId AND i.store.id = :storeId"`
        
-   **findByStore_Id**: Find a list of inventory records for a specific store.
    
    -   Return type: `List<Inventory>`
        
    -   Parameter: `Long storeId`
        
-   **deleteByProductId**: Delete all inventory records related to a specific product ID.
    
    -   Return type: `void`
        
    -   Parameter: `Long productId`
        
    -   Use `@Modifying` and `@Transactional` annotations to modify the database and ensure the transaction is managed correctly.
       

Hint:

-   Extend `JpaRepository<Inventory, Long>` for basic CRUD functionality.
    
-   Use `@Query` for custom queries, `@Modifying` for update/delete queries, and `@Transactional` to handle transactions.


### OrderDetails Repository

1. Open the `OrderDetailsRepository.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Repo/OrderDetailsRepository.java"}

2. Create a repository for the `OrderDetails` model by extending `JpaRepository`. This will allow for basic CRUD operations without needing to implement the methods manually.

Hint:

-   Extend `JpaRepository<OrderDetails, Long>` to inherit basic CRUD functionality.
    
-   No custom methods are required for this repository as it handles basic operations for the `OrderDetails` model.


### OrderItem Repository

1. Open the `OrderItemRepository.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Repo/OrderItemRepository.java"}

2. Create a repository for the `OrderItem` model by extending `JpaRepository`. This will allow for basic CRUD operations without needing to implement the methods manually.

Hint:

-   Extend `JpaRepository<OrderItem, Long>` to inherit basic CRUD functionality.
    
-   No custom methods are required for this repository as it handles basic operations for the `OrderItem` model.



### Product Repository

1. Open the `ProductRepository.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Repo/ProductRepository.java"}

2. Create a repository for the `Product` model by extending `JpaRepository`. This will allow for basic CRUD operations and custom queries.
3. Add the following methods:

-   **findAll**: Find all products.
    
    -   Return type: `List<Product>`
    
    Hint: Use the built-in `findAll` method from `JpaRepository`.
        
-   **findByCategory**: Find products by their category.
    
    -   Return type: `List<Product>`
        
    -   Parameter: `String category`
    
     Hint: Use the `findBy` convention with `category`.
        
-   **findByPriceBetween**: Find products within a price range.
    
    -   Return type: `List<Product>`
        
    -   Parameters: `Double minPrice`, `Double maxPrice`
    
    Hint: Use the `findBy` convention with `priceBetween`.
        
-   **findBySku**: Find a product by its SKU.
    
    -   Return type: `List<Product>`
        
    -   Parameter: `String sku`

    Hint: Use the `findBy` convention with `sku`.
-   **findByName**: Find a product by its name.
    
    -   Return type: `Product`
        
    -   Parameter: `String name`

    Hint: Use the `findBy` convention with `name`.        
-   **findById**: Find a product by its ID.
    
    -   Return type: `Product`
        
    -   Parameter: `Long id`
   
    Hint: Use the `findBy` convention with `id`.    
-   **findByNameLike**: Find products by a name pattern for a specific store.
    
    -   Return type: `List<Product>`
        
    -   Parameters: `Long storeId`, `String pname`
        
    -   Use `@Query` annotation and write the following query 
     ```@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND i.product.category = :category")```
        
-   **findByNameAndCategory**: Find products by name and category for a specific store.
    
    -   Return type: `List<Product>`
        
    -   Parameters: `Long storeId`, `String pname`, `String category`
    
    -   Use `@Query` annotation and write the following query 
     ```@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND i.product.category = :category")``` to combine `name` and `category` filters.
        
-   **findByCategoryAndStoreId**: Find products by category for a specific store.
    
    -   Return type: `List<Product>`
        
    -   Parameters: `Long storeId`, `String category`
        
    -   Use `@Query` annotation and write the following query  `@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND i.product.category = :category")` to filter by both `category` and `storeId`.
        
-   **findProductBySubName**: Find products by a name pattern (ignoring case).
    
    -   Return type: `List<Product>`
        
    -   Parameter: `String pname`
        
    -   Use `@Query` annotation and write the following query  `@Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%'))")`  for partial matching in `name`.
        
-   **findProductsByStoreId**: Find all products for a specific store.
    
    -   Return type: `List<Product>`
        
    -   Parameter: `Long storeId`
    
    -   Use `@Query` annotation and write the following query  `@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId")`  to join `Inventory` and filter by `storeId`.
        
-   **findProductByCategory**: Find products by category for a specific store.
    
    -   Return type: `List<Product>`
        
    -   Parameters: `String category`, `Long storeId`
        
    -   Use `@Query` annotation and write the following query  `@Query("SELECT i.product FROM Inventory i WHERE i.product.category = :category and i.store.id = :storeId")` and filter by `storeId`. to filter by `category` and `storeId`.
        
-   **findProductBySubNameAndCategory**: Find products by a name pattern and category.
    
    -   Return type: `List<Product>`
        
    -   Parameters: `String pname`, `String category`
        
    -   Use `@Query` annotation and write the following query  `@Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND i.category = :category")` to match both `name` and `category` criteria.
        

Hint:

-   Extend `JpaRepository<Product, Long>` to inherit basic CRUD functionality.
    
-   Use `@Query` for more complex queries that involve custom conditions.
    
-   Use `LOWER` in queries to make case-insensitive searches.
    
-   Use `CONCAT` for partial matching on product names.


### Review Repository

1. Open the `ReviewRepository.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Repo/ReviewRepository.java"}

2. Create a repository for the `Review` model by extending `MongoRepository`. This will allow for basic CRUD operations and custom queries.

3. Add the following method:
- **findByStoreIdAndProductId**: Retrieve reviews for a specific product and store.
   - Return type: `List <Review>`
   
   -  Parameters: `Long  storeId`, `Long  productId`

   - Use the `findBy` convention with `StoreId` and `ProductId` to filter reviews.

Hint:

-   Extend `MongoRepository<Review, String>` to work with MongoDB for review-related CRUD operations.
    
-   Use `findBy` naming convention to create queries that retrieve data based on specific field values (in this case, `storeId` and `productId`).


### Store Repository

1. Open the `StoreRepository.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Repo/StoreRepository.java"}

2. Create a repository for the `Store` model by extending `JpaRepository`. This will allow for basic CRUD operations and custom queries.

3. Add the following methods:

-   **findByid**: Retrieve a store by its ID.
    -  Return Type: `Store`
    
    -   Parameter: `Long id`
  
    -   Use `findByid` to fetch a store based on its ID.
        
-   **findBySubName**: Retrieve stores whose name contains a given substring.
    
    -  Return Type: `List<Store>`
    
    -   Parameter: `String pname`
    -   Use `@Query` with `LOWER` and `CONCAT` to create a case-insensitive search based on a substring of the store name.

Hint
-   Extend `JpaRepository<Store, Long>` to interact with the `Store` table.
    
-   For custom queries, use `@Query` annotation with JPQL (Java Persistence Query Language).
    
-   Use `LOWER` to make the search case-insensitive, and `CONCAT` for substring matching.

### Order Service

1. Open the `OrderService.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Service/OrderService.java"}

2. Autowire the necessary repositories and keep the access specifier as private 
   For Example: 
   ```java
   @Autowired
    private ProductRepository productRepository;
   ```
    -   `ProductRepository` for accessing the product data.
        
    -   `InventoryRepository` for accessing inventory data.
        
    -   `CustomerRepository` for validating product IDs and inventory.
    -   `StoreRepository` for accessing the store data.
    -   `OrderDetailsRepository` for saving the order details data.
    -   `OrderItemRepository` for saving the item ordered details data.

3. Create a service class to manage the order process, including retrieving customer data, creating orders, and saving order items.
  
	Hint: Add `@Service` annotation above the class definition

4. Add the following logic:

-   **saveOrder Method**: This method processes a customer&#39;s order, including saving the order details and associated items.
    
    -  Parameters: `PlaceOrderRequestDTO placeOrderRequest` (Request data for placing an order)
        
    -  Return Type: `void` (This method doesn&#39;t return anything, it just processes the order)
        
    -   1.  **Retrieve or Create the Customer**: Check if the customer already exists by their email using `findByEmail`. If the customer exists, use the existing customer, otherwise, create a new `Customer` and save it to the repository.
            
        
             Hint: Use `customerRepository.findByEmail()` to check for an existing customer and `customerRepository.save()` to save a new customer.
            
    -   2.  **Retrieve the Store**: Fetch the store by ID from `storeRepository`. If the store doesn&#39;t exist, throw an exception.
            
        
            Hint: Use `storeRepository.findById()` to retrieve the store.
            
    -   3.  **Create OrderDetails**: Create a new `OrderDetails` object and set customer, store, total price, and the current datetime.
            
        
            Hint: Set the order date with `java.time.LocalDateTime.now()` and save the order with `orderDetailsRepository.save()`.
            
    -   4.  **Create and Save OrderItems**: For each product purchased, find the corresponding `Inventory`, update its stock level, and save the changes.
            
        
             Hint:  Use `inventoryRepository.findByProductIdandStoreId()` to get the inventory and `inventoryRepository.save()` to update it.
            
     
       - 5. Create `OrderItem` for each product and associate it with the `OrderDetails`.
            
            Hint: Use `orderItemRepository.save()` to save each order item.


Additional Hints: 

1.  **Managing Customer Creation**: If the customer is not found in the database, a new `Customer` object will be created and saved to the repository. Make sure to handle the case where the customer already exists and reuse the existing `Customer`.
    
2.  **Store Retrieval**: Always check if the store is present in the database by checking the `Optional` value returned by `findById`. If not, throw a runtime exception to handle the error.
    
3.  **Order Details**: Ensure that the `totalPrice` is passed correctly from the request data. It's also important to store the current timestamp for when the order is created. Use `java.time.LocalDateTime.now()` to capture the time.
    
4.  **Order Item Creation**: For each product in the `purchaseProduct` list, create an `OrderItem`, and ensure the inventory is updated accordingly. Decrease the stock level of the product in the store&#39;s inventory after each purchase.
    
5.  **Stock Level Management**: Be mindful of inventory changes. If the product&#39;s stock level is updated (decreased in this case), remember to save the modified `Inventory` object back to the repository.
    
6.  **Handling Multiple Items**: If the order contains multiple items, iterate through each product, check the inventory, adjust the stock level, and store each corresponding `OrderItem`.
    

### ServiceClass

1. Open the `ServiceClass.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Service/ServiceClass.java"}

2. Create a service class to validate inventory and products, and fetch related data.
  
	Hint: Add `@Service` annotation above the class definition

3. Declare necessary repository to be used as private
   For Example:
    ```java
    private final InventoryRepository inventoryRepository;
    ```
    -   `ProductRepository` for accessing the product data.
        
    -   `InventoryRepository` for accessing inventory data.
   
3. Add the following logic:
-   **validateInventory**: This method checks whether an inventory record exists for a given product and store combination.
    
    -   Parameters: `Inventory inventory` (The inventory object you want to validate)
        
    -   Return Type: `boolean` (Returns `false` if an inventory record already exists for the given product and store, otherwise `true`)
        
    -   **Hint**: Use `inventoryRepository.findByProductIdandStoreId()` to check for existing inventory records. Return `false` if the inventory already exists, and `true` otherwise.
        
-   **validateProduct**: This method checks whether a product exists by its name.
    
    -   Parameters: `Product product` (The product you want to validate)
        
    -   Return Type: `boolean` (Returns `false` if a product with the same name already exists, otherwise `true`)
        
    -   **Hint**: Use `productRepository.findByName()` to check if a product with the same name already exists in the database. If it exists, return `false`; otherwise, return `true`.
        
-   **ValidateProductId**: This method validates whether a product exists by its ID.
    
    -   Parameters: `long id` (The product ID you want to validate)
        
    -   Return Type: `boolean` (Returns `false` if the product does not exist with the given ID, otherwise `true`)
        
    -   **Hint**: Use `productRepository.findByid()` to check if the product exists. If the product is `null`, return `false`.
        
-   **getInventoryId**: This method fetches the inventory record for a given product and store combination.
    
    -   Parameters: `Inventory inventory` (The inventory object to search for)
        
    -   Return Type: `Inventory` (Returns the found inventory record for the given product and store combination)
        
    -   **Hint**: Use `inventoryRepository.findByProductIdandStoreId()` to get the inventory record.


#### Additional Hints:

1.  **validateInventory Method**:
    
    -   Use this method to ensure there&#39;s no duplicate inventory entry for a product-store pair. If a product is already associated with the store, it will return `false`, meaning no new inventory can be added.
        
2.  **validateProduct Method**:
    
    -   This check ensures that no two products with the same name exist in your product database. You can expand this validation to check for other attributes if needed (e.g., SKU).
        
3.  **ValidateProductId Method**:
    
    -   This method helps ensure the product exists by its ID. Before attempting to create an order or any other action, you can use this method to validate the existence of a product with a given ID.
        
4.  **getInventoryId Method**:

    -   This method is useful when you need to retrieve an existing inventory record for a product in a specific store. It can be used when updating stock levels or performing any inventory-related checks.
----------

::page{title="Part 4: Create Controllers"}

Controllers handle incoming HTTP requests, map them to appropriate service methods, and return responses to the client. They act as the entry point of the application, managing the flow between the frontend and backend while keeping the business logic separate.

### Inventory Controller
**Purpose**: This controller handles the CRUD operations for managing inventory. It provides endpoints for updating, saving, searching, and validating inventory and products.


1. Open the `InventoryController.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Controller/InventoryController.java"}

2. **Set Up the Controller Class**
 -   Annotate the class with `@RestController` to designate it as a REST controller for handling HTTP requests.
    
-   Use `@RequestMapping("/inventory")` to set the base URL path for all methods in this controller.

3. **Autowired Dependencies**

-   Autowire the necessary services and repositories:
    
    -   `ProductRepository` for accessing the product data.
        
    -   `InventoryRepository` for accessing inventory data.
        
    -   `ServiceClass` for validating product IDs and inventory.

4. **Define the `updateInventory` Method**

-   Annotate this method with `@PutMapping`.
    
-   The method should accept a request body of type `CombinedRequest`, which contains both a `Product` and `Inventory`.
- It should return a `Map<String, String>`.
-   Validate the product ID.
**Hint:** Use method created in `ServiceClass` to validate productId. If the product doesn&#39;t exist, return an error message.
    
-   Update the `Product` and `Inventory` if the product ID is valid:
    
    -   If the `Inventory` exists for the product, update it and save the changes also return a key `message` with value "Successfully updated product"
        
    -   If the `Inventory` doesn&#39;t exist for the product, return a key `message` with value "No data available".
        
-   Catch any exceptions (e.g., `DataIntegrityViolationException`) and handle them appropriately.

5. **Define the `saveInventory` Method**

-   Annotate this method with `@PostMapping`.
    
-   The method should accept an `Inventory` object in the request body.
- The method should return `Map<String, String>`
-   Check if the inventory already exists.
 **Hint:** Use the method `validateInventory(inventory)` created in  `ServiceClass` to validate inventory.
    
    -   If it exists, return a `message` saying that the data is already present.
        
    -   If it doesn&#39;t exist, save the `Inventory` object to the repository and return `message` saying `data saved successfully`
        
-   Catch any exceptions related to data integrity or other errors and handle them appropriately.

6. **Define the `getAllProducts` Method**
-   Annotate this method with `@GetMapping("/{storeid}")` to retrieve products for a specific store.
    
-   The method should accept a `storeId` as a path variable and fetch the products for that store.
    **Hint:** Call the `findProductsByStoreId(storeid)` method on the `ProductRepository` to get the products belonging to the store with the given `storeid`.
- The method should return `Map<String, Object>`
-   Return the list of products as part of a map with the key `"products"`.
7. **Define the `getProductName` Method**

-   Annotate this method with `@GetMapping("filter/{category}/{name}/{storeid}")`.
    
-   This method should filter products by category and name.
- The method should return `Map<String, Object>`
-   If either `category` or `name` is `"null"`, adjust the filtering logic accordingly:
    
    -   If `category` is `"null"`, filter by product name only.
        
	**Hint:** Call the `findByNameLike(storeid, name)` method on the `ProductRepository` to get the filtered products by name.
        
    -   If `name` is `"null"`, filter by category only.
         
	**Hint:** Call the `findByCategoryAndStoreId` method on the `ProductRepository` to get the filtered products by category.
         
    -   If both `category` and `name` are provided, filter products by both parameters.
        
	**Hint:** Call the `findByNameAndCategory` method on the `ProductRepository` to get the filtered products by name and category.
        
-   Return the filtered products as part of the response map with the key `"product"`.
    

8. **Define the `searchProduct` Method**

-   Annotate this method with `@GetMapping("search/{name}/{storeId}")`.
-   The method should return `Map<String, Object>`     
-   The method should search for products by name within a specific store.

	**Hint:** Use method `findByNameLike(storeId, name)` on the `ProductRepository` to search for products with names that match the `name` parameter.
    
-   Return the products found in the response map with the key `"product"`.
    

9. **Define the `removeProduct` Method**

-   Annotate this method with `@DeleteMapping("/{id}")`.
-   The method should return `Map<String, String>`         
-   This method should delete a product by its `id`.

	**Hint:** Use method `ValidateProductId(id)` on the `ServiceClass`  to check if the product exists. If it doesn&#39;t exist, return a message saying the product not present in database.
    
-   If the product exists, delete the corresponding `Inventory` entry 

    **Hint:** Use method `deleteByProductId(id)` on the `InventoryRepository`.
    
-   Return a success message with key `message`indicating that the product was deleted.
    
10. **Define the `validateQuantity` Method**

-   Annotate this method with `@GetMapping("validate/{quantity}/{storeId}/{productId}")`.
-   The method should return `boolean`             .
-   This method should validate if a specified quantity of a product is available in stock at a given store.
- Retrieve the inventory for the product and store.

	**Hint:**   Use method `findByProductIdandStoreId(productId, storeId)` on the `InventoryRepository`.
    
-   If the stock level is greater than or equal to the requested quantity, return `true`. Otherwise, return `false`.

### Product Controller
**Purpose**: This controller manages the CRUD operations related to the `Product` entity in your application. Here&#39;s how to implement it step by step.


1. Open the `ProductController.java` file


::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Controller/ProductController.java"}

2. **Set Up the Controller Class**
    
-   **Annotate the class with `@RestController`** to denote it as a controller that handles REST API calls.
    
-   **Map the class to the `/product` URL** by using `@RequestMapping("/product")`.

3. **Autowired Dependencies**

- You need the following dependencies injected via `@Autowired`:

  -   **`ProductRepository`**: For interacting with the product data in the database.
    
  -   **`ServiceClass`**: For validating product data and checking business logic like product existence.
    
  -   **`InventoryRepository`**: To manage the inventory associated with the products.
    
4. **Define the `addProduct` Method**

-   **`@PostMapping`**: This method will handle POST requests to add a new product.
- This method will return `Map<String, String>`
    
-   **Request Body**: Accept a `Product` object from the request body.
    
-   **Validation**: Check if the product already exists.
    
	**Hint:** Use method `validateProduct()` on the `ServiceClass`. 
-   **Save Product**: If the product is valid, save it to the database. 
    
	**Hint:** Use `save()` method of `ProductRepository` to save.

- After saving the product return a success message with key `message`.
-   **Handle Errors**: Catch exceptions such as `DataIntegrityViolationException` for scenarios like unique SKU violations.

5. **Define the `getProductbyId` Method**

-   **`@GetMapping("/product/{id}")`**: This method will handle GET requests to retrieve a product by its `id`.
- This method will return `Map<String, Object>`
    
-   **Path Variable**: Use `@PathVariable` to accept the product `id` in the URL.
    
-   **Return Product**: Fetch the product and return it in a map with key `products`.
     
	 **Hint:** Use method `findById(id)` of `ProductRepository` to fetch product.

6. **Define the `updateProduct` Method**

-   **`@PutMapping`**: This method will handle PUT requests to update an existing product.
  - This method will return `Map<String, String>`

-   **Request Body**: Accept a `Product` object to update.
    
-   **Save Product**: Save the updated product.
   
	**Hint:** Use `save()` method of `ProductRepository` to save the updated Product.

- After updating the product return a success message with key `message`.

-   **Error Handling**: Catch any errors during the save operation.

7. **Define the `filterbyCategoryProduct` Method**

-   **`@GetMapping("/category/{name}/{category}")`**: This method filters products based on their `name` and `category`.
  - This method will return `Map<String, Object>`
    
-   **Handle Null Parameters**: If `name` or `category` is `"null"`, apply conditional filtering logic accordingly.
    
-   **Return Filtered Products**: Fetch products using repository methods like `findByCategory()`, `findByCategory(category)` or `findProductBySubNameAndCategory()` based on the provided filters and return in with key `products`

8. **Define the `listProduct` Method**

-   **`@GetMapping`**: This method will handle GET requests to retrieve all products.
  - This method will return `Map<String, Object>`    
-   **Return All Products**: Fetch and return all products with key `products`
   
	**Hint:** Use `findAll()` method of `ProductRepository` to fetch all the product.

9. **Define the `getProductbyCategoryAndStoreId` Method**

-   **`@GetMapping("filter/{category}/{storeid}")`**: This method will filter products by `category` and `storeId`.
  - This method will return `Map<String, Object>`    
-   **Fetch Products**: Retrieve all the products by `category` & `storeId` and return with key `product`

	**Hint:**  Use method `findProductByCategory()`  of `ProductRepository.` to retrieve all the products by `category` and `storeId`

10. **Define the `deleteProduct` Method**

-   **`@DeleteMapping("/{id}")`**: This method will handle DELETE requests to remove a product by its `id`.
  - This method will return `Map<String, String>`    
    
-   **Validation**:  Check if the product exists before deleting.

	**Hint:** Use `ValidateProductId()` method of `ServiceClass` to validate product.
    
-   **Delete Product**: As `Inventory` is mapped with `Product` using foreign key constraint, delete from `Inventory`  table first and then from `Product` table.

	**Hint:** Use method `deleteByProductId(id)` of `inventoryRepository` to remove the inventory entry and use  method `deleteById(id)` of `productRepository` to delete the product.

-   Return a success message with key `message`indicating that the product was deleted.
11. **Define the `searchProduct` Method**

-   **`@GetMapping("/searchProduct/{name}")`**: This method will search for products by their `name`.
  - This method will return `Map<String, Object>`        
-   **Return Search Results**: Search for products by name an return with key `products`

	**Hint:** Use method `findProductBySubName()` of `ProductRepository`to search products.


### Review Controller

**Purpose**: The `ReviewController` handles endpoints for retrieving reviews for products in a store. It provides methods for getting all reviews or filtered reviews by store ID and product ID, with customer information associated with each review.

1. Open the `ReviewController.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Controller/ReviewController.java"}


1. **Set Up the Controller Class**

-   **Action**: Annotate the class with `@RestController` to designate it as a REST controller for handling HTTP requests.
    
-   **URL Mapping**: Use `@RequestMapping("/reviews")` to define the base URL for all methods in this controller.
    

2. **Autowired Dependencies**

-   **Action**: Autowire the necessary repositories:
    
    -   `ReviewRepository` for accessing the review data.
        
    -   `CustomerRepository` for retrieving customer details linked with reviews.
        
3. **Define the `getReviews` Method**

-   **URL Mapping**: Use `@GetMapping("/{storeId}/{productId}")` to create an endpoint that retrieves reviews for a specific product in a store by `storeId` and `productId`.

-    Method will return a `Map<String, Object>`
-   **Path Variables**:
    
    -   `storeId`: The ID of the store.
        
    -   `productId`: The ID of the product.
   
-   **Logic** : 
    - First retreive all the reviews from the database of a specific product of a store.
    **Hint:** Use method `findByStoreIdAndProductId` of `ReviewRepository` to fetch all the reviews of a specific product of a store.
    - Now from all the recevied reviews filter remove the unwanted data and keep `comment`, `rating`. Now add `name` of the customer to the table using `customer id` field in the reviews.
  **Hint:** Use method `findByid(review.getCustomerId())` of `CustomerRepository`
-   **Return Key**: The response will include a key named **`reviews`**. The value for this key will be a list of review objects, each containing the review comment, rating, and the name of the customer who wrote the review.
    
    -   **Customer Name Key**: Each review object will include a key named **`customerName`**, which will either contain the customer&#39;s name or `"Unknown"` if no customer is found.
      
    
### Store Controller  
**Purpose**: The `StoreController` handles the operations related to stores, including adding a new store, validating an existing store, and placing an order. It integrates with the `StoreRepository` and the `OrderService` for managing store and order operations.

1. Open the `StoreController.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Controller/StoreController.java"}


1. **Set Up the Controller Class**

-   **Action**: Annotate the class with `@RestController` to designate it as a REST controller for handling HTTP requests.
    
-   **URL Mapping**: Use `@RequestMapping("/store")` to set the base URL path for all methods in this controller.

2. **Autowired Dependencies**

-   **Action**: Autowire the necessary repositories and services:
    
    -   `StoreRepository` for accessing store data.
        
    -   `OrderService` for handling order-related functionality.

3. **Define the `addStore` Method**

-   **URL Mapping**: Use `@PostMapping` to create an endpoint for adding a new store.
    
-   **Request Body**: This method should accept a `Store` object in the request body.
    
-   **Return Key**: The response will include a key named **`message`**. The value for this key will indicate that the store was successfully created and will include the store&#39;s ID.

4. **Define the `validateStore` Method**

-   **URL Mapping**: Use `@GetMapping("validate/{storeId}")` to create an endpoint that checks if a store with a given `storeId` exists.
    
-   **Path Variable**: `storeId` represents the ID of the store to be validated.
    
-   **Return Key**: This method will return a **boolean** value indicating whether the store exists. If the store is found, the method will return `true`; otherwise, it will return `false`.

5. **Define the `placeOrder` Method**

-   **URL Mapping**: Use `@PostMapping("/placeOrder")` to create an endpoint for placing an order.
    
-   **Request Body**: This method should accept a `PlaceOrderRequestDTO` object in the request body.
    
-   **Return Key**: The response will include a key named **`message`** with the value &#34;Order placed successfully&#34; if the order is successfully processed.
    
    -   **Error Key**: If an error occurs while placing the order, the response will include a key named **`Error`** with the value being the error message.
        

### **Response Structure for Methods**

-   **`addStore`**: Returns a `Map<String, String>` with a key named **`message`** indicating the store ID.
    
-   **`validateStore`**: Returns a **boolean** value to indicate if the store exists (true or false).
    
-   **`placeOrder`**: Returns a `Map<String, String>` with either:
    
    -   **`message`**: &#34;Order placed successfully&#34; if the order is placed without issues.
        
    -   **`Error`**: The error message if there is a failure in processing the order.

### Global Exception Handler

**Purpose**: The `GlobalExceptionHandler` is responsible for handling exceptions globally across all controllers. It ensures that the application responds with meaningful error messages when an exception occurs, improving the user experience and maintaining consistent error handling.

1. Open the `GlobalExceptionHandler.java` file

::openFile{path="/home/project/java-database-final/back-end/src/main/java/com/project/code/Controller/GlobalExceptionHandler.java"}


1. **Set Up the Global Exception Handler Class**

-   **Action**: Annotate the class with `@RestControllerAdvice`. This annotation makes the class capable of handling exceptions globally for all REST controllers in your application.

2. **Define the `handleJsonParseException` Method**

-   **Exception Type**: Use the `@ExceptionHandler(HttpMessageNotReadableException.class)` annotation to handle `HttpMessageNotReadableException`. This exception typically occurs when the request body is not formatted correctly (e.g., invalid JSON syntax).
    
-   **HTTP Status**: Use `@ResponseStatus(HttpStatus.BAD_REQUEST)` to specify that the response will have an HTTP status of **400 Bad Request** when this exception is thrown.
    
-   **Return Key**: The method will return a `Map<String, Object>` containing the following key:
    
    -   **`message`**: A descriptive error message indicating that the input provided is invalid. The value of the `message` key should be: `"Invalid input: The data provided is not valid."`


### **Exception Handling Flow**

-   If a request fails to parse correctly (e.g., due to invalid JSON), the application will invoke the `handleJsonParseException` method.
    
-   This will return a response with a **400 Bad Request** status and the error message under the key **`message`**.

::page{title="Part 5: Load Data"}

### Load NoSQL MongoDB Data

1. The review data is stored in MongoDB. It is available in the file `/home/project/java-database-final/reviews.json`. You can open the file and learn more about the data. 

::openFile{path="/home/project/java-database-final/reviews.json"}

The file contains an array of reviews. Each review has the following information:

- productID: unique id of the product 
- customerID: unique id of the customer who wrote the review
- storeID: unique id of the store where the product was purchased
- rating: rating given by the customer in the review
- comment: any comments left by the customer as part of the review

Example:
```
{
    "productId": 1,
    "customerId": 78,
    "storeId": 1,
    "rating": 3,
    "comment": "The quality exceeded my expectations."
  },
```

2. You will use the `mongoimport` utility to load this data. Execute the following command in the regular terminal in the lab environment.

```bash
mongoimport --uri="mongodb://root:{MONGODB_PASSWORD}@{MONGODB_HOST}/reviews?authSource=admin" --collection=reviews --file /home/project/java-database-final/reviews.json --jsonArray
```

Replace the ${MONGODB_HOST} and ${MONGODB_PASSWORD} from the MongoDB configuration panel.

You should see a message informing you all documents were inserted succesfully and there was no failure.

```
250 document(s) imported successfully. 0 document(s) failed to import.
```

### Load MySQL Data

Before we load sample data, we need to create the database. 
1. Switch to the `MySQL CLI` terminal and create the `inventory` database.

```
create database inventory;
```

Also, before we load sample data, we need to run the backend application so all the tables are created.

2. Switch back to the **standard terminal** (**not** the MySQL CLI one), run the following command to run the backend application:

```bash
cd /home/project/java-database-final/back-end && mvn spring-boot:run
```

This should start the Spring Boot application on port 8080. 

Next, we will load some sample data to the database. The SQL commands to load data are located in the `insert_data.sql` file in your repository. You can open and explore the insert_data.sql file. It first targets the `inventory` database using the `use inventory;` command. It then inserts sample data into `product`, `store`, `inventory`, `customer`, `order_details`, and `order_item` tables.

3. In the **standard terminal** (**not** the MySQL CLI one), because the backend application is still running from the previous step, you must use the `Ctrl+c` keyboard shortcut to kill the process, before you can run the next command. 
4. Now, copy the following command into the termimal window, BUT DON&#39;T RUN IT YET:

```
mysql -h {MYSQL_HOST} -uroot -p{MYSQL_PASSWORD} < /home/project/java-database-final/insert_data.sql
```

5. Replace the `{MYSQL_HOST}` and `{MYSQL_PASSWORD}` information with your real host and password details from the `Connection Information` section of the `MySQL` tab. 

6. Now you can run the command above to load the sample data from the `insert_data.sql` file.

You won&#39;t see any output from this step, but the cursor will come back with no errors.


::page{title="Part 6: Stored Procedures"}

Stored procedures are pre-written SQL scripts stored in the database that perform complex operations. They can be called from repositories to execute tasks efficiently, often used for batch operations, heavy calculations, or when database-side logic is preferred for performance or security reasons.

### Monthly Sales Reports using Stored Procedures
- #### Stored Procedure: Monthly Sales for Each Store

   Write a stored procedure to generate monthly sales for each store, based on a specific year and month.

  Hint:
  -   Create a stored procedure that accepts `year_param` and `month_param` as input parameters.
  -   Use `SELECT` with `SUM(DISTINCT od.total_price)` to calculate the total sales
	- Use `SELECT` with `SUM(DISTINCT od.total_price)` to calculate the total sales.
	- Use `GROUP BY` to group by `store_id`, `MONTH(od.date)`, and `YEAR(od.date)`.
    
  -   Sort by `total_sales DESC` to display stores with the highest sales first.

    Example Call:
   `CALL GetMonthlySalesForEachStore(2025, 3);`
    
    Example Output
    ```mysql
      mysql> CALL GetMonthlySalesForEachStore(2025, 3);
      +----------+-------------+------------+-----------+
      | store_id | total_sales | sale_month | sale_year |
      +----------+-------------+------------+-----------+
      |        8 |      837.03 |          3 |      2025 |
      |        1 |      491.33 |          3 |      2025 |
      |        5 |      390.75 |          3 |      2025 |
      |       10 |      283.09 |          3 |      2025 |
      |        7 |      233.63 |          3 |      2025 |
      |        2 |      211.93 |          3 |      2025 |
      |        3 |      140.68 |          3 |      2025 |
      +----------+-------------+------------+-----------+
      7 rows in set (0.05 sec)

      Query OK, 0 rows affected (0.05 sec)
    ```

- #### Stored Procedure: Aggregate Sales for Company

   Write a stored procedure to calculate the total sales for the company across all stores for a specific month and year.

   Hint:

   -   Create a stored procedure that accepts `year_param` and `month_param` as input parameters.
    
   -   Use `SUM(DISTINCT od.total_price)` to calculate the aggregate sales.
    
   -   Use `GROUP BY` to group by `MONTH(od.date)` and `YEAR(od.date)`.

   Example Call:
       `CALL GetAggregateSalesForCompany(2025, 3);`
 
   Example Output:
  ```mysql
  mysql> CALL GetAggregateSalesForCompany(2025, 3);
  +-------------+------------+-----------+
  | total_sales | sale_month | sale_year |
  +-------------+------------+-----------+
  |     2588.44 |          3 |      2025 |
  +-------------+------------+-----------+
  1 row in set (0.00 sec)

  ```
  

### Identifying Top-Selling Products
- #### By Category
     Write a stored procedure to identify the top-selling products by category for a given month and year.

     Hint:

     -   Create a stored procedure that accepts `target_month` and `target_year` as input parameters.
    
     -   Use `SUM(oi.quantity)` to calculate the total quantity sold.
    
     -   Use a subquery to find the product with the highest total quantity sold within each category.
     -   Group by `category` and `name` to identify the top-selling product in each category.
     
    Example Call:
      `CALL GetTopSellingProductsByCategory(3, 2025);`
   
    Example Output:
    ```mysql
     mysql> CALL GetTopSellingProductsByCategory(3, 2025);
    +----------------------+----------------------+---------------------+-------------+
    | category             | name                 | total_quantity_sold | total_sales |
    +----------------------+----------------------+---------------------+-------------+
    | Accessories          | Sony WH-1000XM4      |                   4 |      179.96 |
    | Home Appliances      | Dyson Vacuum Cleaner |                   3 |       89.97 |
    | Laptops and Monitors | MacBook Pro 16"      |                   3 |      189.97 |
    | Mobile               | Google Pixel 5       |                   3 |      149.97 |
    | TV and AV            | LG NanoCell TV       |                   2 |      119.98 |
    | TV and AV            | Sony Bravia 4K TV    |                   2 |      139.98 |
    +----------------------+----------------------+---------------------+-------------+
    6 rows in set (0.03 sec)

    Query OK, 0 rows affected (0.03 sec)

    ```
- #### By Store

    Write a stored procedure to identify the top-selling product by store for a given month and year.

    Hint:

    -   Create a stored procedure that accepts `target_month` and `target_year` as input parameters.
    
    -   Use `SUM(oi.quantity)` to calculate the total quantity sold for each product in each store.
    
    -   Use a subquery to find the product with the highest total quantity sold for each store.
    
    -   Group by `store_id` and `name` to identify the top-selling product in each store.


    Example Call:
        `CALL GetTopSellingProductByStore(3, 2025);`
  
    Example Output:
  ```mysql
  mysql> CALL GetTopSellingProductByStore(3, 2025);
  +-------------------------+----------+---------------------+--------------------+
  | product_name            | store_id | total_quantity_sold | total_sales        |
  +-------------------------+----------+---------------------+--------------------+
  | Google Pixel 5          |        1 |                   3 |             149.97 |
  | Samsung Galaxy Z Fold 3 |        2 |                   2 |              79.98 |
  | Sony Bravia 4K TV       |        3 |                   2 |             139.98 |
  | Dyson Vacuum Cleaner    |        5 |                   3 |              89.97 |
  | Dell XPS 13             |        7 |                   2 |             159.98 |
  | MacBook Pro 16"         |        8 |                   3 |             189.97 |
  | Sony WH-1000XM4         |        8 |                   3 |             119.97 |
  | Bose QuietComfort 35 II |       10 |                   3 | 269.96999999999997 |
  +-------------------------+----------+---------------------+--------------------+
  8 rows in set (0.05 sec)

  Query OK, 0 rows affected (0.05 sec)

  ```

::page{title="Part 7: Run the Applications"}

### Run the backend
1. In the standard `theia@theiadocker` terminal (not the MySQL CLI one), run the following command to run the backend application. If the application is sill running from a previous process, use the `Ctrl+c` keyboard shortcut to kill the process and then run the command:

```bash
cd /home/project/java-database-final/back-end && mvn spring-boot:run
```

This should start the Spring Boot application on port 8080. 

2. If you get a message saying the port 8080 is already in use, use the following command to kill the previously running program on that port.

```bash
fuser -k 8080/tcp
```

3. Then run the command to run the back-end application:

```bash
cd /home/project/java-database-final/back-end && mvn spring-boot:run
```

### Launch the backend and copy the URL

Use the `Launch Application` feature of the lab environment. Enter `8080` as the port and click on the `Open in new browser tab` icon. You will see an error and that is absolutely fine. Copy the complete URL. You will need it in the next step to connect the frontend to the backend.


### Configure the frontend

1. Open the `script.js` file and set the `apiURL` to the backend URL you copied above. Do not copy the trailing `/`.

::openFile{path="/home/project/java-database-final/front-end/script.js"}

For example, this might look like:

```
const apiURL = 'https://captainfedo1-8080.theiadockernext-0-labs-prod-theiak8s-4-tor01.proxy.cognitiveclass.ai';
```

Your URL will be different.

1. Open the `reviews.html` file and set the URL in the `function getReviews(storeId, productId)` method to the backend URL as well. Just change the `URL` as you still need the remaining part of the variable.

```
url = `URL/reviews/${storeId}/${productId}`;
```

::openFile{path="/home/project/java-database-final/front-end/reviews.html"}

Here is an example of what this might look like:

```
function getReviews(storeId, productId) {
            url = `https://captainfedo1-8080.theiadockernext-0-labs-prod-theiak8s-4-tor01.proxy.cognitiveclass.ai/reviews/${storeId}/${productId}`;
            fetch(url, {
                method: "GET",
                headers: { "content-type": "application/json" }
            })
```

Your url will be different than what is shown here.


### Run the frontend
1. In the terminal, use the following command to run the front-end application:

```bash
cd /home/project/java-database-final/front-end && python3 -m http.server
```

This should start a Python server and serve the front end files on port 8000.

2. You can now visit the front end by using the `Launch Application` feature of the lab environment. Enter `8000` as the port and click on the `Open in new browser tab` icon.

	![launch-front-end.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/WbvehhhVOwR05pY6MUl44w/launch-front-end.png)

3. The front-end of the app should look similar to this:

	![Front-end_1.png](https://cf-courses-data.s3.us.cloud-object-storage.appdomain.cloud/rhdFZhhLPqhodMFptM9oxA/Front-end-1.png)



::page{title="Solution Models"}

## Models

### Customer.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Model;

	import java.util.List;

	import com.fasterxml.jackson.annotation.JsonManagedReference;

	import jakarta.persistence.Entity;
	import jakarta.persistence.FetchType;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.OneToMany;
	import jakarta.validation.Valid;
	import jakarta.validation.constraints.NotNull;

	@Entity
	public class Customer {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@Valid
		@NotNull(message = "Name cannot be null")
		private String name;
		@NotNull(message = "Email cannot be null")
		private String email;
		@NotNull(message = "Phone No cannot be null")
		private String phone;

		@OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
		@JsonManagedReference
		private List<OrderDetails> orders;

		// Getters and Setters

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public List<OrderDetails> getOrders() {
			return orders;
		}

		public void setOrders(List<OrderDetails> orders) {
			this.orders = orders;
		}

		// Constructors (if necessary)
		public Customer() {}

		public Customer(String name, String email, String phone) {
			this.name = name;
			this.email = email;
			this.phone = phone;
		}
	}

</details>

### Inventory.java
<details>
	<summary>Click here for code</summary>

	package com.project.code.Model;

	import com.fasterxml.jackson.annotation.JsonBackReference;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;

	@Entity
	public class Inventory {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@ManyToOne
		@JsonBackReference("inventory-product")
		@JoinColumn(name = "product_id")
		private Product product;

		@ManyToOne
		@JoinColumn(name = "store_id")
		@JsonBackReference("inventory-store")
		private Store store;

		private Integer stockLevel;

		// Getters and Setters

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public Store getStore() {
			return store;
		}

		public void setStore(Store store) {
			this.store = store;
		}

		public Integer getStockLevel() {
			return stockLevel;
		}

		public void setStockLevel(Integer stockLevel) {
			this.stockLevel = stockLevel;
		}

		// Constructors (if necessary)
		public Inventory() {
		}

		public Inventory(Product product, Store store, Integer stockLevel) {
			this.product = product;
			this.store = store;
			this.stockLevel = stockLevel;
		}

		public String toString() {
			return "Inventory{" +
					"id=" + id +
					", product=" + (product != null ? product.getId() : "null") +
					", store=" + (store != null ? store.getId() : "null") +
					", stockLevel=" + stockLevel +
					'}';
		}
	}
</details>
	
### Product.java
	
<details>
	<summary>Click here for code</summary>

	package com.project.code.Model;

	import java.util.List;

	import com.fasterxml.jackson.annotation.JsonManagedReference;

	import jakarta.persistence.Entity;
	import jakarta.persistence.FetchType;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.OneToMany;
	import jakarta.persistence.Table;
	import jakarta.persistence.UniqueConstraint;
	import jakarta.validation.constraints.NotNull;

	@Entity
	@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
	public class Product {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotNull(message = "Name cannot be null")
		private String name;

		@NotNull(message = "Name cannot be null")
		private String category;

		@NotNull(message = "Name cannot be null")
		private Double price;

		@NotNull(message = "Name cannot be null")
		private String sku;

		@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
		@JsonManagedReference("inventory-product")
		private List<Inventory> inventory;

		// Getters and Setters

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
		}

		public List<Inventory> getInventory() {
			return inventory;
		}

		public void setInventory(List<Inventory> inventory) {
			this.inventory = inventory;
		}

		// Constructors (if necessary)
		public Product() {
		}

		public Product(String name, String category, Double price, String sku) {
			this.name = name;
			this.category = category;
			this.price = price;
			this.sku = sku;
		}

		public String toString() {
			return "Product{" +
					"id=" + id +
					", name='" + name + '\'' +
					", category='" + category + '\'' +
					", price=" + price +
					", sku='" + sku + '\'' +
					'}';
		}
	}



</details>
	

### Store.java
	
<details>
	<summary>Click here for code</summary>

	package com.project.code.Model;

	import java.util.List;

	import com.fasterxml.jackson.annotation.JsonManagedReference;

	import jakarta.persistence.Entity;
	import jakarta.persistence.FetchType;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.OneToMany;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.NotNull;

	@Entity
	public class Store {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotNull(message = "Name cannot be null")
		@NotBlank(message = "Name cannot be blank")
		private String name;
		@NotNull(message = "Address cannot be null")
		@NotBlank(message = "Address cannot be blank")
		private String address;

		@OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
		@JsonManagedReference("inventory-store")
		private List<Inventory> inventory;

		// Getters and Setters

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public List<Inventory> getInventory() {
			return inventory;
		}

		public void setInventory(List<Inventory> inventory) {
			this.inventory = inventory;
		}

		// Constructors (if necessary)
		public Store() {
		}

		public Store(String name, String address) {
			this.name = name;
			this.address = address;
		}
	}
</details>
	
### Review.java
	
<details>
	<summary>Click here for code</summary>

	package com.project.code.Model;

	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;

	import jakarta.validation.constraints.NotNull;

	@Document(collection = "reviews")
	public class Review {

		@Id
		private String id; // MongoDB uses String for the ID field

		@NotNull(message = "Customer cannot be null")
		private Long customerId; // The customer who created the review

		@NotNull(message = "Product cannot be null")
		private Long productId; // The product being reviewed

		@NotNull(message = "Store cannot be null")
		private Long storeId; // The store associated with the product

		@NotNull(message = "Rating cannot be null")
		private Integer rating; // Rating out of 5

		private String comment; // Optional comment on the product

		// Constructors
		public Review() {
		}

		public Review(Long customerId, Long productId, Long storeId, Integer rating, String comment) {
			this.customerId = customerId;
			this.productId = productId;
			this.storeId = storeId;
			this.rating = rating;
			this.comment = comment;
		}

		// Getters and Setters
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Long getCustomerId() {
			return customerId;
		}

		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public Long getStoreId() {
			return storeId;
		}

		public void setStoreId(Long storeId) {
			this.storeId = storeId;
		}

		public Integer getRating() {
			return rating;
		}

		public void setRating(Integer rating) {
			this.rating = rating;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

	}


</details>
	
### OrderDetails.java
	
<details>
	<summary>Click here for code</summary>

	package com.project.code.Model;

	import java.time.LocalDateTime;
	import java.util.List;

	import com.fasterxml.jackson.annotation.JsonManagedReference;

	import jakarta.persistence.Entity;
	import jakarta.persistence.FetchType;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.OneToMany;

	@Entity
	public class OrderDetails {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@ManyToOne
		@JoinColumn(name = "customer_id")
		@JsonManagedReference
		private Customer customer;

		@ManyToOne
		@JoinColumn(name = "store_id")
		@JsonManagedReference
		private Store store;

		private Double totalPrice;
		private LocalDateTime date;

		@OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
		@JsonManagedReference
		private List<OrderItem> orderItems;

		// Getters and Setters

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public Store getStore() {
			return store;
		}

		public void setStore(Store store) {
			this.store = store;
		}

		public Double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(Double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public LocalDateTime getDate() {
			return date;
		}

		public void setDate(LocalDateTime date) {
			this.date = date;
		}

		public List<OrderItem> getOrderItems() {
			return orderItems;
		}

		public void setOrderItems(List<OrderItem> orderItems) {
			this.orderItems = orderItems;
		}

		// Constructors (if necessary)
		public OrderDetails() {}

		public OrderDetails(Customer customer, Store store, Double totalPrice, LocalDateTime date) {
			this.customer = customer;
			this.store = store;
			this.totalPrice = totalPrice;
			this.date = date;
		}
	}

	
</details>
	
### OrderItem.java
	
<details>
	<summary>Click here for code</summary>

	package com.project.code.Model;

	import com.fasterxml.jackson.annotation.JsonManagedReference;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;

	@Entity
	public class OrderItem {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@ManyToOne
		@JoinColumn(name = "order_id")
		@JsonManagedReference
		private OrderDetails order;

		@ManyToOne
		@JoinColumn(name = "product_id")
		@JsonManagedReference
		private Product product;

		private Integer quantity;
		private Double price;

		// Getters and Setters

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public OrderDetails getOrder() {
			return order;
		}

		public void setOrder(OrderDetails order) {
			this.order = order;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		// Constructors (if necessary)
		public OrderItem() {}

		public OrderItem(OrderDetails order, Product product, Integer quantity, Double price) {
			this.order = order;
			this.product = product;
			this.quantity = quantity;
			this.price = price;
		}
	}


</details>


::page{title="Solution Repositories and Services"}

## Repositories

### CustomerRepository.java

<details>
	<summary>Click here for code</summary>
	
	package com.project.code.Repo;

	import com.project.code.Model.Customer;
	import org.springframework.data.jpa.repository.JpaRepository;

	public interface CustomerRepository extends JpaRepository<Customer, Long> {

		Customer findByEmail(String email);

		Customer findByid(Long id);
	}
</details>

### InventoryRepository.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Repo;

	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Modifying;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.stereotype.Repository;

	import com.project.code.Model.Inventory;

	import jakarta.transaction.Transactional;

	@Repository
	public interface InventoryRepository extends JpaRepository<Inventory, Long> {

		@Query("SELECT i FROM Inventory i WHERE i.product.id = :productId AND i.store.id = :storeId")
		Inventory findByProductIdandStoreId(Long productId, Long storeId);

		List<Inventory> findByStore_Id(Long storeId);

		@Modifying
		@Transactional
		@Query("DELETE FROM Inventory i WHERE i.product.id = :productId")
		void deleteByProductId(Long productId);

	}
	
</details>

### OrderDetailsRepository.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Repo;

	import com.project.code.Model.OrderDetails;
	import org.springframework.data.jpa.repository.JpaRepository;

	public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
	}
	
</details>

### OrderItemRepository.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Repo;

	import com.project.code.Model.OrderItem;
	import org.springframework.data.jpa.repository.JpaRepository;

	import org.springframework.data.jpa.repository.Modifying;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.stereotype.Repository;

	import jakarta.transaction.Transactional;

	@Repository
	public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

		@Modifying
		@Transactional
		@Query("DELETE FROM OrderItem o WHERE o.product.id = :productId")
		void deleteByProductId(Long productId);

	}
</details>
	
### ProductRepository.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Repo;

	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.stereotype.Repository;

	import com.project.code.Model.Product;

	@Repository
	public interface ProductRepository extends JpaRepository<Product, Long> {



		List<Product> findAll();

		List<Product> findByCategory(String category);

		List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

		List<Product> findBySku(String sku);

		Product findByName(String name);

		Product findByid(Long id);

		@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :pname, '%'))")
		List<Product> findByNameLike(long storeId,String pname);

		@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND i.product.category = :category")
		List<Product> findByNameAndCategory(long storeId, String pname,String category);

		@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND i.product.category = :category")
		List<Product> findByCategoryAndStoreId(long storeId,String category);


		@Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%'))")
		List<Product> findProductBySubName(String pname);

		@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId")
		List<Product> findProductsByStoreId(Long storeId);


		@Query("SELECT i.product FROM Inventory i WHERE i.product.category = :category and i.store.id = :storeId")
		List<Product> findProductByCategory(String category, long storeId);


		@Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND i.category = :category")
		List<Product> findProductBySubNameAndCategory(String pname, String category);
	}
	
</details>
	
### ReviewRepository.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Repo;

	import com.project.code.Model.Review;

	import java.util.List;

	import org.springframework.data.mongodb.repository.MongoRepository;

	public interface ReviewRepository extends MongoRepository<Review, String> {
		List<Review> findByStoreIdAndProductId(Long storeId, Long productId);

	}
	
</details>
	
### StoreRepository.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Repo;

	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.stereotype.Repository;


	import com.project.code.Model.Store;

	@Repository
	public interface StoreRepository extends JpaRepository<Store, Long> {

		Store findByid(Long id);

		@Query("SELECT p FROM Store p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :pname, '%'))")
		List<Store> findBySubName(String pname);

	}
	
</details>

## Services
	
### OrderService.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Service;

	import com.project.code.Model.Customer;
	import com.project.code.Model.Inventory;
	import com.project.code.Model.OrderDetails;
	import com.project.code.Model.OrderItem;
	import com.project.code.Model.PlaceOrderRequestDTO;
	import com.project.code.Model.PurchaseProductDTO;
	import com.project.code.Model.Store;
	import com.project.code.Repo.CustomerRepository;
	import com.project.code.Repo.InventoryRepository;
	import com.project.code.Repo.OrderDetailsRepository;
	import com.project.code.Repo.OrderItemRepository;
	import com.project.code.Repo.ProductRepository;
	import com.project.code.Repo.StoreRepository;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import java.util.List;

	@Service
	public class OrderService {

		@Autowired
		private ProductRepository productRepository;

		@Autowired
		private InventoryRepository inventoryRepository;

		@Autowired
		private CustomerRepository customerRepository;

		@Autowired
		private StoreRepository storeRepository;

		@Autowired
		private OrderDetailsRepository orderDetailsRepository;

		@Autowired
		private OrderItemRepository orderItemRepository;

		public void saveOrder(PlaceOrderRequestDTO placeOrderRequest) {
			// 1. Retrieve or create the Customer
			Customer existingCustomer = customerRepository.findByEmail(placeOrderRequest.getCustomerEmail());
			Customer customer = new Customer();
			customer.setName(placeOrderRequest.getCustomerName());
			customer.setEmail(placeOrderRequest.getCustomerEmail());
			customer.setPhone(placeOrderRequest.getCustomerPhone());

			if (existingCustomer == null) {
				customer = customerRepository.save(customer);
			}
			else{
				customer=existingCustomer;
			}


			// 2. Retrieve the Store
			Store store = storeRepository.findById(placeOrderRequest.getStoreId())
					.orElseThrow(() -> new RuntimeException("Store not found"));

			// 3. Create OrderDetails
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setCustomer(customer);
			orderDetails.setStore(store);
			orderDetails.setTotalPrice(placeOrderRequest.getTotalPrice());
			orderDetails.setDate(java.time.LocalDateTime.now()); // Use current datetime

			orderDetails = orderDetailsRepository.save(orderDetails); 

			// 4. Create and save OrderItems (products purchased)
			List<PurchaseProductDTO> purchaseProducts = placeOrderRequest.getPurchaseProduct();
			for (PurchaseProductDTO productDTO : purchaseProducts) {
				OrderItem orderItem = new OrderItem();

				Inventory inventory=inventoryRepository.findByProductIdandStoreId(productDTO.getId(),placeOrderRequest.getStoreId());

				inventory.setStockLevel(inventory.getStockLevel()-productDTO.getQuantity());
				inventoryRepository.save(inventory);

				orderItem.setOrder(orderDetails); // Link the order to the order item

				orderItem.setProduct(productRepository.findByid(productDTO.getId()));

				orderItem.setQuantity(productDTO.getQuantity());
				orderItem.setPrice(productDTO.getPrice()*productDTO.getQuantity());

				orderItemRepository.save(orderItem); // Save OrderItem
			}
		}
	}
	
</details>
	
### ServiceClass.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Service;

	import org.springframework.stereotype.Service;

	import com.project.code.Model.Inventory;
	import com.project.code.Model.Product;
	import com.project.code.Repo.InventoryRepository;
	import com.project.code.Repo.ProductRepository;

	@Service
	public class ServiceClass {


		private final InventoryRepository inventoryRepository;
		private final ProductRepository productRepository;

		public ServiceClass(InventoryRepository inventoryRepository,ProductRepository productRepository) {
			this.inventoryRepository = inventoryRepository;
			this.productRepository=productRepository;
		}

		public boolean validateInventory(Inventory inventory)
		{
			Inventory result=inventoryRepository.findByProductIdandStoreId(inventory.getProduct().getId(),inventory.getStore().getId());
			if(result!=null)
			{
				return false;
			}
			return true;
		}

		public boolean validateProduct(Product product)
		{
			Product result=productRepository.findByName(product.getName());
			if(result!=null)
			{
				return false;
			}
			return true;
		}

		public boolean ValidateProductId(long id)
		{
			Product result=productRepository.findByid(id);
			System.out.println(result);
			if(result==null)
			{
				return false;
			}
			return true;
		}

		public Inventory getInventoryId(Inventory inventory)
		{
			Inventory result=inventoryRepository.findByProductIdandStoreId(inventory.getProduct().getId(),inventory.getStore().getId());

			return result;
		}
	}
</details>
	

::page{title="Solution Controllers"}

## Controllers

### InventoryController.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Controller;

	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.DataIntegrityViolationException;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.project.code.Model.CombinedRequest;
	import com.project.code.Model.Inventory;
	import com.project.code.Model.Product;
	import com.project.code.Repo.InventoryRepository;
	import com.project.code.Repo.ProductRepository;
	import com.project.code.Service.ServiceClass;

	@RestController
	@RequestMapping("/inventory")
	public class InventoryController {

		@Autowired
		private ProductRepository productRepository;
		@Autowired
		private InventoryRepository inventoryRepository;

		@Autowired
		private ServiceClass serviceClass;

		@PutMapping
		public Map<String, String> updateInventory(@RequestBody CombinedRequest request) {
			Product product = request.getProduct();
			Inventory inventory = request.getInventory();

			Map<String, String> map = new HashMap<>();
			System.out.println("Stock Level: " + inventory.getStockLevel());
			if (!serviceClass.ValidateProductId(product.getId())) {
				map.put("message", "Id " + product.getId() + " not present in database");
				return map;
			}
			productRepository.save(product);
			map.put("message", "Successfully updated product with id: " + product.getId());

			if (inventory != null) {
				try {
					Inventory result = serviceClass.getInventoryId(inventory);
					if (result != null) {
						inventory.setId(result.getId());
						inventoryRepository.save(inventory);
					} else {
						map.put("message", "No data available for this product or store id");
						return map;
					}

				} catch (DataIntegrityViolationException e) {
					map.put("message", "Error: " + e);
					System.out.println(e);
					return map;
				} catch (Exception e) {
					map.put("message", "Error: " + e);
					System.out.println(e);
					return map;
				}
			}

			return map;

		}

		@PostMapping
		public Map<String, String> saveInventory(@RequestBody Inventory inventory) {

			Map<String, String> map = new HashMap<>();
			try {
				if (serviceClass.validateInventory(inventory)) {
					inventoryRepository.save(inventory);
				} else {
					map.put("message", "Data Already present in inventory");
					return map;
				}

			} catch (DataIntegrityViolationException e) {
				map.put("message", "Error: " + e);
				System.out.println(e);
				return map;
			} catch (Exception e) {
				map.put("message", "Error: " + e);
				System.out.println(e);
				return map;
			}
			map.put("message", "Product added to inventory successfully");
			return map;
		}

		@GetMapping("/{storeid}")
		public Map<String, Object> getAllProducts(@PathVariable Long storeid) {
			Map<String, Object> map = new HashMap<>();
			List<Product> result = productRepository.findProductsByStoreId(storeid);
			map.put("products", result);
			return map;
		}

		@GetMapping("filter/{category}/{name}/{storeid}")
		public Map<String, Object> getProductName(@PathVariable String category, @PathVariable String name,
				@PathVariable long storeid) {
			Map<String, Object> map = new HashMap<>();
			if (category.equals("null") ) {
				map.put("product", productRepository.findByNameLike(storeid, name));
				return map;
			}
			else if(name.equals("null"))
			{
				System.out.println("name is null");
				map.put("product", productRepository.findByCategoryAndStoreId(storeid,category));
				return map;
			}
			map.put("product", productRepository.findByNameAndCategory(storeid, name, category));
			return map;
		}

		@GetMapping("search/{name}/{storeId}")
		public Map<String,Object> searchProduct(@PathVariable String name, @PathVariable long storeId)
		{
			Map<String, Object> map = new HashMap<>();
			map.put("product", productRepository.findByNameLike(storeId, name));
			return map;
		}

		@DeleteMapping("/{id}")
		public Map<String, String> removeProduct(@PathVariable Long id) {
			Map<String, String> map = new HashMap<>();

			if (!serviceClass.ValidateProductId(id)) {
				map.put("message", "Id " + id + " not present in database");
				return map;
			}
			inventoryRepository.deleteByProductId(id);
			map.put("message", "Deleted product successfully with id: " + id);
			return map;
		}

		@GetMapping("validate/{quantity}/{storeId}/{productId}")
		public boolean validateQuantity(@PathVariable int quantity, @PathVariable long storeId,
				@PathVariable long productId) {
			Inventory result = inventoryRepository.findByProductIdandStoreId(productId, storeId);
			if (result.getStockLevel() >= quantity) {
				return true;
			}
			return false;

		}

	}
</details>
	
### ProductController.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Controller;

	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.DataIntegrityViolationException;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.project.code.Model.Product;
	import com.project.code.Repo.InventoryRepository;
	import com.project.code.Repo.OrderItemRepository;
	import com.project.code.Repo.ProductRepository;
	import com.project.code.Service.ServiceClass;

	@RequestMapping("/product")
	@RestController
	public class ProductController {

		@Autowired
		private ProductRepository productRepository;

		@Autowired
		private OrderItemRepository orderItemRepository;

		@Autowired
		private ServiceClass serviceClass;

		@Autowired
		private InventoryRepository inventoryRepository;

		@PostMapping
		public Map<String, String> addProduct(@RequestBody Product product) {

			Map<String, String> map = new HashMap<>();
			if (!serviceClass.validateProduct(product)) {
				map.put("message", "Product already present in database");
				return map;
			}
			try {
				productRepository.save(product);
				map.put("message", "Product added successfully");
			}

			catch (DataIntegrityViolationException e) {
				map.put("message", "SKU should be unique");
			}
			return map;
		}

		@GetMapping("/product/{id}")
		public Map<String, Object> getProductbyId(@PathVariable Long id) {
			System.out.println("result: ");
			System.out.println("result: ");
			System.out.println("result: ");
			Map<String, Object> map = new HashMap<>();
			Product result = productRepository.findByid(id);

			System.out.println("result: "+result);
			map.put("products", result);
			return map;
		}

		@PutMapping
		public Map<String, String> updateProduct(@RequestBody Product product) {
			Map<String, String> map = new HashMap<>();
			try {
				productRepository.save(product);
				map.put("message", "Data upated sucessfully");
			} catch (Error e) {
				map.put("message", "Error occured");
			}

			return map;
		}

		@GetMapping("/category/{name}/{category}")
		public Map<String, Object> filterbyCategoryProduct(@PathVariable String name,@PathVariable String category) {
			Map<String, Object> map = new HashMap<>();

			if(name.equals("null"))
			{
				map.put("products", productRepository.findByCategory(category));
				return map;
			}
			else if(category.equals("null"))
			{
				map.put("products", productRepository.findProductBySubName(name));
				return map;

			}
			map.put("products",productRepository.findProductBySubNameAndCategory(name,category));
			return map;

		}

		@GetMapping
		public Map<String, Object> listProduct() {

			Map<String, Object> map = new HashMap<>();
			map.put("products",productRepository.findAll());
			return map;
		}  




		@GetMapping("filter/{category}/{storeid}")
		public Map<String, Object> getProductbyCategoryAndStoreId(@PathVariable String category,@PathVariable long storeid) {
		   Map<String, Object> map = new HashMap<>();
		   List<Product> result = productRepository.findProductByCategory(category,storeid);

			map.put("product", result);
			return map;
		}

		@DeleteMapping("/{id}")
		public Map<String, String> deleteProduct(@PathVariable Long id) {
			Map<String, String> map = new HashMap<>();

			if (!serviceClass.ValidateProductId(id)) {
				map.put("message", "Id " + id + " not present in database");
				return map;
			}
			inventoryRepository.deleteByProductId(id);
			orderItemRepository.deleteByProductId(id);
			productRepository.deleteById(id);

			map.put("message", "Deleted product successfully with id: " + id);
			return map;
		}

		@GetMapping("/searchProduct/{name}")
		public Map<String, Object> searchProduct(@PathVariable String name) {
			Map<String, Object> map = new HashMap<>();
			map.put("products", productRepository.findProductBySubName(name));
			return map;
		}


	}
</details>
	
### ReviewController.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Controller;

	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.project.code.Model.Customer;
	import com.project.code.Model.Review;
	import com.project.code.Repo.CustomerRepository;
	import com.project.code.Repo.ReviewRepository;

	@RestController
	@RequestMapping("/reviews")
	public class ReviewController {

		@Autowired
		ReviewRepository reviewRepository;

		@Autowired
		CustomerRepository customerRepository;

		@GetMapping("/{storeId}/{productId}")
		public Map<String,Object> getReviews(@PathVariable long storeId, @PathVariable long productId)
		{
			Map<String, Object> map = new HashMap<>();
			 List<Review> reviews = reviewRepository.findByStoreIdAndProductId(storeId,productId);

			 List<Map<String, Object>> reviewsWithCustomerNames = new ArrayList<>();

			 // For each review, fetch customer details and add them to the response
			 for (Review review : reviews) {
				 Map<String, Object> reviewMap = new HashMap<>();
				 reviewMap.put("review", review.getComment());
				 reviewMap.put("rating", review.getRating());

				 // Fetch customer details using customerId
				 Customer customer = customerRepository.findByid(review.getCustomerId());
				 if (customer != null) {
					 reviewMap.put("customerName", customer.getName());  
				 } else {
					 reviewMap.put("customerName", "Unknown");
				 }

				 reviewsWithCustomerNames.add(reviewMap);
			 }

			 map.put("reviews", reviewsWithCustomerNames);
			 return map;

		}

		@GetMapping
		public Map<String,Object> getAllReviews()
		{
			Map<String,Object> map=new HashMap<>();
			map.put("reviews",reviewRepository.findAll());
			return map;
		}


	}
</details>
	
### StoreController.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Controller;

	import java.util.HashMap;
	import java.util.Map;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.project.code.Model.PlaceOrderRequestDTO;
	import com.project.code.Model.Store;
	import com.project.code.Repo.StoreRepository;
	import com.project.code.Service.OrderService;


	@RestController
	@RequestMapping("/store")
	public class StoreController {

		@Autowired
		private StoreRepository storeRepository;

		@Autowired
		private OrderService orderService;

		@PostMapping
		public Map<String, String> addStore(@RequestBody Store store) {
			Store savedStore = storeRepository.save(store);
			Map<String, String> map = new HashMap<>();
			map.put("message", "Store added successfully with id "+ savedStore.getId());
			return map;
		}


		@GetMapping("validate/{storeId}")
		public boolean validateStore(@PathVariable Long storeId ) 
		{
			Store store=storeRepository.findByid(storeId);
			if(store!=null)
			{
				return true;
			}
			return false;
		}


		@PostMapping("/placeOrder")
		public Map<String,String> placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequest) {

			Map<String,String> map=new HashMap<>();
			try{
			orderService.saveOrder(placeOrderRequest);
			map.put("message","Order placed successfully");
			}
			catch(Error e)
			{
				map.put("Error",""+e);

			}
			return map;  
		}

	}
</details>

::page{title="Solution Global Exception Handler"}

### GlobalExceptionHandler.java

<details>
	<summary>Click here for code</summary>

	package com.project.code.Controller;

	import java.util.HashMap;
	import java.util.Map;

	import org.springframework.http.HttpStatus;
	import org.springframework.http.converter.HttpMessageNotReadableException;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.ResponseStatus;
	import org.springframework.web.bind.annotation.RestControllerAdvice;

	@RestControllerAdvice
	public class GlobalExceptionHandler {

		@ExceptionHandler(HttpMessageNotReadableException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		public Map<String, Object> handleJsonParseException(HttpMessageNotReadableException ex) {
			Map<String,Object> map=new HashMap<>();
			map.put("message","Invalid input: The data provided is not valid.");
			return map;
		}
	}
</details>
	

::page{title="Solution Stored Procedures"}

### Monthly sales by store

<details>
	<summary>Click here for code</summary>

	DELIMITER $$
	CREATE PROCEDURE GetMonthlySalesForEachStore(IN year_param INT, IN month_param INT)
	BEGIN
		SELECT 
			od.store_id, 
			SUM(DISTINCT od.total_price) AS total_sales,  -- Use DISTINCT to avoid duplicates
			MONTH(od.date) AS sale_month,
			YEAR(od.date) AS sale_year
		FROM 
			order_details od
		JOIN 
			order_item oi ON od.id = oi.order_id
		WHERE 
			YEAR(od.date) = year_param
			AND MONTH(od.date) = month_param
		GROUP BY 
			od.store_id, MONTH(od.date), YEAR(od.date)
		ORDER BY 
			total_sales DESC;
	END $$
	DELIMITER ;

</details>
	
### Example usage
	CALL GetMonthlySalesForEachStore(2025, 3);

### Aggregate sales for the company

<details>
	<summary>Click here for code</summary>
	
	DELIMITER $$
	CREATE PROCEDURE GetAggregateSalesForCompany(IN year_param INT, IN month_param INT)
	BEGIN
		SELECT 
			SUM(DISTINCT od.total_price) AS total_sales,
			MONTH(od.date) AS sale_month,
			YEAR(od.date) AS sale_year
		FROM 
			order_details od
		JOIN 
			order_item oi ON od.id = oi.order_id
		WHERE 
			YEAR(od.date) = year_param
			AND MONTH(od.date) = month_param
		GROUP BY 
			MONTH(od.date), YEAR(od.date);
	END$$
	DELIMITER ;
</details>
	
### Example usage
	CALL GetAggregateSalesForCompany(2025, 3);

### Identify top selling products by category

<details>
	<summary>Click here for code</summary>
	
	DELIMITER $$
	CREATE PROCEDURE GetTopSellingProductsByCategory(IN target_month INT, IN target_year INT)
	BEGIN
		SELECT 
			p.category, 
			p.name, 
			SUM(oi.quantity) AS total_quantity_sold,
			SUM(oi.price * oi.quantity) AS total_sales
		FROM 
			product p
		JOIN 
			order_item oi ON p.id = oi.product_id
		JOIN 
			order_details od ON oi.order_id = od.id
		WHERE 
			MONTH(od.date) = target_month  -- Use the provided month
			AND YEAR(od.date) = target_year  -- Use the provided year
		GROUP BY 
			p.category, p.name
		HAVING 
			SUM(oi.quantity) = (
				SELECT 
					MAX(total_quantity)
				FROM (
					SELECT 
						SUM(oi2.quantity) AS total_quantity
					FROM 
						order_item oi2
					JOIN 
						order_details od2 ON oi2.order_id = od2.id
					JOIN 
						product p2 ON oi2.product_id = p2.id
					WHERE 
						MONTH(od2.date) = target_month  -- Same month
						AND YEAR(od2.date) = target_year  -- Same year
						AND p2.category = p.category  -- Same category
					GROUP BY 
						p2.name  -- Group by product name to calculate the total for each product
				) AS Subquery
			)
		ORDER BY 
			p.category;
	END$$
	DELIMITER ;
	
</details>
	
### Example usage
	CALL GetTopSellingProductsByCategory(3,2025);

### Identify top selling products by store
<details>
	<summary>Click here for code</summary>
	
	DELIMITER $$
	CREATE PROCEDURE GetTopSellingProductByStore(IN target_month INT, IN target_year INT)
	BEGIN
		SELECT 
			p.name AS product_name,
			od.store_id,
			SUM(oi.quantity) AS total_quantity_sold,
			SUM(oi.price * oi.quantity) AS total_sales
		FROM 
			product p
		JOIN 
			order_item oi ON p.id = oi.product_id
		JOIN 
			order_details od ON oi.order_id = od.id
		WHERE 
			MONTH(od.date) = target_month  -- Use the provided month
			AND YEAR(od.date) = target_year  -- Use the provided year
		GROUP BY 
			od.store_id, p.name  -- Group by store and product name
		HAVING 
			SUM(oi.quantity) = (
				SELECT 
					MAX(total_quantity)
				FROM (
					SELECT 
						SUM(oi2.quantity) AS total_quantity
					FROM 
						order_item oi2
					JOIN 
						order_details od2 ON oi2.order_id = od2.id
					JOIN 
						product p2 ON oi2.product_id = p2.id
					WHERE 
						MONTH(od2.date) = target_month  -- Same month
						AND YEAR(od2.date) = target_year  -- Same year
						AND od2.store_id = od.store_id  -- Same store
					GROUP BY 
						p2.name  -- Group by product name
				) AS Subquery
			)
		ORDER BY 
			od.store_id;
	END$$
	DELIMITER ;
	
</details>
	
### Example usage
	CALL GetTopSellingProductByStore(3, 2025); 

::page{title="Conclusion"}

Congratulations on completing this final project assignment.
	
You have successfully demonstrated your understanding of how to build a backend system for a retail management system by using your Java, Sprng Boot, and MySQL skills. 

You successfully configured the backend to use the relevant databases for your data, and built the required stored procedures. You also created the required Spring Boot models, repositories, services, and controllers. And finally, you successfully integrated the backend you built with the pre-built frontend to run the project&#39;s application and tested it end-to-end.
	
If you missed some steps or couldn&#39;t complete the lab at this time, you can always come back and do the lab again at a later time.

## Next Steps
Tell people what they should explore next. Here is an example:

In this lab, you got to deploy your Node.js application to a Kubernetes cluster. You used a shared cluster provided to you by the IBM Developer Skills Network. If you are interested in continuing to learn about Kubernetes and containers, you should get your own [free Kubernetes cluster](https://www.ibm.com/cloud/container-service/?utm_source=skills_network&utm_content=in_lab_content_link&utm_id=Lab-final-project-lab-v1_1743719228) and your own free [IBM Container Registry](https://www.ibm.com/cloud/container-registry?utm_source=skills_network&utm_content=in_lab_content_link&utm_id=Lab-final-project-lab-v1_1743719228).

## Author(s)
Upkar Lidder

<!---
## Changelog
| Date | Version | Changed by | Change Description |
|------|--------|--------|---------|
| 2025-04-07 | 0.1 | UL | Initial version created |
| 2025-04-08 | 0.2 | Steve Ryan | ID review / partial test |
|   |   |   |   |
|   |   |   |   |
--->