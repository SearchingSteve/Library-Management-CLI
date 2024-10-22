package test; // Initialzie the current file as part of the test package

// Import the necessary classes to be tested from the main package
import main.authors.Author; // Import the Author class from the main.authors package
import main.items.Book; // Import the Book class from the main.items package
import main.items.Periodical; // Import the Periodical class from the main.items package
import main.library.Library; // Import the Library class from the main.library package
import main.patrons.Student; // Import the Student class from the main.patrons package
import main.patrons.Employee; // Import the Employee class from the main.patrons package
import main.items.Status; // Import the Status class from the main.library package

public class LibrarySystemTest {
    // **Item Management**
    // Test type of periodical (printed or electronic)

    // Test type of book (printed, electronic, audio)

    // Test adding a new item to the library

    // Test editiing an item in the library

    // Test removing an item from the library
    
    // Test item info (ID, Title, Author, ISBN, Publisher, #ofCopies **other = genre, dateOfPublication, ect**)

    // **Author Managenment**

    // Test adding a new author to the library

    // Test editing an author in the library

    // Test removing an author from the library

    // Test author info (Name, Date of birth, List of items they've written)


    // **Patron Management**
    // Test adding a new patron to the library

    // Test editing a patron in the library

    // Test removing a patron from the library

    // Test patron info (Name, Address, Phone #, List of items they've checked out, is patron student or employee)


    // **Library Management**
    // ** Item Borrowing**
    // Test patron item available search (by title, author, genre, ISBN, ect)
    
    // Test patron checking out an item (book or periodical) - single & multiple

    // Test patron checking out an item currently checked out by another patron

    // **Item Returning**
    // Test patron item return search (by title, author, genre, ISBN, ect)
    // Test patron returning an item (book or periodical) - single & multiple


    // **Item Status**
    // Test item status (`AVAILABLE`, `CHECKED_OUT`, and `OVERDUE`.)
    

    
}
