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
import main.patrons.Student;
import main.patrons.Employee;

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
            System.out.println("6. Add an Author ");
            System.out.println("7. Edit an Author");
            System.out.println("8. Delete an Author");
            System.out.println("9. Add a Patron");
            System.out.println("10.  Edit a Patron");
            System.out.println("11. Delete a Patron");
            System.out.println("12. Exit");
            System.out.println("----------------------------------------");
            System.out.print("Enter your choice (1-12): ");

            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input.trim());
                System.out.println("----------------------------------------");

                switch (choice) {
                    // Add a library item
                 
                    case 1:
                        System.out.print(
                            // prompt user to enter type of libraryItem
                                "Adding a new library item...\nEnter the type of Library item (Book/Periodical): ");
                                String itemType = scanner.nextLine();

                        // validate library item type entered
                        if (!itemType.equalsIgnoreCase("Book") && !itemType.equalsIgnoreCase("Periodical")) {
                            System.out.println("Invalid item type. Please enter either 'Book' or 'Periodical'.");
                            break;
                        }
                        
                        if (itemType.equalsIgnoreCase("Book")) {
                            // ID starts with "B" if book
                            System.out.print("Enter item ID: B");
                        } else {
                            // ID starts with "P" if periodical
                            System.out.print("Enter item ID: P");

                        }

                        // validate item ID entered to not be blank, and display messages if item is already in the system
                        String itemID = scanner.nextLine();
                        if (itemID == null || itemID.isEmpty()) {
                            System.out.println(
                                    "\u001B[31mItem ID cannot be empty. Please enter a valid item ID.\u001B[0m");
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

                        // prompt user to enter a title
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();

                        // prompt user to enter an author
                        System.out.print("Enter author name: ");
                        String authorName = scanner.nextLine();
                        // prompt user to enter the authors DOB
                        System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
                        String authorDOBString = scanner.nextLine();


                        Date authorDOB = null;

                        while (true) {
                            // validate author DOB using the method in Author class
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

                        // NEW ISBN PROMPT
                        String ISBN = "";
                        while (true) {
                            // prompt user to enter new ISBN
                            System.out.print("Enter ISBN: ");
                            ISBN = scanner.nextLine();

                            // validate ISBN to not be empty, and to only contain 10/13 numbers 
                            if (!ISBN.isEmpty()) {
                                if (ISBN.matches("\\d{10}|\\d{13}")) {
                                    break;
                                } else {
                                    System.out.println("ISBN must contain numbers only and be 10 or 13 digits long.");
                                }
                            } else {
                                System.out.println("ISBN cannot be empty. Please enter a valid ISBN.");
                            }
                        }

                        //prompt user to enter publisher
                        System.out.print("Enter publisher: ");
                        String publisher = scanner.nextLine();
                        // prompt user to enter total copies of item
                        System.out.print("Enter total copies in the system: ");
                        int totalCopies;
                        do {
                            // validate total copies to not be negative 
                            totalCopies = scanner.nextInt();
                            if (totalCopies < 0) {
                                System.out.println("Total copies cannot be negative. Please enter a valid number.");
                            }
                        } while (totalCopies < 0);

                        // prompt user to enter available copies
                        System.out.print("Enter available copies: ");
                        int availableCopies;
                        do {
                            availableCopies = scanner.nextInt();
                            if (availableCopies < 0) {
                                // validate available copies to not be negative and not exceed total copies entered
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
                            while(true){
                                System.out.print("Enter book type ( Printed, Electronic, Audio): ");
                                String bookType = scanner.nextLine();

                                if (!bookType.equalsIgnoreCase("Printed") && !bookType.equalsIgnoreCase("Electronic") && !bookType.equalsIgnoreCase("Audio") ){
                                    System.out.println("Book Type must be Printed, Electronic or Audio");
                                }else{
                                    // add new item if book Type is valid
                                    newItem = new Book(itemID, title, author, ISBN, publisher, availableCopies,
                                    totalCopies, Status.AVAILABLE,
                                    bookType);

                                    break;
                                }
                                
                            }
                            
                        }

                        // Assume function to add periodical to inventory
                        else if (itemType.equalsIgnoreCase("Periodical")) {
                            while (true){
                                // prompt user to enter periodical type
                                System.out.print("Enter periodical type ( Printed, Electronic): ");
                                String periodicalType = scanner.nextLine();
                                // validate periodical type to be electronic or printed 
                                if (!periodicalType.equalsIgnoreCase("Printed") && !periodicalType.equalsIgnoreCase("Electronic")){
                                    System.out.println("Periodical Type must be Printed or Electronic");
                                }else{
                                    // add new periodical if input is valid
                                    newItem = new Periodical(itemID, title, author, ISBN, publisher, availableCopies,
                                    totalCopies, Status.AVAILABLE,
                                    periodicalType);

                                    break;
                                }
                                
                            }
                           
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
                            // user has the option to leave specific fields unchanged, but leaving the 
                            // field blank.

                            // edit title
                            // display current value
                            System.out.println("Current Title: " + itemToEdit.getTitle());
                            // prompt user to input new title
                            System.out.print("Enter new title (leave blank and hit enter to keep unchanged): ");
                            String newTitle = scanner.nextLine();

                             // if empty, keep the same
                            if (!newTitle.isEmpty()) {
                                itemToEdit.setTitle(newTitle);
                            }

                            // edit author info
                            // display current value
                            System.out.println("Current Author: " + itemToEdit.getAuthor().getName());
                            // prompt user to enter new name
                            System.out.print("Enter new author (leave blank and hit enter to keep unchanged): ");
                            String newAuthorName = scanner.nextLine();

                            if (!newAuthorName.isEmpty()) {

                                // only prompt DOB to be changed if a new author name is entered.
                                // logically, DOB should not change unless the author does.
                                
                                // prompt user to enter new DOB
                                 // if empty, keep the same
                                System.out.print("Enter Author's date of birth (yyyy-mm-dd): ");
                                String newAuthorDOBString = scanner.nextLine();
                                Date newAuthorDOB = null;

                                while (true) {
                                    // validate author DOB entered using the method in Author class
                                    
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

                            // edit isbn
                            // display current value
                            System.out.println("Current ISBN: " + itemToEdit.getISBN());

                            while (true) {
                                // prompt user to input new ISBN
                                System.out.print("Enter new ISBN (leave blank and hit enter to keep unchanged): ");
                                String newISBN = scanner.nextLine();

                                // validate ISBN to be numbers only and 10/13 digits long.
                                 // if empty, keep the same
                            
                                if (!newISBN.isEmpty()) {
                                    if (newISBN.matches("\\d{10}|\\d{13}")) {
                                        itemToEdit.setISBN(newISBN);
                                        break;
                                    } else {
                                        System.out
                                                .println("ISBN must contain numbers only and be 10 or 13 digits long.");
                                    }
                                } else {
                                    break; // Break the loop if the input is empty
                                }
                            }

                            // edit publisher
                            // display current value
                            System.out.println("Current Publisher: " + itemToEdit.getPublisher());
                            // prompt user to enter new publisher
                            System.out.print("Enter new publisher (leave blank and hit enter to keep unchanged): ");
                            String newPublisher = scanner.nextLine();

                            // if empty, keep the same
                            if (!newPublisher.isEmpty()) {
                                itemToEdit.setPublisher(newPublisher);
                            }

                            //edit total copies
                            // display current value
                            System.out.println("Current Total Copies: " + itemToEdit.getTotalCopies());

                            while(true){
                                // prompt user to input new total copies value
                                System.out.print("Enter New Total Copies (keep blank and hit enter to keep unchanged): ");
                                String newTotalCopies = scanner.nextLine();

                                // validate total copies to be a positive number 
                                 // if empty, keep the same

                                if (!newTotalCopies.isEmpty()) {
                                    try {
                                            int newTotalCopiesInt = Integer.parseInt(newTotalCopies);
                                            if(newTotalCopiesInt > 0 ){
                                                itemToEdit.setTotalCopies(newTotalCopiesInt);
                                                break;
                                            }else{
                                                System.err.println("Total Copies Must Exceed 0.");
                                            }
                                            
                                    }catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Total copies must be a numeric value.");
                                    }
                                    
                                }else{
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
                                }else{
                                    break;
                                }
                                // NEW LOCATION (for success message)=> had to change success message back to old location.
                                // success message was being printed every time the loop was iterated
                                // through, whether the input was valid or invalid. moved it outside
                                // the loop to only display after all fields have been successfully 
                                // changed.
                               
                               
                            }

                        }
                        // OLD LOCATION => success message moved back here, explained above.
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
                    // add an author
                    System.out.println("Add an Author... \n Enter Author ID: ");
                    case 7: 
                    // edit an author
                    System.out.println("Edit an Author...\nEnter Author ID: ");
                    case 8: 
                    //delete an author 
                    System.out.println("Delete an Author...\nEnter Author ID: ");

                    case 9: 
                    // add a patron
                    String addPatronID;

                    while(true){
                        // prompt user to enter patron id
                        System.out.println("Add a Patron...\nEnter Patron ID: ");
                        addPatronID = scanner.nextLine().trim();
                        // ensure id is not empty
                        if(addPatronID.isEmpty()){
                            System.out.println("ID cannot be empty.");
                        // ensure id is not already in system
                        }else if(library.getPatronByID(addPatronID) != null){
                            System.out.println("This patron already exists.");
                        }else{
                            break;
                        }
                    }

                    String patronType;
                    while(true){
                        // validate patrontype to be "Student" or "Employee"
                        System.out.print("Enter patron type (student/employee): ");
                        patronType = scanner.nextLine().trim();
                        if(patronType.equalsIgnoreCase("Student") || patronType.equalsIgnoreCase("Employee")){
                            break;
                        }else if(patronType.isEmpty()){
                            System.out.println("Patron type cannot be empty.");
                        }else{
                            System.out.println("Invalid patron type entered. Patron type must be student or employee");
                        }
                    }

                    // prompt user to enter patron name
                    System.out.println("Enter patron name: ");
                    String patronName = scanner.nextLine();

                    // prompt user to enter patron address
                    System.out.println("Enter patron address: ");
                    String patronAddress = scanner.nextLine();

                    //prompt user to enter patron phone number
                    System.out.println("Enter patron phone number: ");
                    String patronPhoneNum = scanner.nextLine();

                    Patron addedPatron;
                    // if patron type = student, add new student
                    if(patronType.equalsIgnoreCase("Student")){
                        addedPatron = new Student(addPatronID, patronName, patronAddress, patronPhoneNum);
                    }else{
                        // if patron type = employee, add new employee
                        addedPatron = new Employee(addPatronID, patronName, patronAddress, patronPhoneNum);
                    }

                    // add patron to patronMap
                    library.addPatron(addedPatron);
                    // display sucess messsage
                    System.out.print("Patron added successfully!");

                    case 10:
                    // edit a patron
                    // prompt user to enter patron ID
                    System.out.print("Edit a Patron...\nPlease Enter Patron ID: ");
                    String editPatronID = scanner.nextLine();
                    Patron patronToEdit = library.getPatronByID(editPatronID);

                    // alert if patron cannot be found in system
                    if(patronToEdit == null){
                        System.out.println("Patron not found.");
                        break;
                    }

                    // display current patron name
                    System.out.println("Current Name: " + patronToEdit.getName());
                    // prompt user to enter new patron name
                    System.out.print("Enter new name (leave blank and hit enter to keep unchanged): ");
                    String newPatronName = scanner.nextLine();

                    // if empty, keep the same
                    if(!newPatronName.isEmpty()){
                        patronToEdit.setName(newPatronName);
                    }

                    // display current address
                    System.out.println("Current Address: " + patronToEdit.getAddress());
                    //prompt user to enter new address
                    System.out.print("Enter new address (leave blank and hit enter to keep unchanged): ");
                    String newPatronAddress = scanner.nextLine();

                    // if empty, keep the same
                    if(!newPatronAddress.isEmpty()){
                        patronToEdit.setAddress(newPatronAddress);
                    }

                    // display current phone number
                    System.out.println("Current Phone Number: " + patronToEdit.getPhoneNumber());
                    // prompt user to enter new phone number
                    System.out.print("Enter new phone number (leave blank and hit enter to keep unchanged): ");
                    String newPatronPhone = scanner.nextLine();

                    // if empty, keep the same
                    if(!newPatronPhone.isEmpty()){
                        patronToEdit.setPhoneNum(newPatronPhone);
                    }

                    // print success message
                    System.out.println("Patron Edited Sucessfully!");
                    break;

                    case 11: 
                    // delete a patron
                    System.out.println("Delete a Patron...\nEnter Patron ID: ");
                    patronID = scanner.nextLine();

                    Patron patronToDelete = library.getPatronByID(patronID);
                    if(patronToDelete == null){
                        System.out.println("Patron" + patronID + "not found. Cannot be deleted");
                    }else{
                        library.removePatron(patronToDelete);
                        System.out.println("Patron Deleted Successfully!");
                    }

                    break;
                    
                    case 12:
                        // exit menu
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 12.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                choice = 0; // Reset choice to 0 to re-enter the loop
            }
        } while (choice != 12);
        scanner.close();
    }

}
