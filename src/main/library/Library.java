package main.library;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

import main.items.Book;
import main.items.LibraryItem;
import main.items.Periodical;
import main.items.Status;
import main.authors.Author;
import main.patrons.Employee;
import main.patrons.Patron;
import main.patrons.Student;

/**
 * The Library class models a library system, handling library items, authors,
 * and patrons.
 * It includes methods to add, edit, and remove items and patrons, and to lend
 * and return items.
 */
public class Library {
    // create maps for LibraryItem and Patron so that adding, deleting or editing a
    // field/item/patron
    // is easier - all items can be located through a match (id for example) rather
    // than a loop being
    // iterated through (alternative option) which would take longer and require
    // validation.

    /**
     * Maps item IDs to library items, allowing for efficient retrieval and
     * management of items.
     */
    private static HashMap<String, LibraryItem> itemMap = new HashMap<>(); // String = itemID\
    /**
     * Maps patron IDs to patrons, allowing for efficient retrieval and management
     * of patrons.
     */
    private static HashMap<String, Patron> patronMap = new HashMap<>(); // String = patronID

    /**
     * Initializes mock data, including library items and patrons, for testing
     * purposes.
     */
    public void initializeMockData() {
        initializeMockItems();
        initializeMockPatrons();
    }

    /**
     * Initializes mock authors for the library system.
     */
    public void initializeMockAuthors() {
        Author author1 = new Author("Joshua Bloch", parseDate("1970-01-01"));
        Author author2 = new Author("Robert C. Martin", parseDate("1970-01-01"));
        Author author3 = new Author("Erich Gamma", parseDate("1965-03-10"));
        Author author4 = new Author("Andrew Hunt", parseDate("1969-04-01"));
        Author author5 = new Author("Steve McConnell", parseDate("1964-07-21"));

        Author.authorList.add(author1);
        Author.authorList.add(author2);
        Author.authorList.add(author3);
        Author.authorList.add(author4);
        Author.authorList.add(author5);
    }

    /**
     * Initializes mock items in the library, linking each item to an author.
     */
    public void initializeMockItems() {
        initializeMockAuthors(); // Ensure authors are initialized first
        Author author1 = Author.authorList.get(0); // Joshua Bloch
        Author author2 = Author.authorList.get(1); // Robert C. Martin
        Author author3 = Author.authorList.get(2); // Erich Gamma
        Author author4 = Author.authorList.get(3); // Andrew Hunt
        Author author5 = Author.authorList.get(4); // Steve McConnell

        // Add 7 books to the library to the library itemMap
        addLibraryItem(new Book("001", "Effective Java", author1, "1234567890", "Addison-Wesley", 10, 11,
                Status.AVAILABLE, "Print"));
        author1.getAuthoredItems().add(new Book("001", "Effective Java", author1, "1234567890", "Addison-Wesley", 10,
                11, Status.AVAILABLE, "Print"));

        addLibraryItem(new Book("002", "Clean Code", author2, "1234567891", "Prentice Hall", 15, 20, Status.AVAILABLE,
                "Electronic"));
        author2.getAuthoredItems().add(new Book("002", "Clean Code", author2, "1234567891", "Prentice Hall", 15, 20,
                Status.AVAILABLE, "Electronic"));

        addLibraryItem(new Book("003", "Design Patterns", author3, "1234567892", "Addison-Wesley", 8, 8,
                Status.AVAILABLE, "Audio"));
        author3.getAuthoredItems().add(new Book("003", "Design Patterns", author3, "1234567892", "Addison-Wesley", 8, 8,
                Status.AVAILABLE, "Audio"));

        addLibraryItem(new Book("004", "The Pragmatic Programmer", author4, "1234567895", "Addison-Wesley Professional",
                12, 15, Status.AVAILABLE, "Print"));
        author4.getAuthoredItems().add(new Book("004", "The Pragmatic Programmer", author4, "1234567895",
                "Addison-Wesley Professional", 12, 15, Status.AVAILABLE, "Print"));

        addLibraryItem(new Book("005", "Code Complete", author5, "1234567896", "Microsoft Press", 18, 19,
                Status.AVAILABLE, "Electronic"));
        author5.getAuthoredItems().add(new Book("005", "Code Complete", author5, "1234567896", "Microsoft Press", 18,
                19, Status.AVAILABLE, "Electronic"));

        addLibraryItem(new Book("006", "Refactoring", author1, "1234567898", "Addison-Wesley", 5, 6, Status.AVAILABLE,
                "Audio"));
        author1.getAuthoredItems().add(new Book("006", "Refactoring", author1, "1234567898", "Addison-Wesley", 5, 6,
                Status.AVAILABLE, "Audio"));

        addLibraryItem(new Book("007", "Artificial Intelligence", author2, "1234567899", "Pearson", 10, 13,
                Status.AVAILABLE, "Print"));
        author2.getAuthoredItems().add(new Book("007", "Artificial Intelligence", author2, "1234567899", "Pearson", 10,
                13, Status.AVAILABLE, "Print"));

        // Add 3 periodicals to the library itemMap
        addLibraryItem(new Periodical("008", "The Economist", author3, "1234567893", "The Economist Newspaper", 30, 30,
                Status.AVAILABLE, "Print"));
        author3.getAuthoredItems().add(new Periodical("008", "The Economist", author3, "1234567893",
                "The Economist Newspaper", 30, 30, Status.AVAILABLE, "Print"));

        addLibraryItem(new Periodical("009", "National Geographic", author4, "1234567894",
                "National Geographic Society", 25, 29, Status.AVAILABLE, "Electronic"));
        author4.getAuthoredItems().add(new Periodical("009", "National Geographic", author4, "1234567894",
                "National Geographic Society", 25, 29, Status.AVAILABLE, "Electronic"));

        addLibraryItem(new Periodical("010", "Science", author5, "1234567897",
                "American Association for the Advancement of Science", 20, 23, Status.AVAILABLE, "Print"));
        author5.getAuthoredItems().add(new Periodical("010", "Science", author5, "1234567897",
                "American Association for the Advancement of Science", 20, 23, Status.AVAILABLE, "Print"));
    }

