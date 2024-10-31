# Development Documentation

# Table of Contents

- [Source Code Structure](#source-code-structure)
- [Build Process](#build-process)
  - [Requirements](#requirements-for-java)
  - [Compilation and Execution](#compilation)
- [Compiler Dependencies](#compiler-dependencies)
- [Theoretical Database Design](#theoretical-database-design)
- [Retrieving and Contributing to the Repository](#retrieving-source-code-from-github-repository)

## Source Code Structure
```
src/
├── main/
│ ├── authors/
│ │ └── Author.java
│ ├── items/
│ │ ├── Book.java
│ │ ├── LibraryItem.java
│ │ └── Periodical.java
│ ├── library/
│ │ ├── Library.java
│ │ └── LibraryMenu.java # entry point for user
│ ├── patrons/
│ │ ├── Employee.java
│ │ ├── Patron.java
│ │ └── Student.java
```

## Build Process

### Requirements for Java:
- **Java Development Kit (JDK) version 8 or higher**
  - Required for Java compilation.
- **Git**
  - Required for cloning repository and contributing to project.

### Compilation:
_(in terminal)_
1. `git clone https://github.com/SearchingSteve/MidtermSprint-Java-Sem3.git`
2. `cd MidtermSprint-Java-Sem3`
3. `javac LibraryMenu.java`

### Run Library Management System:
_(in terminal)_
1. `java LibraryMenu`
2. Choose one of 15 displayed menu options by entering a number 1-15 and pressing enter.
3. Follow displayed instructions, input required values, and hit enter.
   - If an error occurs with the entered value, a message will be displayed informing the user that an invalid value was entered, and they will be prompted to re-enter.

## Compiler Dependencies

### Class Relationships
- Classes within the system depend on each other.
  - Example: `Library` needs access to `LibraryItem`, `Book`, `Periodical`, `Author`, `Patron`, `Student`, and `Employee`
- Import statements are used to manage this.
  - Example: `import main.authors.Author;`
- Subclasses are dependent on main classes:
  - `Book` and `Periodical` depend on `LibraryItem`.
  - `Student` and `Employee` depend on `Patron`.

### Java Library Dependencies
- **Data Structure**: `java.util.ArrayList`, `java.util.HashMap`, and `java.util.List` are utilized within the system and must be imported.
- **Date and Time**: `java.time.LocalDate` and `java.util.Date` are utilized within the system and must be imported.
- **Utilities**: `java.util.Scanner`, `java.util.Objects`, and `java.util.Optional` are utilized within the system and must be imported.

## Theoretical Database Design

### Authors Table
Manages a given author and their information.

| COLUMN      | TYPE       |
|-------------|------------|
| Author_id   | INT - PK   |
| Author_name | VARCHAR(150) |
| Author_DOB  | DATE       |

### LibraryItems Table
Manages items within the library system and their details, including books and periodicals.

| COLUMN          | TYPE                |
|-----------------|---------------------|
| Item_id         | INT - PK            |
| Title           | VARCHAR(150)        |
| Author_id       | INT FK REFERENCES Authors(Author_id) |
| ISBN            | VARCHAR(13)         |
| Publisher       | VARCHAR(150)        |
| Total_copies    | INT                 |
| Available_copies | INT                |

### Books Table

| COLUMN          | TYPE                 |
|-----------------|----------------------|
| Item_id       | INT - PK, FK REFERENCES LibraryItems(Item_id)             |
| Book_type | VARCHAR(10)         |

### Periodicals Table
| COLUMN          | TYPE                 |
|-----------------|----------------------|
| Item_id       | INT - PK, FK REFERENCES LibraryItems(Item_id)             |
| Periodical_type | VARCHAR(10)         |

### Patrons Table
Manages patrons within the system and their details, including students and employees.

| COLUMN          | TYPE                 |
|-----------------|----------------------|
| Patron_ID       | INT - PK             |
| Patron_name     | VARCHAR(150)         |
| Patron_address  | VARCHAR(250)         |
| Patron_phone_num | VARCHAR(10)         |
| Patron_type | VARCHAR(10)         |

<!-- Junctions for handling many to many -->
### Authored Items Table
| COLUMN          | TYPE                               |
|-----------------|------------------------------------|
| Author_id     | INT FK REFERENCES Authors(Author_id) | References the author                       |
| Item_id         | INT - PK, FK REFERENCES LibraryItems(Item_id) |

### Borrowed Items Table
| COLUMN             | TYPE                            |
|--------------------|---------------------------------|
| Patron_id          | INT FK REFERENCES Patrons(Patron_id) | 
| Item_id            | INT FK REFERENCES LibraryItems(Item_id) |
| Num_copies_borrowed | INT                            |
                   

### Retrieve Source Code from GitHub Repository

**To get a local copy of the project running:**

_(In terminal)_

`` git clone https://github.com/yourgithub/MidtermSprint-Java-Sem3.git ``

`cd MidtermSprint-Java-Sem3`

Instructions for Contributing:

**1. Create a new branch for your feature.**

_(In terminal)_

`git checkout -b feature/feature-name`

**2. Add thorough comments as you add features in your branch.**

Comments inform other contributors and other people viewing your repository

About why you made the updates that you did.

**3. Use consistent naming conventions and clear names.**

Note that the program consistently uses camelCase for variables and PascalCase for classes, other contributors should keep that consistency. Variable and method names being clear are also important (ex: authorName rather than name.)

**4. Commit changes in increments when you finish each step, including a detailed commit message.**

_(In terminal)_

`git commit -m <commit message>`

**5. Create a pull request.**

Navigate to a GitHub browser and create a pull request. This notifies other contributors of your committed changes and allows them to review them before merging your branch to the main branch.


