
# Library Inventory Management System

A web-based application to manage books in a library. Built using Spring Boot, Thymeleaf, MySQL, and Spring Security. Supports CRUD operations and CSV file upload for bulk book entry.

---

## Features

-  Add, edit, delete, and view books.
-  Upload books in bulk via CSV.
-  Login system with Spring Security (in-memory user).
-  Thymeleaf-based views (HTML templates).
-  Persistent MySQL database (can start with H2 for testing).
-  Routes protected with custom login page.

---

## Tech Stack

- Java 17+
- Spring Boot (MVC + Data JPA + Security)
- Thymeleaf
- MySQL (or H2)
- Gradle (Groovy DSL)
- Bootstrap Icons (for UI)
- Apache Commons CSV

---

##  CSV Format

Upload CSV file must follow this structure (without header line):

```
bookName,author,genre,quantity
The Hobbit,J.R.R. Tolkien,Fantasy,5
To Kill a Mockingbird,Harper Lee,Fiction,3
...
```

>  Make sure the header line is **not** included or handle it in code.

---

##  Sample Credentials

| Username | Password  |
|----------|-----------|
| admin    | admin@22  |

Configured in `SecurityConfig.java`.

---

## Setup Instructions

1. **Clone the repository**

```bash
git clone https://github.com/your-username/library-inventory.git
cd library-inventory
```

2. **Configure `application.properties`**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/librarydb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.thymeleaf.cache=false
```

3. **Run MySQL locally** and ensure `librarydb` exists (or Spring Boot will create it).

4. **Build and run the app**

```bash
./gradlew bootRun
```

5. **Access the app**

Visit: [http://localhost:8080/login]
---

## Route Mappings

| Route              | Description               |
|--------------------|---------------------------|
| `/login`           | Custom login page         |
| `/`                | Home page                 |
| `/books`           | List all books            |
| `/add-book`        | Add a new book            |
| `/edit-book?id=`   | Edit a book by ID         |
| `/delete-book?id=` | Delete a book by ID       |
| `/upload-csv`      | Upload CSV to add books   |

---

## Screenshots

<img width="1919" height="955" alt="Screenshot 2025-07-29 102829" src="https://github.com/user-attachments/assets/dbbfe6c8-1a3c-4e98-afe4-9534bee83481" />

<img width="941" height="422" alt="Screenshot 2025-07-29 102843" src="https://github.com/user-attachments/assets/786a03de-d657-400f-a203-77f2a8561923" />


<img width="1919" height="958" alt="Screenshot 2025-07-29 102853" src="https://github.com/user-attachments/assets/155364d4-9fb5-415c-aa34-955d44136b53" />

<img width="1917" height="956" alt="Screenshot 2025-07-29 103038" src="https://github.com/user-attachments/assets/188b9f07-a174-46e7-9d44-299cee5710f6" />

<img width="1391" height="278" alt="Screenshot 2025-07-29 103055" src="https://github.com/user-attachments/assets/f863ebf6-fc75-4eda-9d0f-f115ba17e632" />

<img width="1919" height="959" alt="Screenshot 2025-07-29 103110" src="https://github.com/user-attachments/assets/f9be4fb1-3e5c-408f-9b9e-a83b7baf9c05" />

---

## Project Structure (Simplified)

```
src
├── main
│   ├── java
│   │   └── com.InventoryManager.libraryInventory
│   │       ├── controller
│   │       ├── service
│   │       ├── model
│   │       └── configs
│   └── resources
│       ├── templates
│       │   ├── home.html
│       │   ├── book-list.html
│       │   ├── add-book.html
│       │   ├── edit-form.html
│       │   └── login.html
│       └── application.properties
```

---

## To-Do / Improvements

- Add user roles (ADMIN, LIBRARIAN)
- Handle malformed CSV rows gracefully
- Add pagination and search
- Unit & integration tests

---

## 👨‍💻 Developed by

**Nishanth B K**  
Java Developer Intern @ Anugraha Exceed Pvt Ltd  
[GitHub](https://github.com/Nishanth-BK) | [LinkedIn](https://www.linkedin.com/in/nishanthbk)
