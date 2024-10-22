//*
// This is just one example of how a menu can look using a scanner
// This isnt complete nor does it have all required sections included
// This is just meant to give you a brief idea of how a menu could look
// Good luck and have fun!
//
//
// *//

package main.library;

import java.util.Scanner;

import main.items.Book;
import main.items.LibraryItem;
import main.items.Periodical;
import main.items.Status;

public class LibraryMenu {
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
                case 1:
                    System.out.println("Adding a new library item...");
                    // Add logic to add a library item
                    System.out.println("Enter the type of Library item?: ");
                    String itemType = scanner.nextLine();

                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String ISBN = scanner.nextLine();
                    System.out.print("Enter publisher: ");
                    String publisher = scanner.nextLine();
                    System.out.print("Enter total copies: ");
                    int totalCopies = scanner.nextInt();
                    System.out.print("Enter available copies: ");
                    int availableCopies = scanner.nextInt();
                    scanner.nextLine();
                    LibraryItem libraryItem;

                    // Assume function to add book to inventory
                    if (itemType.equalsIgnoreCase("Book")) {
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter book type (e.g. Print, Electronic, Audio): ");
                        String bookType = scanner.nextLine();
                        libraryItem = new Book(title, ISBN, publisher, availableCopies, Status.AVAILABLE,
                                author, bookType);
                    }
                    
                    // Assume function to add periodical to inventory
                    else if (itemType.equalsIgnoreCase("Periodical")) {
                        System.out.print("Enter periodical type (e.g. Print, Electronic): ");
                        String periodicalType = scanner.nextLine();
                        libraryItem = new Periodical(title, ISBN, publisher, availableCopies, Status.AVAILABLE,
                                periodicalType);
                    }
                    break;
                case 2:
                    System.out.println("Editing an existing library item...");
                    // Add logic to edit a library item
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