    /**
     * Initializes mock patrons in the library system.
     */
    public void initializeMockPatrons() {
        addPatron(new Student("001", "John Doe", "1234 Elm St, Springfield, IL", "123-555-1234"));
        addPatron(new Employee("002", "Jane Doe", "5678 Oak St, Springfield, IL", "123-555-5678"));
        addPatron(new Student("003", "Alice Smith", "9101 Pine St, Springfield, IL", "123-555-9101"));
        addPatron(new Employee("004", "Bob Smith", "1122 Birch St, Springfield, IL", "456-555-1122"));
        addPatron(new Student("005", "Charlie Brown", "3344 Maple St, Springfield, IL", "456-555-3344"));
        addPatron(new Employee("006", "Lucy Brown", "5566 Cedar St, Springfield, IL", "456-555-5566"));
        addPatron(new Student("007", "Snoopy Dog", "7788 Pine St, Springfield, IL", "456-555-7788"));
        addPatron(new Employee("008", "Woodstock Bird", "9900 Elm St, Springfield, IL", "456-555-9900"));
        addPatron(new Student("009", "Linus Van Pelt", "1122 Oak St, Springfield, IL", "789-555-1122"));
        addPatron(new Employee("010", "Sally Brown", "3344 Birch St, Springfield, IL", "789-555-3344"));
    }

    /**
     * Parses a date from a string in the format "yyyy-MM-dd".
     * 
     * @param date The date string to parse.
     * @return The parsed Date object, or today's date if parsing fails.
     */
    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * Retrieves all library items in the system.
     * 
     * @return A list of all library items.
     */
    public List<LibraryItem> getAllItems() {
        return new ArrayList<>(itemMap.values());
    }

    /**
     * Displays library items based on specified item IDs. If no IDs are specified,
     * displays all items.
     * 
     * @param itemIDs The IDs of items to display.
     */
    public void displayItems(String... itemIDs) {
        if (itemIDs.length == 0) {
            // No item IDs provided, display all items
            itemMap.values().forEach(item -> System.out.println(item));
        } else {
            // Display items for the provided IDs
            for (String id : itemIDs) {
                LibraryItem item = itemMap.get(id);
                if (item != null) {
                    System.out.println(item);
                } else {
                    System.out.println("Item with ID: " + id + " not found.");
                }
            }
        }
    }

