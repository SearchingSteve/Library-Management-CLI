package main.items;

// Book class is a subclass of LibraryItem
public class Book extends LibraryItem {
    private String author;
    private String bookType;
    
    // Constructor
    public Book(String title, String ISBN, String publisher, int availableCopies, Status status, String author, String bookType) {
        super(title, ISBN, publisher, availableCopies, status);
        this.author = author;
        this.bookType = bookType;
    }
}
