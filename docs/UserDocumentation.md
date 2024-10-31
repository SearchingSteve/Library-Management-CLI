# User Documentation
 
## Table of Contents
1. [Overview](#1-overview)
2. [How to Start and Access the Application](#2-how-to-start-and-access-the-application)
3. [Class Diagram](#3-class-diagram)
4. [Class Overview and Explanation](#4-class-overview-and-explanation)
   - [LibraryMenu](#41-librarymenu)
   - [Library](#42-library)
   - [LibraryItem (Abstract Class)](#43-libraryitem-abstract-class)
   - [Book (Subclass of LibraryItem)](#44-book-subclass-of-libraryitem)
   - [Periodical (Subclass of LibraryItem)](#45-periodical-subclass-of-libraryitem)
   - [Author](#46-author)
   - [Patron (Abstract Class)](#47-patron-abstract-class)
   - [Student (Subclass of Patron)](#48-student-subclass-of-patron)
   - [Employee (Subclass of Patron)](#49-employee-subclass-of-patron)

---
 
## 1. Overview
 
This application is a **Library Management System** developed to streamline the management of **library items**, **authors**, and **patrons**. The system enables users to **add, edit, and remove** both authors and library items (which include two main types **books** and **periodicals**).
 
### Key Features:
 
- **Library Items Management**:
  - Library items can be **added, edited, and removed** in the system.
  - Each item is associated with its **author** and is added to the **author's list of works** as well as the **library’s collection**.
 
- **Author Management**:
  - **Authors** can also be **added, edited, and removed** as needed.
  - Each author maintains a **list of authored items** (a record of books or periodicals they have written).
 
- **Patron Management**:
  - **Patrons** are able to **borrow and return items**, with each borrowed item tracked for each individual patron.
 
- **Display Management**:
  - The system provides options to **display library items, authors, and patrons** based on specific input criteria:
    - **Input a blank value** to display all items, authors, or patrons.
    - **Input a single ID** to display the corresponding item, author, or patron.
    - **Input multiple IDs separated by commas** to display multiple specified items, authors, or patrons at once.
 
For ease of use, the application includes an option to initialize **mock data** for demonstration and testing purposes. This comprehensive setup allows users to efficiently manage and display all aspects of library administration.

---
## 2. How to Start and Access the Application
 
1. **Setup**: 
   - Ensure you have Java installed.
   - Place all Java files in an organized directory structure according to their package names (e.g., `main.library`, `main.items`, etc.).
 
2. **Running the Application**:
   - Compile all Java files using a Java IDE or the command line:
     ```bash
     javac main/library/LibraryMenu.java
     ```
   - Run the main menu:
     ```bash
     java main.library.LibraryMenu
     ```
   - You may also run the application in your favourite IDE using the built-in runtime environment. **Ensure you have all neccesary packages and extensions downloaded.**
   - The program's menu will now be displayed in the command line interface. Input the number that corresponds to the part of the program you wish to access (1-15) and press return, and follow the on-screen prompts to proceed.
   - Once you are done using the application, return to the main menu, and type option "15" to exit.


## 3. Class Diagram
 
The class diagram below depicts the relationships between classes, including associations between `Library`, `LibraryItem` (and its subclasses), `Author`, and `Patron` (and its subclasses).
![Midterm UML](https://github.com/user-attachments/assets/d069077a-b10d-44bd-bc39-be0ef7f46f6d)
  

## 4. Class Overview and Explanation
 
### 4.1 `LibraryMenu`
- **Purpose**: Provides a console-based menu (with valid options randing from numbers 1-15) for interacting with the library system.
- **Responsibilities**: Allows users to interact with the library system to add, edit, and delete a library item, author, or patron. It also provides methods for borrowing or returning a library item, in addition to displaying/searching for library items, patrons, or authors.

### 4.2 `Library`
- **Purpose**: Main class for managing library items and patrons.
- **Responsibilities**: Stores all library items and patrons, provides methods to add, remove, lend, and return library items to patrons, and handles mock data initialization.
- **Key Methods**:
  - `initializeMockData()`: Initializes sample data for library items and patrons.
  - `getAllItems()`, `getAllPatrons()`: Retrieve lists of items and patrons.
  - `lendLibraryItem()`, `returnLibraryItem()`: Manage lending and returning items to and from patrons.
 
### 4.3 `LibraryItem` (Abstract Class)
- **Purpose**: Base class for all library items (Books and Publications).
- **Attributes**: Contains common properties like `itemID`, `title`, `ISBN`, `publisher`, `availableCopies`, and `totalCopies`.
- **Subclasses**:
  - `Book`: Represents books in the library (can be `Printed`, `Electronic`, or `Audio`).
  - `Periodical`: Represents periodicals in the library (can be `Printed` or `Electronic`).
- **Key Methods**:
  - `getAvailableCopies()`, `setAvailableCopies()`: Manage item availability.
  - `getTitle()`, `getISBN()`: Retrieve basic item details.
 
### 4.4 `Book` (Subclass of `LibraryItem`)
- **Purpose**: Represents a book in the library with the additional `bookType` parameter.
- **Attributes**: Inherits from `LibraryItem` and includes specific attributes like `bookType` (Printed, Electronic, or Audio).
 
### 4.5 `Periodical` (Subclass of `LibraryItem`)
- **Purpose**: Represents a periodical in the library with the additional `periodicalType` parameter.
- **Attributes**: Inherits from `LibraryItem` and includes specific attributes like `periodicalType` (Printed, Electronic, or Audio).
 
### 4.6 `Author`
- **Purpose**: Represents the author of the library items.
- **Attributes**: Includes `authorName`, `DOB` (Date of Birth), and a `listOfItems`.
- **Key Methods**:
  - `getName()`, `getDOB()`, `getListOfItems()`: Retrieve author details and their works.
  - `addLibraryItem()`: Adds an item authored by this author.
 
### 4.7 `Patron` (Abstract Class)
- **Purpose**: Base class for all library patrons (Students and Employees).
- **Attributes**: Includes `id`, `name`, `address`, `phoneNumber`, and `borrowedLibraryItems`.
- **Subclasses**:
  - `Student`: Represents student patrons.
  - `Employee`: Represents employee patrons.
- **Key Methods**:
  - `addBorrowedItem()`, `returnBorrowedItem()`: Manage borrowing and returning items for patrons.
  - `getType()`: Abstract method for subclass-specific type (e.g., Student or Employee).
 
### 4.8 `Student` (Subclass of `Patron`)
- **Purpose**: Represents a student patron.
 
### 4.9 `Employee` (Subclass of `Patron`)
- **Purpose**: Represents an employee patron.
 

 

---
 