    /**
     * Retrieves all patrons in the system.
     * 
     * @return A list of all patrons.
     */
    public List<Patron> getAllPatrons() {
        return new ArrayList<>(patronMap.values());
    }

    /**
     * Displays patrons based on specified patron IDs. If no IDs are specified,
     * displays all patrons.
     * 
     * @param patronIDs The IDs of patrons to display.
     */
    public void displayPatrons(String... patronIDs) {
        if (patronIDs.length == 0) {
            // No patron IDs provided, display all patrons
            patronMap.values().forEach(patron -> System.out.println(patron));
        } else {
            // Display patrons for the provided IDs
            for (String id : patronIDs) {
                Patron patron = patronMap.get(id);
                if (patron != null) {
                    System.out.println(patron);
                } else {
                    System.out.println("Patron with ID: " + id + " not found.");
                }
            }
        }
    }

    /**
     * Retrieves a library item by its ID.
     * 
     * @param itemID The ID of the item to retrieve.
     * @return The library item with the specified ID, or null if not found.
     */
    public LibraryItem getItemByID(String itemID) {
        if (itemMap.containsKey(itemID)) {
            return itemMap.get(itemID);
        } else {
            // System.out.println("Item with ID " + itemID + " not found in the library.");
            return null;
        }
    }

    /**
     * Retrieves a patron by their ID.
     * 
     * @param patronID The ID of the patron to retrieve.
     * @return The patron with the specified ID, or null if not found.
     */
    public Patron getPatronByID(String patronID) {
        if (patronMap.containsKey(patronID)) {
            return patronMap.get(patronID);
        } else {
            System.out.println("Patron with ID " + patronID + " not found in the library.");
            return null;
        }
    }

