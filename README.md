### <h2> Online Book Store </h2>

  <a href="#introduction">Introduction</a> •
  <a href="#functionalities">Functionalities</a> •
  <a href="#list-of-used-technologies">Technologies</a> •
  <a href="#endpoints-api">Endpoints</a> •
  <a href="#how-to-run">How to run</a> •
  <a href="#contacts">Contacts</a>


### Introduction
The Online Book Store project is a platform for implementing an online service for selling books.
Users are provided in this project with a set of actions for an enjoyable shopping experience: viewing a catalog of books, selecting a book based on parameters, adding items to the shopping cart and updating the cart, placing a purchase order, viewing the history and status of their orders.
For administrators, functionality has been implemented to manage the book catalog, book categories, and order statuses.

### Functionalities
<h5>User use cases:</h5>
<li>Registration and authentication: users can create an account and sign in to the system</li>
<li>Browsing through book catalog: users have access to view all books in catalog, to filter it by category and search by different parameters</li>
<li>Managing shopping cart: users can add new items to their shopping carts and update previously added items</li>
<li>Managing purchase orders: users can create orders and manage them, users can get list of history of all previous orders </li>

<h5>Admin use cases:</h5>
<li>Managing book catalog: admins can add, update and remove books and book categories</li>
<li>Managing order statuses: admins can update statuses of existing orders</li>

### List of used technologies
<li>Java 17</li>
<li>Spring Boot</li>
<li>Spring Security</li>
<li>Spring Data JPA</li>
<li>Liquibase</li>
<li>Lombok</li>
<li>MapStruct</li>
<li>MySQL</li>
<li>Swagger</li>

### Endpoints API
<h5>Registration and Authentication</h5>

| **HTTP method** | **Endpoint**                 | **Role**  | **Function**                                             |
|-----------------|------------------------------|-----------|:---------------------------------------------------------|
| POST            | /api/auth/registration       | ALL       | Register a new user                                      |
| POST            | /api/auth/login              | ALL       | Get access to user's account                             |

<h5>Book controller</h5>

| **HTTP method** | **Endpoint**                 | **Role**  | **Function**                                             |
|-----------------|------------------------------|-----------|----------------------------------------------------------|
| POST            | /api/books                   | ADMIN     | Add a new book to the catalog                            |
| GET             | /api/books                   | ALL       | Get a list of all books                                  |
| GET             | /api/books/{id}              | ALL       | Get one specific book                                    |
| GET             | /api/books/search            | ADMIN     | Get a list of books within specified parameters          |
| PUT             | /api/books/{id}              | ADMIN     | Update information about specific book                   |
| DELETE          | /api/books/{id}              | ADMIN     | Remove book from catalog                                 |

<h5>Category controller</h5>

| **HTTP method** | **Endpoint**                 | **Role**  | **Function**                                             |
|-----------------|------------------------------|-----------|----------------------------------------------------------|
| POST            | /api/categories              | ADMIN     | Create a new book category                               |
| GET             | /api/categories              | USER      | Get list of all book categories                          |
| GET             | /api/categories/{id}         | USER      | Get specific book category by id                         |
| GET             | /api/categories/{id}/books   | USER      | Get a list of books by category id                       |
| PUT             | /api/categories/{id}         | ADMIN     | Update book category by id                               |
| DELETE          | /api/categories/{id}         | ADMIN     | Remove book category                                     |

<h5>Shopping cart controller</h5>

| **HTTP method** | **Endpoint**                      | **Role**   | **Function**                                            |
|-----------------|-----------------------------------|------------|---------------------------------------------------------|
| POST            | /api/cart                         | USER       | Add book to the current user's shopping cart            |
| GET             | /api/cart                         | USER       | Get list of items from the current user's shopping cart |
| PUT             | /api/cart/cart-items/{cartItemId} | USER       | Update cart item by id in the current shopping cart     |
| DELETE          | /api/cart/cart-items/{cartItemId} | USER       | Remove cart item from the shopping cart                 |

<h5>Order controller</h5>

| **HTTP method** | **Endpoint**                         | **Role** | **Function**                                   |
|-----------------|--------------------------------------|----------|------------------------------------------------|
| POST            | /api/orders                          | USER     | Allow user to place an order                   |
| GET             | /api/orders                          | USER     | Get user's order history                       |
| GET             | /api/orders/{id}/items               | USER     | Get list of order items by order id            |
| GET             | /api/orders/{orderId}/items/{itemId} | USER     | Retrieve a specific order item within an order |
| PATCH           | /api/orders/{id}                     | ADMIN    | Update the status of the order                 |

### How to run

Follow next steps to run the project:
1. Clone this repository: `git clone https://github.com/yevhenkukharchuk/online-book-store.git`
2. Navigate to the project directory: `cd online-book-store`
3. Build the project with Maven: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

### Contacts

I would be appreciated to receive any feedback on this project.
For additional information please contact me:
e-mail: eukukharchuk@gmail.com
