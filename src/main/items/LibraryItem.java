package main.items; 
import main.authors.Author; 

// Abstract class for all library items
public abstract class LibraryItem {
    // Generic fields for all library items (books, periodicals)
    private String itemID;
    private String title;
    private String ISBN;
    private Author author;
    private String publisher;
    private int availableCopies;
    private Status status;  

    // Constructor
    public LibraryItem(String itemID, String title, Author author, String ISBN, String publisher, int availableCopies, Status status) {
        this.itemID = itemID;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.availableCopies = availableCopies;
        this.status = status;
    }

    // Might be better to put this here instead of seperate Status.java file as here it stays organized with the LibraryItem class
   // Also change the name of the enum to ItemStatus
   // Say we have a class called Status in the same package, it would be confusing to have two classes with the same name
   // If we want to add status for patrons (WHICH WE DONT HAVE TO FOR THIS ASSIGNMENT), the status from it will most likely differentiate from our ItemStatus states
   // i.e PatronStatus.CURRENT, PatronStatus.LATE, PatronStatus.SUSPENDED
    // public enum ItemStatus {
    //     // get and set status should be completed in libraryItem.java
    //     AVAILABLE,
    //     CHECKED_OUT,
    //     OVERDUE,
    // }

    // Getters and setters
    public String getItemID(){
        return itemID;
    }

    public void setItemID(String itemID){
        this.itemID = itemID;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Author getAuthor(){
        return author;
    }

    public void setAuthor(Author author){
        this.author = author;
    }

    public String getISBN(){
        return ISBN;
    }

    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public int getAvailableCopies(){
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies){
        this.availableCopies = availableCopies;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    // Abstract method for returning type of item. (will need same method naming and override in book/periodical subclasses - getItemType())
    public abstract String getItemType(); // if using same method name getItemType() in book/periodicals wiht @override
    
    //else if using different method names in book/periodicals, we can use the following:
    // public abstract String getBookType();
    // public abstract String getPeriodicalType();

    // No setItemType() method here as we don't want to change the type of an item once it's created
    

    //ADDITIONAL METHODS FOR BOOK/PERIODICAL SUBCLASSES 
}
