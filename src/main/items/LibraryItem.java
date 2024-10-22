package main.items; 

// Abstract class for all library items
public abstract class LibraryItem {
    private String title;
    private String ISBN;
    private String publisher;
    private int availableCopies;
    private Status status;  

    // Constructor
    public LibraryItem(String title, String ISBN, String publisher, int availableCopies, Status status) {
        this.title = title;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.availableCopies = availableCopies;
        this.status = status;
    }
    
}
