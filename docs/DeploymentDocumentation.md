# Deployment Documentation

This document provides instructions for installing and running the **Library Management System** application. Follow these steps to set up and deploy the application on your local environment.

# Table of Contents

- [Prerequisites](#1-prerequisites)
- [Installation Steps](#2-installation-steps)
  - [Download the Source Code](#download-the-source-code)
  - [Set Up Directory Structure](#set-up-directory-structure)
  - [Compile the Application](#compile-the-application)
  - [Run the Application](#run-the-application)
- [Usage Instructions](#3-usage-instructions)
  - [Initializing Mock Data](#initializing-mock-data)
  - [Interacting with the System](#interacting-with-the-system)
- [Troubleshooting](#4-troubleshooting)
- [Additional Notes](#5-additional-notes)


## 1. Prerequisites

Before deploying the application, ensure that you have the following prerequisites installed:

- **Java Development Kit (JDK)** - Version 8 or higher
- **Java Runtime Environment (JRE)** - Version 8 or higher (if not included in the JDK)
- **Command Line Interface (CLI)** - To compile and run the application (or use an IDE like VSCode or IntelliJ)

## 2. Installation Steps

1. **Download the Source Code**
   - Clone or download the repository containing the source code for the Library Management System.
   - Place the downloaded files in a suitable directory.

2. **Set Up Directory Structure**
   - Ensure the Java files are organized into the correct package structure:
     - Example structure:
- **main/**: Root directory for the application
  - **authors/**: Contains classes related to authors in the library system
    - `Author.java`: Represents authors and their list of authored items

  - **items/**: Defines classes representing different types of library items
    - `LibraryItem.java`: Abstract base class for all library items
    - `Book.java`: Represents books in the library
    - `Periodical.java`: Represents periodicals, such as magazines and journals


  - **library/**: Contains core classes for managing library operations
    - `Library.java`: Main class for managing library items, authors, and patrons
    - `LibraryMenu.java`: Console-based menu interface for interacting with the library

  - **patrons/**: Manages the patrons (users) of the library system
    - `Patron.java`: Abstract base class for patrons who borrow items
    - `Student.java`: Represents student patrons
    - `Employee.java`: Represents employee patrons



3. **Compile the Application**
   - Open a terminal or command prompt.
   - Navigate to the root directory where the `main` package resides.
   - Compile the Java files using the following command:
     ```bash
     javac main/library/LibraryMenu.java
     ```
   - This will compile all required classes in the correct order if the package structure is followed.

4. **Run the Application**
   - After compiling, run the application by executing:
     ```bash
     java main.library.LibraryMenu
     ```
   - The main menu will appear, allowing you to interact with the library system.

## 3. Usage Instructions

- **Initializing Mock Data**:
  - The application includes mock data for testing and demonstration purposes which is saved only in memory . By default this data automatically initializes data upon starting the application.

- **Interacting with the System**:
  - Follow the console prompts to perform various library actions within the menu system.
- More details can be found in [User Documentation](../UserDocumentation.md)

## 4. Troubleshooting

- **Java Not Recognized**:
  - If the `javac` or `java` commands are not recognized, check that Java is installed and that your `PATH` environment variable includes the Java `bin` directory.

- **Incorrect Directory Structure**:
  - Ensure that all Java files are in the correct package directories as specified above. Incorrect directory structure will result in `package does not exist` errors during compilation.

## 5. Additional Notes

- This application runs in the **console** and requires no additional software or libraries beyond Java.
- For further customization or debugging, consider using an IDE like **VSCode** or **IntelliJ**, which can automatically manage packages and simplify the compile/run process.
