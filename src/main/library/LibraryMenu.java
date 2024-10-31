package main.library;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Scanner;

import main.patrons.Patron;
import main.patrons.Student;
import main.authors.Author;
import main.items.Book;
import main.items.LibraryItem;
import main.items.Periodical;
import main.patrons.Employee;

/**
 * The LibraryMenu class provides a command-line interface for managing library
 * items, authors, and patrons.
 * Users can add, edit, delete, borrow, and return library items; manage authors
 * and patrons; and display data.
 */
public class LibraryMenu {

    /**
     * Main method to display the library management menu and handle user input.
     * Users can select various options to interact with library items, authors, and
     * patrons.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        int choice = 0;

        // Initialize mock data - library items, authors, patrons
        library.initializeMockData();

        /**
         * Displays the library management menu with options for managing items,
         * authors, and patrons.
         */
        do {
            System.out.println("----------------------------------------");
            System.out.println("Welcome to the Library Management System");
            System.out.println("----------------------------------------");
            System.out.println(" 1. Library Item - Add");
            System.out.println(" 2. Library Item - Edit");
            System.out.println(" 3. Library Item - Delete");
            System.out.println(" 4. Library Item - Borrow");
            System.out.println(" 5. Library Item - Return");
            System.out.println(" 6. Author - Add");
            System.out.println(" 7. Author - Edit");
            System.out.println(" 8. Author - Delete");
            System.out.println(" 9. Patron - Add");
            System.out.println("10. Patron - Edit");
            System.out.println("11. Patron - Delete");
            System.out.println("12. Display - Library Items");
            System.out.println("13. Display - Patrons");
            System.out.println("14. Display - Authors");
            System.out.println("15. Exit");
            System.out.println("----------------------------------------");
            System.out.print("Enter your choice (1-15): ");

            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input.trim());
                System.out.println("----------------------------------------");

