package main.library;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import main.items.Book;
import main.items.LibraryItem;
import main.items.Periodical;
import main.items.Status;
import main.authors.Author;
import main.patrons.Patron;

// Main class for the library
public class Library {
    private static HashMap<String, LibraryItem> itemMap = new HashMap<>(); // String = itemID
    private static HashMap<String, Patron> patronMap = new HashMap<>(); // String = patronID


    public void initializeMockItems() {
        addLibraryItem(new Book("B001", "Effective Java", new Author("Joshua Bloch", parseDate("1970-01-01")), "1234567890", "Addison-Wesley", 10, Status.AVAILABLE, "Print"));
        addLibraryItem(new Book("B002", "Clean Code", new Author("Robert C. Martin", parseDate("1970-01-01")), "1234567891", "Prentice Hall", 15, Status.AVAILABLE, "Electronic"));
        addLibraryItem(new Book("B003", "Design Patterns", new Author("Erich Gamma", parseDate("1965-03-10")), "1234567892", "Addison-Wesley", 8, Status.AVAILABLE, "Audio"));
        addLibraryItem(new Periodical("P001", "The Economist", new Author("John Micklethwait", parseDate("1987-05-15")), "1234567893", "The Economist Newspaper", 30, Status.AVAILABLE, "Print"));
        addLibraryItem(new Periodical("P002", "National Geographic", new Author("Susan Goldberg", parseDate("1985-02-17")), "1234567894", "National Geographic Society", 25, Status.AVAILABLE, "Electronic"));
        addLibraryItem(new Book("B004", "The Pragmatic Programmer", new Author("Andrew Hunt", parseDate("1969-04-01")), "1234567895", "Addison-Wesley Professional", 12, Status.AVAILABLE, "Print"));
        addLibraryItem(new Book("B005", "Code Complete", new Author("Steve McConnell", parseDate("1964-07-21")), "1234567896", "Microsoft Press", 18, Status.AVAILABLE, "Electronic"));
        addLibraryItem(new Periodical("P003", "Science", new Author("Jeremy Berg", parseDate("1950-03-15")), "1234567897", "American Association for the Advancement of Science", 20, Status.AVAILABLE, "Print"));
        addLibraryItem(new Book("B006", "Refactoring", new Author("Martin Fowler", parseDate("1963-12-18")), "1234567898", "Addison-Wesley", 5, Status.AVAILABLE, "Audio"));
        addLibraryItem(new Book("B007", "Artificial Intelligence", new Author("Stuart Russell", parseDate("1962-05-20")), "1234567899", "Pearson", 10, Status.AVAILABLE, "Print"));
    }
    
    
    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date(); 
        }
    }
    

    public List<LibraryItem> getAllItems(){
        return new ArrayList<>(itemMap.values());
    }

    public void displayAllItems(){
        for (LibraryItem item : itemMap.values()){
            System.out.println(item);
        }
    }

    public List<Patron> getAllPatrons(){
        return new ArrayList<>(patronMap.values());
    }

    public LibraryItem getItemByID(String itemID){
        return itemMap.get(itemID);
    }

    public Patron getPatronByID(String patronID){
        return patronMap.get(patronID);
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
    public void addLibraryItem(LibraryItem item){
        if (itemMap.containsKey(item.getItemID())){
            System.out.println("Item already exists in the library");
        } else {
            itemMap.put(item.getItemID(), item);
        }
    }

    // Remove a library item
    public void removeLibraryItem(LibraryItem item){
        if (!itemMap.containsKey(item.getItemID())){
            System.err.println("Item does not exist in the library");
        } else {
            itemMap.remove(item.getItemID());
        }
    }

    // **SARA**
    // Edit a library item
    public void editLibraryItem(LibraryItem item){
        // Add code to edit a library item
        // Can do something simple like in case 1. Build all the item parameters in LibraryMenu,
        // then call this method to edit the item in the map.
    }

    // Add a patron to the library
    public void addPatron(Patron patron){
        if (patronMap.containsKey(patron.getId())){
            System.out.println("Patron already exists in the library");
        } else {
            patronMap.put(patron.getId(), patron);
        }
    }

    // Remove a patron from the library
    public void removePatron(Patron patron){
        if (!patronMap.containsKey(patron.getId())){
            System.err.println("Patron does not exist in the library");
        } else {  
            // Remove patron from the library referece from patron class. - Might not need 
            patron.deletePatron();
            patronMap.remove(patron.getId());
        }

 
    }


    // **NASSER**
    // PLACEHOLDER for borrowing a library item
    public void borrowLibraryItem(LibraryItem libraryItem) {
        // Add logic to check if the library item is available based on quantity
        
        // if LibraryItem.getAvailableCopies() > 0 and item not null, then decrement available copies by 1
        // else, item is not available
    }

    // PLACEHOLDER for returning a library item
    public void returnLibraryItem(LibraryItem libraryItem) {
        // 
    }


    



    
}
