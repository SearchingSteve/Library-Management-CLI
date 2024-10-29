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

// Main class for the library
public class Library {
    private static HashMap<String, LibraryItem> itemMap = new HashMap<>(); // String = itemID
    private static HashMap<String, Patron> patronMap = new HashMap<>(); // String = patronID

    public void initializeMockItems() {
        // Adding books first
        addLibraryItem(new Book("001", "Effective Java", new Author("Joshua Bloch", parseDate("1970-01-01")),
                "1234567890", "Addison-Wesley", 10, 11, Status.AVAILABLE, "Print"));
        addLibraryItem(new Book("002", "Clean Code", new Author("Robert C. Martin", parseDate("1970-01-01")),
                "1234567891", "Prentice Hall", 15, 20, Status.AVAILABLE, "Electronic"));
        addLibraryItem(new Book("003", "Design Patterns", new Author("Erich Gamma", parseDate("1965-03-10")),
                "1234567892", "Addison-Wesley", 8, 8, Status.AVAILABLE, "Audio"));
        addLibraryItem(new Book("004", "The Pragmatic Programmer", new Author("Andrew Hunt", parseDate("1969-04-01")),
                "1234567895", "Addison-Wesley Professional", 12, 15, Status.AVAILABLE, "Print"));
        addLibraryItem(new Book("005", "Code Complete", new Author("Steve McConnell", parseDate("1964-07-21")),
                "1234567896", "Microsoft Press", 18, 19, Status.AVAILABLE, "Electronic"));
        addLibraryItem(new Book("006", "Refactoring", new Author("Martin Fowler", parseDate("1963-12-18")),
                "1234567898", "Addison-Wesley", 5, 6, Status.AVAILABLE, "Audio"));
        addLibraryItem(new Book("007", "Artificial Intelligence", new Author("Stuart Russell", parseDate("1962-05-20")),
                "1234567899", "Pearson", 10, 13, Status.AVAILABLE, "Print"));

        // Adding periodicals second
        addLibraryItem(new Periodical("001", "The Economist", new Author("John Micklethwait", parseDate("1987-05-15")),
                "1234567893", "The Economist Newspaper", 30, 30, Status.AVAILABLE, "Print"));
        addLibraryItem(
                new Periodical("002", "National Geographic", new Author("Susan Goldberg", parseDate("1985-02-17")),
                        "1234567894", "National Geographic Society", 25, 29, Status.AVAILABLE, "Electronic"));
        addLibraryItem(new Periodical("003", "Science", new Author("Jeremy Berg", parseDate("1950-03-15")),
                "1234567897", "American Association for the Advancement of Science", 20, 23, Status.AVAILABLE,
                "Print"));
    }

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

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public List<LibraryItem> getAllItems() {
        return new ArrayList<>(itemMap.values());
    }

    public void displayAllItems() {
        for (LibraryItem item : itemMap.values()) {
            System.out.println(item);
        }
    }

    public List<Patron> getAllPatrons() {
        return new ArrayList<>(patronMap.values());
    }

    public void displayAllPatrons() {
        for (Patron patron : patronMap.values()) {
            System.out.println(patron);
        }
    }

    public LibraryItem getItemByID(String itemID) {
        if (itemMap.containsKey(itemID)) {
            return itemMap.get(itemID);
        } else {
            // System.out.println("Item with ID " + itemID + " not found in the library.");
            return null;
        }
    }

    public Patron getPatronByID(String patronID) {
        if (patronMap.containsKey(patronID)) {
            return patronMap.get(patronID);
        } else {
            System.out.println("Patron with ID " + patronID + " not found in the library.");
            return null;
        }
    }

    // Search for library items by title, author, or ISBN
    public List<LibraryItem> searchByTitle(String title) {
        return itemMap.values().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<LibraryItem> searchByAuthor(Author author) {
        return itemMap.values().stream()
                .filter(item -> item.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<LibraryItem> searchByISBN(String ISBN) {
        return itemMap.values().stream()
                .filter(item -> item.getISBN().equals(ISBN))
                .collect(Collectors.toList());
    }

    // Add a library item
    public void addLibraryItem(LibraryItem item) {
        if (itemMap.containsKey(item.getItemID())) {
            System.out.println("Item already exists in the library");
        } else {
            itemMap.put(item.getItemID(), item);
        }
    }

    // Remove a library item
    // FIX - Need to check if the item is borrowed by any patron before deleting, if
    // so opt to delete from the patrons borrowed items list
    public void removeLibraryItem(String itemID) {
        if (itemMap.containsKey(itemID)) {
            patronMap.values().forEach(patron -> {
                if (patron.checkQuantityBorrowed(itemID) > 0) {
                    System.out.println(
                            "Item with ID " + itemID + " is borrowed by " + patron.getName() + ". Cannot delete.");
                    return;
                }
            });
            // NEED GARBAGE COLLECTION? - set all library items refenrences to null?
            LibraryItem bookToBeDeleted = itemMap.get(itemID);
            String titleOfBookToBeDeleted = bookToBeDeleted.getTitle();
            System.out
                    .println("Item with ID " + itemID + " (Title: " + titleOfBookToBeDeleted + ")"
                            + " deleted successfully.");
            itemMap.remove(itemID);
        } else {
            System.out.println("No item found with ID: " + itemID + ". No item deleted.");
        }
    }

    // Add a patron to the library
    public void addPatron(Patron patron) {
        if (patronMap.containsKey(patron.getId())) {
            System.out.println("Patron already exists in the library");
        } else {
            patronMap.put(patron.getId(), patron);
        }
    }

    // Remove a patron from the library
    public void removePatron(Patron patron) {
        if (!patronMap.containsKey(patron.getId())) {
            System.err.println("Patron does not exist in the library");
        } else {
            // Remove patron from the library referece from patron class. - Might not need
            patron.deletePatron();
            patronMap.remove(patron.getId());
        }

    }

    // Lend a library item to a patron if the item is available in requested
    // quantity and the patron exists
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
            patron.addBorrowedItems(libraryItem.getItemID(), quantity); // Add the borrowed item to the patron
            System.out.println(
                    "Lent " + quantity + " copies of " + libraryItem.getTitle() + " to " + patron.getName() + ".");
        }

    }

    // Return a library item to the library
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
            patron.returnBorrowedItems(libraryItem.getItemID(), quantity);
            System.out.println("Returned " + quantity + " copies of " + libraryItem.getTitle() + ".");

        }

    }

}
