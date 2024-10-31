package main.items;

import main.authors.Author;

// Abstract class for all library items
public abstract class LibraryItem {
    // Generic fields for all library items (books, periodicals)
    private String itemID;
    private String title;
    private Author author;
    private String ISBN;
    private String publisher;
    private int availableCopies;
    private int totalCopies;

    // Enum for status of library items
    private Status status;

    // Constructor
    public LibraryItem(String itemID, String title, Author author, String ISBN, String publisher, int availableCopies,
            int totalCopies, Status status) {
        this.itemID = itemID;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
        this.status = status;
    }

    // Getters and setters
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Abstract method for returning type of item. (will need same method naming and
    // override in book/periodical subclasses - getItemType())
    public abstract String getItemType(); // if using same method name getItemType() in book/periodicals wiht @override

    // ADDITIONAL METHODS FOR BOOK/PERIODICAL SUBCLASSES
}
