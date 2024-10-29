//*
// This is just one example of how a menu can look using a scanner
// This isnt complete nor does it have all required sections included
// This is just meant to give you a brief idea of how a menu could look
// Good luck and have fun!
//
//
// *//

package main.library;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Scanner;

import main.patrons.Patron;
import main.authors.Author;
import main.items.Book;
import main.items.LibraryItem;
import main.items.Periodical;
import main.items.Status;

public class LibraryMenu {

    // create a list of library items to be used in menu cases
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        int choice = 0;

        library.initializeMockItems();
        library.initializeMockPatrons();

        library.displayAllItems();
        library.displayAllPatrons();

        do {
            System.out.println("----------------------------------------");
            System.out.println("Welcome to the Library Management System");
            System.out.println("----------------------------------------");
            // there are more options needed for the project.
            System.out.println("1. Add Library Item");
            System.out.println("2. Edit Library Item");
            System.out.println("3. Delete Library Item");
            System.out.println("4. Borrow Library Item");
            System.out.println("5. Return Library Item");
            System.out.println("6. Exit");
            System.out.println("----------------------------------------");
            System.out.print("Enter your choice (1-6): ");

            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input.trim());
                System.out.println("----------------------------------------");

                switch (choice) {
                    // Add a library item
                    // ** Error handling issues:
                    // - If user does specify type as Book or Periodical, the program will crash.
                    // Cannot build a new item without specifying one of these type.
                    case 1:
                        System.out.print(
                                "Adding a new library item...\nEnter the type of Library item (Book/Periodical): ");
                        String itemType = scanner.nextLine();
                        if (!itemType.equalsIgnoreCase("Book") && !itemType.equalsIgnoreCase("Periodical")) {
                            System.out.println("Invalid item type. Please enter either 'Book' or 'Periodical'.");
                            break;
                        }
                        if (itemType.equalsIgnoreCase("Book")) {
                            System.out.print("Enter item ID: B");
                        } else {
                            System.out.print("Enter item ID: P");

                        }

                        String itemID = scanner.nextLine();
                        if (itemID == null || itemID.isEmpty()) {
                            System.out.println("Item ID cannot be empty. Please enter a valid item ID.");
                            break;
                        }
                        if (itemType.equalsIgnoreCase("Book")) {
                            if (library.getItemByID("B" + itemID) != null) {
                                System.out.println(
                                        "Item with ID B" + itemID + " already exists. Please enter a different ID.");
                                break;
                            }
                        } else {
                            if (library.getItemByID("P" + itemID) != null) {
                                System.out.println(
                                        "Item with ID P" + itemID + " already exists. Please enter a different ID.");
                                break;
                            }
                        }

                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter author name: ");
                        String authorName = scanner.nextLine();
                        System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
                        String authorDOBString = scanner.nextLine();

                        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        // Date authorDOB = null;
                        // try {
                        // authorDOB = dateFormat.parse(authorDOBString);
                        // } catch (ParseException e) {
                        // System.out.println("Invalid date format. Please enter a date in the format
                        // YYYY-MM-DD.");
                        // break;
                        // }

                        Date authorDOB = null;

                        while (true) {
                            if (Author.validateAuthorDOB(authorDOBString)) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    authorDOB = dateFormat.parse(authorDOBString);
                                } catch (ParseException e) {
                                    continue;
                                }
                                break;
                            } else {
                                authorDOBString = scanner.nextLine();
                            }
                        }
                        Author author = new Author(authorName, authorDOB);

                        System.out.print("Enter ISBN: ");
                        String ISBN = scanner.nextLine();
                        System.out.print("Enter publisher: ");
                        String publisher = scanner.nextLine();
                        System.out.print("Enter total copies in the system: ");
                        int totalCopies;
                        do {
                            totalCopies = scanner.nextInt();
                            if (totalCopies < 0) {
                                System.out.println("Total copies cannot be negative. Please enter a valid number.");
                            }
                        } while (totalCopies < 0);

                        System.out.print("Enter available copies: ");
                        int availableCopies;
                        do {
                            availableCopies = scanner.nextInt();
                            if (availableCopies < 0) {
                                System.out.print("Available copies cannot be negative. Please enter a valid number.");
                                System.out.print("Enter available copies: ");
                            } else if (availableCopies > totalCopies) {
                                System.out.print(
                                        "Available copies cannot exceed total copies. Please enter a valid number.");
                                System.out.print("Enter available copies: ");
                            }
                        } while (availableCopies < 0 || availableCopies > totalCopies);

                        scanner.nextLine();
                        LibraryItem newItem = null;

                        // Assume function to add book to inventory
                        if (itemType.equalsIgnoreCase("Book")) {
                            System.out.print("Enter book type (e.g. Print, Electronic, Audio): ");
                            String bookType = scanner.nextLine();
                            newItem = new Book(itemID, title, author, ISBN, publisher, availableCopies,
                                    totalCopies, Status.AVAILABLE,
                                    bookType);
                        }

                        // Assume function to add periodical to inventory
                        else if (itemType.equalsIgnoreCase("Periodical")) {
                            System.out.print("Enter periodical type (e.g. Print, Electronic): ");
                            String periodicalType = scanner.nextLine();
                            newItem = new Periodical(itemID, title, author, ISBN, publisher, availableCopies,
                                    totalCopies, Status.AVAILABLE,
                                    periodicalType);
                        }

                        library.addLibraryItem(newItem); // add new library item to library
                        System.out.println("Item added successfully!");
                        library.displayAllItems();
                        library.displayAllPatrons();
                        break;

                    // Edit a library item
                    case 2:
                        System.out.print("Editing an existing library item...\nPlease enter item ID: ");
                        String editItemID = scanner.nextLine();
                        LibraryItem itemToEdit = library.getItemByID(editItemID);

                        if (itemToEdit == null) {
                            break;
                        } else {
                            System.out.println("Item found. Please enter new details for the item");

                            // display all current details to user including prompts to edit information.

                            // edit title
                            System.out.println("Current Title: " + itemToEdit.getTitle());
                            System.out.print("Enter new title (leave blank and hit enter to keep unchanged): ");
                            String newTitle = scanner.nextLine();

                            if (!newTitle.isEmpty()) {
                                itemToEdit.setTitle(newTitle);
                            }

                            // edit author info
                            System.out.println("Current Author: " + itemToEdit.getAuthor().getName());
                            System.out.print("Enter new author (leave blank and hit enter to keep unchanged): ");
                            String newAuthorName = scanner.nextLine();

                            if (!newAuthorName.isEmpty()) {
                                
                                System.out.print("Enter Author's date of birth (yyyy-mm-dd): ");
                                String newAuthorDOBString = scanner.nextLine();
                                Date newAuthorDOB = null;

                                while (true){

                                    if (Author.validateAuthorDOB(newAuthorDOBString)){
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        dateFormat.setLenient(false);
                                    

                                        try {
                                            newAuthorDOB = dateFormat.parse(newAuthorDOBString);
                                            itemToEdit.setAuthor(new Author(newAuthorName, newAuthorDOB));
                                            break;
                                        } catch (ParseException e) {
                                            System.out.println("Please enter date in the format yyyy-mm-dd");
                                            newAuthorDOBString = scanner.nextLine();
                                        }

                                    }else{
                                        System.out.println("Please enter date in the format yyyy-mm-dd");
                                        newAuthorDOBString = scanner.nextLine();
                                    }
                                }
                            }

                            // edit isbn
                            System.out.println("Current ISBN: " + itemToEdit.getISBN());

                            while(true){
                                System.out.print("Enter new ISBN (leave blank and hit enter to keep unchanged): ");
                                String newISBN = scanner.nextLine();
    
                                if (!newISBN.isEmpty()) {
                                    if(newISBN.matches("\\d{10}|\\d{13}")){
                                    itemToEdit.setISBN(newISBN);
                                }else{
                                    System.out.println("ISBN must contain numbers only and be 10 or 13 digits long.");
                                }
                                    
                                }
                            }
                      

                            // edit publisher
                            System.out.println("Current Publisher: " + itemToEdit.getPublisher());
                            System.out.print("Enter new publisher (leave blank and hit enter to keep unchanged): ");
                            String newPublisher = scanner.nextLine();

                            if (!newPublisher.isEmpty()) {
                                itemToEdit.setPublisher(newPublisher);
                            }

                            // edit available copies
                            System.out.println("Current Available Copies: " + itemToEdit.getAvailableCopies());
                            
                            while(true){
                                System.out.print("Enter new availale copies (leave blank and hit enter to keep unchanged): ");
                                String newAvailableCopies = scanner.nextLine();
    
                                if (!newAvailableCopies.isEmpty()) {
                                    try{
                                        int newAvailableCopiesInt = Integer.parseInt(newAvailableCopies);
                                        if(newAvailableCopiesInt <= itemToEdit.getTotalCopies()){
                                            itemToEdit.setAvailableCopies(newAvailableCopiesInt);
                                        }else{
                                            System.out.println("Available copies cannot exceed total copies.");
                                        }
                                       
                                    }catch (NumberFormatException e){
                                        System.out.println("Invalid input. Available copies must be a numeric value.");
                                    }
                                }
                            }

                        }
                        // print success message
                        System.out.println("Item Edited Successfully!");
                        break;

                    // Delete a library item
                    case 3:
                        System.out.print("Deleting an existing library item...\nPlease enter item ID: ");
                        String deleteItemID = scanner.nextLine();
                        library.removeLibraryItem(deleteItemID);
                        break;

                    // Borrow a library item
                    case 4:
                        System.out.print("Borrowing a library item...\nPlease enter item ID: ");
                        String borrowItemID = scanner.nextLine();
                        LibraryItem itemToBorrow = library.getItemByID(borrowItemID);
                        if (itemToBorrow == null) {
                            System.out.println("Item not found."); // DOES NOT PRINT
                            break; // break out of the case statement. Could replace to ask user for input again.
                        }
                        if (itemToBorrow.getAvailableCopies() <= 0) {
                            System.out.println("No copies currently available for item ID: " + borrowItemID);
                            break;
                        }
                        System.out.print("Please enter patron ID: ");
                        String patronID = scanner.nextLine();
                        Patron patron = library.getPatronByID(patronID);
                        if (patron == null) {
                            break; // break out of the case statement. Could replace to ask user for input again.
                        }
                        int amountBorrowed = 0;
                        while (true) {
                            System.out.print("Enter the number of copies to borrow (available copies: "
                                    + itemToBorrow.getAvailableCopies() + "): ");
                            input = scanner.nextLine();
                            try {
                                amountBorrowed = Integer.parseInt(input);
                                if (amountBorrowed <= 0) {
                                    System.out.println("Please enter a positive number.");
                                } else if (amountBorrowed > itemToBorrow.getAvailableCopies()) {
                                    System.out.println("Only " + itemToBorrow.getAvailableCopies()
                                            + " copies are available. Please enter a smaller number.");
                                } else {
                                    break; // Break the loop if input is valid
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a numeric value.");
                            }
                        }

                        // Lend item to patron
                        library.lendLibraryItem(itemToBorrow, patron, amountBorrowed);
                        System.out.println("Item borrowed successfully.");

                        library.displayAllItems();
                        library.displayAllPatrons();
                        break;

                    case 5:
                        // Add logic to return a library item
                        System.out.print("Returning a library item...\nPlease enter item ID: ");
                        String returnItemID = scanner.nextLine();
                        LibraryItem itemToReturn = library.getItemByID(returnItemID);
                        if (itemToReturn == null) {
                            break; // break out of the case statement. Could replace to ask user for input again.
                        }
                        System.out.print("Please enter patron ID: ");
                        patronID = scanner.nextLine(); // reusing patronID variable, overwriting previous value
                        patron = library.getPatronByID(patronID); // reusing patron variable, overwriting previous value
                        if (patron == null) {
                            break; // break out of the case statement. Could replace to ask user for input again.
                        }
                        int borrowedCopies = patron.checkQuantityBorrowed(itemToReturn.getItemID());
                        if (borrowedCopies <= 0) {
                            System.out.println("Patron has not borrowed any copies of item ID: " + returnItemID);
                            break;
                        } else {
                            while (true) {
                                System.out.print("Enter the number of copies to return (borrowed copies: "
                                        + borrowedCopies + "): ");
                                input = scanner.nextLine();
                                try {
                                    borrowedCopies = Integer.parseInt(input);
                                    break; // Break the loop if input is valid
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a numeric value.");
                                }
                            }
                            // Return item to library
                            library.returnLibraryItem(itemToReturn, patron, borrowedCopies);
                        }

                        library.displayAllItems();
                        library.displayAllPatrons();
                        break;
                    case 6:
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                choice = 0; // Reset choice to 0 to re-enter the loop
            }
        } while (choice != 6);
        scanner.close();
    }

}