    /**
     * Searches for library items by title.
     * 
     * @param title The title of the item to search for.
     * @return A list of items with the specified title.
     */
    public List<LibraryItem> searchByTitle(String title) {
        return itemMap.values().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    /**
     * Searches for library items by author.
     * 
     * @param author The author of the items to search for.
     * @return A list of items authored by the specified author.
     */
    public List<LibraryItem> searchByAuthor(Author author) {
        return itemMap.values().stream()
                .filter(item -> item.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    /**
     * Searches for library items by ISBN.
     * 
     * @param ISBN The ISBN of the item to search for.
     * @return A list of items with the specified ISBN.
     */
    public List<LibraryItem> searchByISBN(String ISBN) {
        return itemMap.values().stream()
                .filter(item -> item.getISBN().equals(ISBN))
                .collect(Collectors.toList());
    }

    /**
     * Adds a library item to the system and associates it with the appropriate
     * author.
     * 
     * @param item The library item to add.
     */
    public void addLibraryItem(LibraryItem item) {
        if (itemMap.containsKey(item.getItemID())) {
            System.out.println("Item already exists in the library");
        } else {
            itemMap.put(item.getItemID(), item);
            Author author = item.getAuthor();
            // Add the author to the author list if not already present
            if (Author.authorList.contains(author)) {
                Author.authorList.add(author);
            }
            // Add the item to the author's authored items list
            author.getAuthoredItems().add(item);
            System.out.println("Item " + item.getTitle() + " added successfully for author " + author.getName() + ".");
        }
    }

    /**
     * Removes a library item from the system, ensuring that borrowed items are
     * returned first.
     * 
     * @param itemID The ID of the item to remove.
     */
    public void removeLibraryItem(String itemID) {
        if (itemMap.containsKey(itemID)) {
            patronMap.values().forEach(patron -> {
                // ensures item is not borrowed before its deleted, removed item
                if (patron.checkQuantityBorrowed(itemID) > 0) {
                    patron.returnBorrowedItem(itemID, patron.checkQuantityBorrowed(itemID));
                }
            });
            LibraryItem bookToBeDeleted = itemMap.get(itemID);
            String titleOfBookToBeDeleted = bookToBeDeleted.getTitle();

            // Remove the author from the author list if the author has no more authored
            // items
            if (Author.authorList.contains(bookToBeDeleted.getAuthor())) {
                Author.authorList.remove(bookToBeDeleted.getAuthor());
            }
            // print success message
            System.out.println(
                    "Item with ID " + itemID + " (Title: " + titleOfBookToBeDeleted + ")" + " deleted successfully.");
            // Remove the item from the library itemMap and set it to null
            itemMap.remove(itemID);
            bookToBeDeleted = null;
        } else {
            System.out.println("No item found with ID: " + itemID + ". No item deleted.");
        }
    }

    /**
     * Adds an author to the system.
     * 
     * @param author The author to add.
     */
    public void addAuthor(Author author) {
        if (Author.authorList.contains(author)) {
            System.out.println("Author already exists in the library");
        } else {
            Author.authorList.add(author);
        }
    }

    /**
     * Removes all items associated with an author from the system.
     * 
     * @param author The author whose items should be removed.
     */
    public void removeAuthorItems(Author author) {
        List<LibraryItem> itemsToRemove = searchByAuthor(author);
        for (LibraryItem item : itemsToRemove) {
            removeLibraryItem(item.getItemID());
        }
    }

    /**
     * Adds a patron to the library system.
     * 
     * @param patron The patron to add.
     */
    public void addPatron(Patron patron) {
        if (patronMap.containsKey(patron.getId())) {
            System.out.println("Patron already exists in the library");
        } else {
            patronMap.put(patron.getId(), patron);
        }
    }

    /**
     * Removes a patron from the library system, returning all borrowed items first.
     * 
     * @param patron The patron to remove.
     */
    public void removePatron(Patron patron) {
        if (!patronMap.containsKey(patron.getId())) {
            System.err.println("Patron does not exist in the library");
        } else {
            // Return all borrowed items to library before removing the patron
            patron.getBorrowedLibraryItems().forEach((itemID, quantity) -> {
                LibraryItem item = itemMap.get(itemID);
                item.setAvailableCopies(item.getAvailableCopies() + quantity);
            });
            patronMap.remove(patron.getId());
        }
    }

    /**
     * Lends a specified quantity of a library item to a patron if available.
     * 
     * @param libraryItem The item to lend.
     * @param patron      The patron borrowing the item.
     * @param quantity    The quantity of the item to lend.
     */
    public void lendLibraryItem(LibraryItem libraryItem, Patron patron, int quantity) {

        // Check if library item exists in the library
        if (libraryItem == null) {
            System.out.println("Item does not exist in the library");
            return;
        }

        // Check if patron exists in the library
        if (patron == null) {
            System.out.println("Patron does not exist in the library");
            return;
        }

        // Check if library item exists in the library in requested quantity
        int availableCopies = libraryItem.getAvailableCopies();
        if (availableCopies > 0 && libraryItem != null) {
            libraryItem.setAvailableCopies(libraryItem.getAvailableCopies() - quantity);
            patron.addBorrowedItem(libraryItem.getItemID(), quantity); // Add the borrowed item to the patron
            System.out.println(
                    "Lent " + quantity + " copies of " + libraryItem.getTitle() + " to " + patron.getName() + ".");
        }
    }

    /**
     * Returns a specified quantity of a borrowed library item from a patron back to
     * the library.
     * Validates the item and patron exists and checks that the quantity returned
     * does not exceed the quantity borrowed.
     *
     * @param libraryItem The library item being returned.
     * @param patron      The patron returning the item.
     * @param quantity    The quantity of the item being returned.
     */
    public void returnLibraryItem(LibraryItem libraryItem, Patron patron, int quantity) {
        // Check if library item exists in the library
        if (libraryItem == null) {
            System.out.println("Item does not exist in the library");
            return;
        }

        // Check if patron exists in the library
        if (patron == null) {
            System.out.println("Patron does not exist in the library");
            return;
        }

        // Check if quantity to return exceeds the quantity borrowed and throws an error
        if (patron.checkQuantityBorrowed(libraryItem.getItemID()) < quantity) {
            System.out.println("Quantity to return exceeds the quantity borrowed.");
            return;
        } else if (quantity < 0) {
            System.out.println("Quantity to return cannot be negative.");
            return;
        } else if (quantity == 0) {
            System.out.println("Quantity to return cannot be zero.");
            return;
        } else {
            // Return the item to the library and remove it from the patron's borrowed items
            libraryItem.setAvailableCopies(libraryItem.getAvailableCopies() + quantity);
            patron.returnBorrowedItem(libraryItem.getItemID(), quantity);
            System.out.println("Returned " + quantity + " copies of " + libraryItem.getTitle() + ".");
        }
    }
}
