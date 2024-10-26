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
import java.util.ArrayList;
import java.util.List;

import main.authors.Author;
import main.items.Book;
import main.items.LibraryItem;
import main.items.Periodical;
import main.items.Status;



public class LibraryMenu {

    // create a list of library items to be used in menu cases 
    private static List<LibraryItem> libraryItems = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome to the Library Management System");
            // there are more options needed for the project.
            System.out.println("1. Add Library Item");
            System.out.println("2. Edit Library Item");
            System.out.println("3. Delete Library Item");
            System.out.println("4. Borrow Library Item");
            System.out.println("5. Return Library Item");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            choice = scanner.nextInt();

            switch (choice) {
                // Add a library item
                case 1:
                    System.out.println("Adding a new library item...");
                    System.out.println("Enter the type of Library item?: ");
                    String itemType = scanner.nextLine();
                    System.out.print("Enter item ID: ");
                    String itemID = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter author name: ");
                    String authorName = scanner.nextLine();
                    System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
                    String authorDOBString = scanner.nextLine();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date authorDOB = null;
                    try {
                        authorDOB = dateFormat.parse(authorDOBString);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter a date in the format YYYY-MM-DD.");
                        break;
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
                            System.out.println("Available copies cannot be negative. Please enter a valid number.");
                        } else if (availableCopies > totalCopies) {
                            System.out.println("Available copies cannot exceed total copies. Please enter a valid number.");
                        }
                    } while (availableCopies < 0 || availableCopies > totalCopies);
                    
                    scanner.nextLine();
                    LibraryItem libraryItem;

                    // Assume function to add book to inventory
                    if (itemType.equalsIgnoreCase("Book")) {
                        System.out.print("Enter book type (e.g. Print, Electronic, Audio): ");
                        String bookType = scanner.nextLine();
                        libraryItem = new Book(itemID, title, author, ISBN, publisher, availableCopies, Status.AVAILABLE,
                                bookType);
                    }
                    
                    // Assume function to add periodical to inventory
                    else if (itemType.equalsIgnoreCase("Periodical")) {
                        System.out.print("Enter periodical type (e.g. Print, Electronic): ");
                        String periodicalType = scanner.nextLine();
                        libraryItem = new Periodical(itemID, title, author, ISBN, publisher, availableCopies, Status.AVAILABLE,
                                periodicalType);
                    }

                    // Append to library item map
                    
                    break;

                case 2:
                    System.out.println("Editing an existing library item...");
                    // Add logic to edit a library item
                    System.out.println("Please enter item ID: ");
                    String editItemID = scanner.nextLine();

                    LibraryItem itemToEdit = null;
                    for(LibraryItem item : libraryItems){
                        if (item.getItemID().equals(editItemID)){
                            itemToEdit = item;
                            break;
                        }
                    }
                    if ( itemToEdit == null){
                        System.out.println("Library item not found in system. Please retry.");
                        break;
                    }

                    // display all current details to user including prompts to edit information.

                    //edit title

                    System.out.println("Enter new title (leave blank and hit enter to keep unchanged.): ");
                    String newTitle = scanner.nextLine();
                
                    if(!newTitle.isEmpty()){
                        itemToEdit.setTitle(newTitle);
                    }

                    // edit author info
                    System.out.println("Current Author: " + itemToEdit.getAuthor().getName());
                    System.out.println("Enter new author (leave blank and hit enter to keep unchanged.): ");
                    String newAuthorName = scanner.nextLine();
                    if(!newAuthorName.isEmpty()){
                        System.out.println("Enter Author's date of birth (yyyy-mm-dd): ");
                        String newAuthorDOBString = scanner.nextLine();
                    

                        Date newAuthorDOB;
                        try{
                            SimpleDateFormat Case2DateFormat = new SimpleDateFormat("yyyy-mm-dd");
                            Case2DateFormat.setLenient(false);
                            newAuthorDOB = Case2DateFormat.parse(newAuthorDOBString);

                            itemToEdit.setAuthor(new Author(newAuthorName, newAuthorDOB));
                        }catch(ParseException e){
                            System.out.println("Please enter date in the format yyyy-mm-dd");
                            break;
                        }
                    }

                    // edit isbn
                    System.out.println("Enter new ISBN (leave blank and hit enter to keep unchanged.): ");
                    String newISBN = scanner.nextLine();
                    
                    if(!newISBN.isEmpty()){
                        itemToEdit.setISBN(newISBN);
                    }

                    // edit publisher
                    System.out.println("Enter new publisher (leave blank and hit enter to keep unchanged.): ");
                    String newPublisher = scanner.nextLine();

                    if(!newPublisher.isEmpty()){
                        itemToEdit.setPublisher(newPublisher);
                    }


                    // edit available copies
                    System.out.println("Enter new availale copies (leave blank and hit enter to keep unchanged.): ");
                    String newAvailableCopies = scanner.nextLine();

                    if(!newAvailableCopies.isEmpty()){
                        int newAvailableCopiesInt = Integer.parseInt(newAvailableCopies);
                        itemToEdit.setAvailableCopies(newAvailableCopiesInt);
                    }
                    

                    // print success message
                    System.out.println("Item Edited Successfully!");
                    break;

                case 3:
                    System.out.println("Deleting a library item...");
                    // Add logic to delete a library item
                    break;
                case 4:
                    System.out.println("Borrowing a library item...");
                    // Add logic to borrow a library item
                    break;
                case 5:
                    System.out.println("Returning a library item...");
                    // Add logic to return a library item
                    break;
                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        } while (choice != 6);

        scanner.close();
    }
}

