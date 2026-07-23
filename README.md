# caveX - X Bookmark Manager (Version 1.0)

## Overview

caveX is a Java console-based bookmark management application inspired by the bookmark feature on X (formerly Twitter). It allows users to store, organize, search, update, and manage useful links in a structured way.

This project was built to strengthen backend fundamentals by applying Object-Oriented Programming, Java Collections, JDBC, and PostgreSQL in a real-world CRUD application.

---

## Features

* Add a new bookmark
* View all saved bookmarks
* Search bookmarks
* Filter bookmarks by category
* Update existing bookmarks
* Delete bookmarks
* Automatic timestamp generation for every bookmark

---

## Tech Stack

* Java
* Object-Oriented Programming (OOP)
* Java Collections Framework
* JDBC
* PostgreSQL

---

## Database Schema

| Column     | Type                                |
| ---------- | ----------------------------------- |
| id         | SERIAL PRIMARY KEY                  |
| link       | TEXT NOT NULL                       |
| category   | VARCHAR(50)                         |
| note       | TEXT                                |
| created_at | TIMESTAMP DEFAULT CURRENT_TIMESTAMP |

---

## Project Structure

```
src/
│
├── model/
├── dao/
├── database/
├── util/
└── Main.java
```

---

## Learning Objectives

This project focuses on building practical backend development skills by implementing:

* Clean object-oriented design
* CRUD operations using JDBC
* Database connectivity
* SQL query writing
* Exception handling
* Layered project structure
* PostgreSQL integration

---

## Future Enhancements

### Version 1.1

* Favorite bookmarks
* Archive bookmarks

### Version 1.2

* Tag support
* Advanced filtering

### Version 2.0

* Spring Boot REST API

### Version 3.0

* React frontend

---

## Status
 Version 1.0 is currently under development.