                switch (choice) {
                    /**
                     * Adds a new library item based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance to which the item is added.
                     */
                    case 1:
                        String itemType = "";
                        System.out.println("Adding a new library item...");
                        while (true) {
                            System.out.print("Enter the type of Library item (Book/Periodical): ");
                            itemType = scanner.nextLine();

                            if (!itemType.equalsIgnoreCase("Book") && !itemType.equalsIgnoreCase("Periodical")) {
                                System.out.println("Invalid item type. Please enter either 'Book' or 'Periodical'.");
                            } else {
                                break;
                            }
                        }

                        String itemID = "";
                        while (true) {
                            // Prompt user to enter item ID and append "B" or "P" to the start based on item
                            // type
                            if (itemType.equalsIgnoreCase("Book")) {
                                // ID starts with "B" if book
                                System.out.print("Enter item ID: B");
                            } else {
                                // ID starts with "P" if periodical
                                System.out.print("Enter item ID: P");
                            }

                            // Validate item ID entered to not be blank, and display messages if item is
                            // already in the system
                            itemID = scanner.nextLine();
                            if (itemID == null || itemID.isEmpty()) {
                                System.out.println(
                                        "\u001B[31mItem ID cannot be empty. Please enter a valid item ID.\u001B[0m");
                            } else if (itemType.equalsIgnoreCase("Book")) {
                                if (library.getItemByID("B" + itemID) != null) {
                                    System.out.println(
                                            "Item with ID B" + itemID
                                                    + " already exists. Please enter a different ID.");
                                } else {
                                    break;
                                }
                            } else if (itemType.equalsIgnoreCase("Periodical")) {
                                if (library.getItemByID("P" + itemID) != null) {
                                    System.out.println(
                                            "Item with ID P" + itemID
                                                    + " already exists. Please enter a different ID.");
                                } else {
                                    break;
                                }
                            }
                        }

                        // Prompt user to enter a title
                        String title = "";
                        while (true) {
                            System.out.print("Enter title: ");
                            title = scanner.nextLine();
                            if (title.isEmpty()) {
                                System.out.println("Title cannot be empty. Please enter a valid title.");
                            } else {
                                break;
                            }
                        }

                        // Prompt user to enter an author
                        String authorName = "";
                        while (true) {
                            System.out.print("Enter author name: ");
                            authorName = scanner.nextLine();
                            if (authorName.isEmpty()) {
                                System.out.println("Author name cannot be empty.");
                            } else {
                                break;
                            }
                        }

                        System.out.print("Enter author name: ");
                        // Prompt user to enter the authors DOB
                        System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
                        String authorDOBString = scanner.nextLine();
                        Date authorDOB = null;

                        while (true) {
                            // Validate author DOB using the method in Author class
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

                        // Prompt user to enter ISBN
                        String ISBN = "";
                        while (true) {
                            System.out.print("Enter ISBN: ");
                            ISBN = scanner.nextLine();

                            // Validate ISBN to not be empty, and to only contain 10/13 numbers
                            if (!ISBN.isEmpty()) {
                                if (ISBN.matches("\\d{10}|\\d{13}")) {
                                    break;
                                }
                                // Check if ISBN already exists in the library
                                else if (library.getItemByID(ISBN) != null) {
                                    System.out.println("Item with ISBN " + ISBN
                                            + " already exists. Please enter a different ISBN.");
                                }
                                // Check if ISBN is 10 or 13 digits long
                                else {
                                    System.out.println("ISBN must contain numbers only and be 10 or 13 digits long.");
                                }
                                // Check if ISBN is empty
                            } else {
                                System.out.println("ISBN cannot be empty. Please enter a valid ISBN.");
                            }
                        }

                        // Prompt user to enter publisher
                        System.out.print("Enter publisher: ");
                        String publisher = scanner.nextLine();
                        // Prompt user to enter total copies of item
                        System.out.print("Enter total copies in the system: ");
                        int totalCopies;
                        do {
                            // Validate total copies to not be negative
                            totalCopies = scanner.nextInt();
                            if (totalCopies < 0) {
                                System.out.println("Total copies cannot be negative. Please enter a valid number.");
                            }
                        } while (totalCopies < 0);

                        // Prompt user to enter available copies
                        System.out.print("Enter available copies: ");
                        int availableCopies;
                        do {
                            availableCopies = scanner.nextInt();
                            if (availableCopies < 0) {
                                // Validate available copies to not be negative and not exceed total copies
                                // entered
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
                            while (true) {
                                System.out.print("Enter book type (Printed, Electronic, Audio): ");
                                String bookType = scanner.nextLine();

                                if (!bookType.equalsIgnoreCase("Printed") && !bookType.equalsIgnoreCase("Electronic")
                                        && !bookType.equalsIgnoreCase("Audio")) {
                                    System.out.println("Book Type must be Printed, Electronic or Audio");
                                } else {
                                    // Add new item if book Type is valid
                                    newItem = new Book(itemID, title, author, ISBN, publisher, availableCopies,
                                            totalCopies,
                                            bookType);
                                    break;
                                }
                            }
                        }

                        // Check if type is periodical
                        else if (itemType.equalsIgnoreCase("Periodical")) {
                            while (true) {
                                // Prompt user to enter periodical type
                                System.out.print("Enter periodical type (Printed, Electronic): ");
                                String periodicalType = scanner.nextLine();
                                // Validate periodical type to be electronic or printed
                                if (!periodicalType.equalsIgnoreCase("Printed")
                                        && !periodicalType.equalsIgnoreCase("Electronic")) {
                                    System.out.println("Periodical Type must be Printed or Electronic");
                                } else {
                                    // Add new periodical if input is valid
                                    newItem = new Periodical(itemID, title, author, ISBN, publisher, availableCopies,
                                            totalCopies, periodicalType);
                                    break;
                                }
                            }
                        }
                        library.addLibraryItem(newItem); // add new library item to library
                        break;

                    /**
                     * Edits an existing library item based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance from which the item is edited.
                     */
                    case 2:
                        System.out.print(
                                "Editing an existing library item...\nPlease enter item ID (leave blank to exit this step): ");
                        String editItemID = scanner.nextLine();

                        // If user enters nothing, wait one second then return to main menu
                        if (editItemID.isEmpty()) {
                            System.out.println("Returning to main menu...");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                            break;
                        }

                        LibraryItem itemToEdit = library.getItemByID(editItemID);
                        if (itemToEdit == null) {
                            System.out.println("Item not found.");
                            break;
                        } else {
                            System.out.println("Item found. Editing: " + itemToEdit.getTitle() + " by "
                                    + itemToEdit.getAuthor().getName() + ".");

                            System.out.println("Please enter new details for the item");
                            System.out.println("Current title: " + itemToEdit.getTitle());

                            // Prompt user to input new title
                            System.out.print("Enter new title (leave blank and hit enter to keep unchanged): ");
                            String newTitle = scanner.nextLine();

                            // If empty, keep the same
                            if (!newTitle.isEmpty()) {
                                itemToEdit.setTitle(newTitle);
                            }

                            // Edit author info
                            // Display current value
                            System.out.println("Current Author: " + itemToEdit.getAuthor().getName());
                            // prompt user to enter new name
                            System.out.print("Enter new author (leave blank and hit enter to keep unchanged): ");
                            String newAuthorName = scanner.nextLine();

                            if (!newAuthorName.isEmpty()) {
                                // Prompt user to enter new DOB. If empty, keep the same
                                System.out.print("Enter the new author's date of birth (yyyy-mm-dd): ");
                                String newAuthorDOBString = scanner.nextLine();
                                Date newAuthorDOB = null;
                                while (true) {
                                    // Validate author DOB entered using the method in Author class
                                    if (Author.validateAuthorDOB(newAuthorDOBString)) {
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        dateFormat.setLenient(false);
                                        try {
                                            newAuthorDOB = dateFormat.parse(newAuthorDOBString);
                                            itemToEdit.setAuthor(new Author(newAuthorName, newAuthorDOB));
                                            break;
                                        } catch (ParseException e) {
                                            newAuthorDOBString = scanner.nextLine();
                                        }
                                    } else {
                                        newAuthorDOBString = scanner.nextLine();
                                    }
                                }
                            }

                            System.out.println("Current ISBN: " + itemToEdit.getISBN());
                            while (true) {
                                // Prompt user to input new ISBN
                                System.out.print("Enter new ISBN (leave blank and hit enter to keep unchanged): ");
                                String newISBN = scanner.nextLine();

                                // Validate ISBN to be numbers only and 10/13 digits long. If empty, keep the
                                // same

                                if (!newISBN.isEmpty()) {
                                    if (newISBN.matches("\\d{10}|\\d{13}")) {
                                        itemToEdit.setISBN(newISBN);
                                        break;
                                    }
                                    // Check if ISBN already exists in the library
                                    else if (library.getItemByID(newISBN) != null) {
                                        System.out.println("Item with ISBN " + newISBN
                                                + " already exists. Please enter a different ISBN.");
                                    } else {
                                        System.out
                                                .println("ISBN must contain numbers only and be 10 or 13 digits long.");
                                    }
                                } else {
                                    break; // Break the loop if the input is empty
                                }
                            }

                            System.out.println("Current Publisher: " + itemToEdit.getPublisher());
                            // Prompt user to enter new publisher
                            System.out.print("Enter new publisher (leave blank and hit enter to keep unchanged): ");
                            String newPublisher = scanner.nextLine();

                            // If empty, keep the same
                            if (!newPublisher.isEmpty()) {
                                itemToEdit.setPublisher(newPublisher);
                            }

                            System.out.println("Current Total Copies: " + itemToEdit.getTotalCopies());
                            while (true) {
                                // Prompt user to input new total copies value
                                System.out
                                        .print("Enter New Total Copies (keep blank and hit enter to keep unchanged): ");
                                String newTotalCopies = scanner.nextLine();

                                // Validate total copies to be a positive number
                                // if empty, keep the same

                                if (!newTotalCopies.isEmpty()) {
                                    try {
                                        int newTotalCopiesInt = Integer.parseInt(newTotalCopies);
                                        if (newTotalCopiesInt > 0) {
                                            itemToEdit.setTotalCopies(newTotalCopiesInt);
                                            break;
                                        } else {
                                            System.err.println("Total Copies Must Exceed 0.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Total copies must be a numeric value.");
                                    }
                                } else {
                                    break;
                                }
                            }

                            // edit available copies
                            // display current value
                            System.out.println("Current Available Copies: " + itemToEdit.getAvailableCopies());

                            while (true) {
                                // prompt user to enter new available copies value
                                System.out.print(
                                        "Enter new availale copies (leave blank and hit enter to keep unchanged): ");
                                String newAvailableCopies = scanner.nextLine();

                                // validate available copies to be numeric, and not exceed total copies entered.
                                // if empty, keep the same
                                if (!newAvailableCopies.isEmpty()) {
                                    try {
                                        int newAvailableCopiesInt = Integer.parseInt(newAvailableCopies);
                                        if (newAvailableCopiesInt <= itemToEdit.getTotalCopies()) {
                                            itemToEdit.setAvailableCopies(newAvailableCopiesInt);
                                            break;
                                        } else {
                                            System.out.println("Available copies cannot exceed total copies.");
                                        }

                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Available copies must be a numeric value.");
                                    }
                                } else {
                                    break;
                                }
                            }

                        }
                        System.out.println("Item Edited Successfully!");
                        break;

                    /**
                     * Deletes a library item based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance from which the item is deleted.
                     */
                    case 3:
                        System.out.print("Deleting an existing library item...\nPlease enter item ID: ");
                        String deleteItemID = scanner.nextLine();
                        library.removeLibraryItem(deleteItemID);
                        break;

                    /**
                     * Borrows a library item by a patron based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance from which the item is borrowed.
                     */
                    case 4:
                        System.out.print("Borrowing a library item...\nPlease enter item ID: ");
                        String borrowItemID = scanner.nextLine();
                        LibraryItem itemToBorrow = library.getItemByID(borrowItemID);
                        if (itemToBorrow == null) {
                            System.out.println("Item not found.");
                            break; // break out of the case statement.
                        }
                        if (itemToBorrow.getAvailableCopies() <= 0) {
                            System.out.println("No copies currently available for item ID: " + borrowItemID);
                            break;
                        }
                        System.out.print("Please enter patron ID: ");
                        String patronID = scanner.nextLine();
                        Patron patron = library.getPatronByID(patronID);
                        if (patron == null) {
                            break; // break out of the case statement.
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
                        break;

                    /**
                     * Returns a borrowed library item based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance to which the item is returned.
                     */
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
                        break;

                    // Add an author
                    /**
                     * Adds a new author based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance where the author is added.
                     */
                    case 6:
                        System.out.println("Adding an author..");
                        authorName = "";
                        while (true) {
                            System.out.print("Enter author name: ");
                            authorName = scanner.nextLine();
                            if (authorName.isEmpty()) {
                                System.out.println("Author name cannot be empty.");
                            } else if (Author.getAuthorByName(authorName) != null) {
                                System.out.println(
                                        "Author " + authorName + " already exists. Please enter a different name.");
                            } else {
                                break;
                            }
                        }

                        System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
                        authorDOBString = scanner.nextLine();
                        authorDOB = null;
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
                        author = new Author(authorName, authorDOB);
                        library.addAuthor(author); // Add author to the author list

                        // Prompt to add authored items to the author
                        System.out.print("Would you like to add items authored by " + authorName + "? (Y/N): ");
                        String addItems = scanner.nextLine();
                        if (addItems.equalsIgnoreCase("Y")) {
                            System.out.print("Enter the number of items to add: ");
                            int numItems = scanner.nextInt();
                            scanner.nextLine();

                            // Run the code from case 1 to add items to the author and repeats an equal
                            // number to the number of items to add
                            for (int i = 0; i < numItems; i++) {
                                ////
                                System.out.println("----------------------------------------");
                                System.out.println("Enter the details for " + authorName + "'s item #" + (i + 1));
                                System.out.println("----------------------------------------");
                                System.out.println("Adding a new library item...");
                                while (true) {
                                    // prompt user to enter type of libraryItem
                                    System.out.print("Enter the type of Library item (Book/Periodical): ");
                                    itemType = scanner.nextLine();

                                    // validate library item type entered
                                    if (!itemType.equalsIgnoreCase("Book")
                                            && !itemType.equalsIgnoreCase("Periodical")) {
                                        System.out.println(
                                                "Invalid item type. Please enter either 'Book' or 'Periodical'.");
                                    } else {
                                        break;
                                    }
                                }

                                // Prompt user to enter item ID and append "B" or "P" to the start based on item
                                // type
                                if (itemType.equalsIgnoreCase("Book")) {
                                    System.out.print("Enter item ID: B");
                                } else {
                                    System.out.print("Enter item ID: P");

                                }

                                itemID = scanner.nextLine();
                                if (itemID == null || itemID.isEmpty()) {
                                    System.out.println(
                                            "\u001B[31mItem ID cannot be empty. Please enter a valid item ID.\u001B[0m");
                                    break;
                                }
                                if (itemType.equalsIgnoreCase("Book")) {
                                    if (library.getItemByID("B" + itemID) != null) {
                                        System.out.println(
                                                "Item with ID B" + itemID
                                                        + " already exists. Please enter a different ID.");
                                        break;
                                    }
                                } else {
                                    if (library.getItemByID("P" + itemID) != null) {
                                        System.out.println(
                                                "Item with ID P" + itemID
                                                        + " already exists. Please enter a different ID.");
                                        break;
                                    }
                                }

                                System.out.print("Enter title: ");
                                title = scanner.nextLine();
                                ISBN = "";
                                while (true) {
                                    System.out.print("Enter ISBN: ");
                                    ISBN = scanner.nextLine();

                                    if (!ISBN.isEmpty()) {
                                        if (ISBN.matches("\\d{10}|\\d{13}")) {
                                            break;
                                        }
                                        // Check if ISBN already exists in the library
                                        else if (library.getItemByID(ISBN) != null) {
                                            System.out.println("Item with ISBN " + ISBN
                                                    + " already exists. Please enter a different ISBN.");
                                        }
                                        // Check if ISBN is 10 or 13 digits long
                                        else {
                                            System.out.println(
                                                    "ISBN must contain numbers only and be 10 or 13 digits long.");
                                        }
                                        // Check if ISBN is empty
                                    } else {
                                        System.out.println("ISBN cannot be empty. Please enter a valid ISBN.");
                                    }
                                }

                                System.out.print("Enter publisher: ");
                                publisher = scanner.nextLine();
                                System.out.print("Enter total copies in the system: ");
                                do {
                                    totalCopies = scanner.nextInt();
                                    if (totalCopies < 0) {
                                        System.out.println(
                                                "Total copies cannot be negative. Please enter a valid number.");
                                    }
                                } while (totalCopies < 0);

                                System.out.print("Enter available copies: ");
                                do {
                                    availableCopies = scanner.nextInt();
                                    if (availableCopies < 0) {
                                        System.out.print(
                                                "Available copies cannot be negative. Please enter a valid number.");
                                        System.out.print("Enter available copies: ");
                                    } else if (availableCopies > totalCopies) {
                                        System.out.print(
                                                "Available copies cannot exceed total copies. Please enter a valid number.");
                                        System.out.print("Enter available copies: ");
                                    }
                                } while (availableCopies < 0 || availableCopies > totalCopies);

                                scanner.nextLine();
                                newItem = null;

                                // Check if item type is book
                                if (itemType.equalsIgnoreCase("Book")) {
                                    System.out.print("Enter book type (e.g. Print, Electronic, Audio): ");
                                    String bookType = scanner.nextLine();
                                    newItem = new Book(itemID, title, author, ISBN, publisher, availableCopies,
                                            totalCopies,
                                            bookType);
                                }

                                // Check if item type is periodical
                                else if (itemType.equalsIgnoreCase("Periodical")) {
                                    System.out.print("Enter periodical type (e.g. Print, Electronic): ");
                                    String periodicalType = scanner.nextLine();
                                    newItem = new Periodical(itemID, title, author, ISBN, publisher, availableCopies,
                                            totalCopies,
                                            periodicalType);
                                }
                                library.addLibraryItem(newItem); // add new library item to library
                            }
                        }
                        break;

                    /**
                     * Edits an existing author based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     */

                    case 7:
                        System.out.println("Editing an author..");
                        authorName = "";
                        while (true) {
                            System.out.print("Enter author name to edit: ");
                            authorName = scanner.nextLine();
                            if (authorName.isEmpty()) {
                                System.out.println("Author name cannot be empty.");
                            } else {
                                break;
                            }
                        }

                        String newAuthorName = "";
                        while (true) {
                            System.out.print("Enter new author name: ");
                            newAuthorName = scanner.nextLine();
                            if (newAuthorName.isEmpty()) {
                                System.out.println("New Author name cannot be empty.");
                            } else {
                                break;
                            }
                        }

                        System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
                        authorDOBString = scanner.nextLine();
                        authorDOB = null;

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
                        // Edit author name to new author name and
                        Author.editAuthor(authorName, newAuthorName, authorDOB);
                        break;

                    /**
                     * Deletes an author and all their associated items from the library based on
                     * user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance from which the author is deleted.
                     */
                    case 8:
                        System.out.println("Deleting an author...");
                        System.out.print("Enter author name to delete: ");
                        authorName = scanner.nextLine();

                        // Remove the author from the author list and delete their items from the
                        // library
                        boolean canRemoveItems = Author.removeAuthor(authorName);
                        System.out.println("canRemoveItems: " + canRemoveItems);
                        if (canRemoveItems) {
                            library.removeAuthorItems(Author.getAuthorByName(authorName));
                            System.out.println("Author " + authorName + " and all associated items have been deleted.");
                        } else {
                            System.out.println("Author " + authorName + " not found.");
                        }
                        break;

                    /**
                     * Adds a new patron based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance where the patron is added.
                     */
                    case 9:

                        String patronType = "";
                        while (true) {
                            // validate patrontype to be "Student" or "Employee"
                            System.out.print("Enter patron type (student/employee): ");
                            patronType = scanner.nextLine().trim();
                            if (patronType.equalsIgnoreCase("Student") || patronType.equalsIgnoreCase("Employee")) {
                                break;
                            } else if (patronType.isEmpty()) {
                                System.out.println("Patron type cannot be empty.");
                            } else {
                                System.out.println(
                                        "Invalid patron type entered. Patron type must be student or employee");
                            }
                        }
                        String addPatronID = "";
                        while (true) {
                            // Prompt user to enter patron ID and append "S" or "E" to the start based on
                            // item
                            // type
                            if (patronType.equalsIgnoreCase("Student")) {
                                // ID starts with "S" if Student
                                System.out.print("Enter patron ID: S");
                            } else {
                                // ID starts with "E" if Employee
                                System.out.print("Enter patron ID: E");
                            }

                            // Validate patron ID entered to not be blank, and display messages if item is
                            // already in the system
                            addPatronID = scanner.nextLine();
                            if (addPatronID == null || addPatronID.isEmpty()) {
                                System.out.println(
                                        "\u001B[31mPatron ID cannot be empty. Please enter a valid ID.\u001B[0m");
                            } else if (patronType.equalsIgnoreCase("Student")) {
                                if (library.validatePatronByID("S" + addPatronID) != null) {
                                    System.out.println(
                                            "Item with ID S" + addPatronID
                                                    + " already exists. Please enter a different ID.");
                                } else {
                                    break;
                                }
                            } else if (patronType.equalsIgnoreCase("Employee")) {
                                if (library.validatePatronByID("E" + addPatronID) != null) {
                                    System.out.println(
                                            "Item with ID E" + addPatronID
                                                    + " already exists. Please enter a different ID.");
                                } else {
                                    break;
                                }
                            }
                        }

                        // String addPatronID;
                        // while (true) {
                        // // prompt user to enter patron id
                        // System.out.print("Add a Patron...\nEnter Patron ID: (DO NOT NEED TO ADD
                        // PREFIX) ");
                        // addPatronID = scanner.nextLine().trim();
                        // // ensure id is not empty
                        // if (addPatronID.isEmpty()) {
                        // System.out.println("ID cannot be empty.");
                        // // ensure id is not already in system
                        // } else if (library.validatePatronByID(addPatronID) != null) {
                        // System.out.println("This patron already exists.");
                        // } else {
                        // break;
                        // }
                        // }
                        // prompt user to enter patron name
                        System.out.print("Enter patron name: ");
                        String patronName = scanner.nextLine();
                        if (patronName.isEmpty()) {
                            System.out.println("Patron name cannot be empty. Please enter a valid name.");
                            break;
                        }
                        // prompt user to enter patron address
                        System.out.print("Enter patron address: ");
                        String patronAddress = scanner.nextLine();
                        if (patronAddress.isEmpty()) {
                            System.out.println("Patron address cannot be empty. Please enter a valid address.");
                            break;
                        }
                        // prompt user to enter patron phone number
                        System.out.print("Enter patron phone number: ");
                        String patronPhoneNum = scanner.nextLine();
                        if (patronPhoneNum.isEmpty()) {
                            System.out
                                    .println("Patron phone number cannot be empty. Please enter a valid phone number.");
                            break;
                        }

                        Patron newPatron;
                        // if patron type = student, add new student
                        if (patronType.equalsIgnoreCase("Student")) {
                            newPatron = new Student(addPatronID, patronName, patronAddress, patronPhoneNum);
                        } else {
                            // if patron type = employee, add new employee
                            newPatron = new Employee(addPatronID, patronName, patronAddress, patronPhoneNum);
                        }

                        // add patron to patronMap
                        library.addPatron(newPatron);
                        // display sucess messsage
                        System.out.println("Patron added successfully!");
                        break;

                    /**
                     * Edits an existing patron based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance from which the patron is edited.
                     */
                    case 10:
                        // prompt user to enter patron ID
                        System.out.print("Edit a Patron...\nPlease Enter Patron ID: ");
                        String editPatronID = scanner.nextLine();
                        Patron patronToEdit = library.getPatronByID(editPatronID);

                        // alert if patron cannot be found in system
                        if (patronToEdit == null) {
                            System.out.println("Patron not found.");
                            break;
                        }

                        // display current patron name
                        System.out.println("Current Name: " + patronToEdit.getName());
                        // prompt user to enter new patron name
                        System.out.print("Enter new name (leave blank and hit enter to keep unchanged): ");
                        String newPatronName = scanner.nextLine();

                        // if empty, keep the same
                        if (!newPatronName.isEmpty()) {
                            patronToEdit.setName(newPatronName);
                        }

                        // display current address
                        System.out.println("Current Address: " + patronToEdit.getAddress());
                        // prompt user to enter new address
                        System.out.print("Enter new address (leave blank and hit enter to keep unchanged): ");
                        String newPatronAddress = scanner.nextLine();

                        // if empty, keep the same
                        if (!newPatronAddress.isEmpty()) {
                            patronToEdit.setAddress(newPatronAddress);
                        }

                        // display current phone number
                        System.out.println("Current Phone Number: " + patronToEdit.getPhoneNumber());
                        // prompt user to enter new phone number
                        System.out.print("Enter new phone number (leave blank and hit enter to keep unchanged): ");
                        String newPatronPhone = scanner.nextLine();

                        // if empty, keep the same
                        if (!newPatronPhone.isEmpty()) {
                            patronToEdit.setPhoneNumber(newPatronPhone);
                        }

                        // print success message
                        System.out.println("Patron Edited Sucessfully!");
                        break;

                    /**
                     * Deletes a patron from the library based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance from which the patron is deleted.
                     */
                    case 11:
                        System.out.println("Delete a Patron...\nEnter Patron ID: ");
                        patronID = scanner.nextLine();

                        Patron patronToDelete = library.getPatronByID(patronID);
                        if (patronToDelete == null) {
                            System.out.println("Patron" + patronID + "not found. Cannot be deleted");
                        } else {
                            library.removePatron(patronToDelete);
                            System.out.println("Patron Deleted Successfully!");
                        }
                        break;

                    /**
                     * Displays library items based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance whose items are displayed.
                     */
                    case 12:
                        System.out.println("Displaying library items...");
                        System.out.print(
                                "Enter itemIDs to display followed by a comma between each itemID or blank to display all items: ");
                        String itemIDs = scanner.nextLine();
                        library.displayItems(itemIDs);

                        // Pause system and prompt user to read the output before returning to the
                        // main menu
                        System.out.println();
                        System.out.print("Press 'Enter/Return' to return to the main menu...");
                        scanner.nextLine();
                        break;

                    /**
                     * Displays patrons based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     * @param library The library instance whose patrons are displayed.
                     */
                    case 13:
                        System.out.println("Displaying patrons...");
                        System.out.print(
                                "Enter patronID's to display followed by a comma between each patronID or blank to display all patrons: ");
                        String patronIDs = scanner.nextLine();
                        library.displayPatrons(patronIDs);

                        // Pause system and prompt user to read the output before returning to the
                        // main menu
                        System.out.println();
                        System.out.print("Press 'Enter/Return' to return to the main menu...");
                        scanner.nextLine();
                        break;

                    /**
                     * Displays authors based on user input.
                     *
                     * @param scanner Scanner object for reading user input.
                     */
                    case 14:
                        System.out.println("Displaying authors...");
                        System.out.print(
                                "Enter author names to display followed by a comma between each author name or blank to display all authors: ");
                        String authorNames = scanner.nextLine();
                        Author.displayAuthors(authorNames);

                        // Pause system and prompt user to read the output before returning to the
                        // main menu
                        System.out.println();
                        System.out.print("Press 'Enter/Return' to return to the main menu...");
                        scanner.nextLine();
                        break;
                    case 15:
                        System.out.println("Exiting the system. Goodbye!");
                        System.err.println("----------------------------------------");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mInvalid input. Please enter a numeric value.\u001B[0m");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                choice = 0; // Reset choice to 0 to re-enter the loop
            }
        } while (choice != 15);
        scanner.close();
    }
}
