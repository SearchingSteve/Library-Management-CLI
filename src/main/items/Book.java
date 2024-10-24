package main.items;

// IMPORT STATUS ENUM
import main.items.Status;

// Book class is a subclass of LibraryItem
public class Book extends LibraryItem {
    // ASSIGN ATTRIBUTES
    private String author;
    private String bookType;

    
    // CONSTRUCTOR
    public Book(String title, String ISBN, String publisher, int availableCopies, Status status, String author, String bookType) {
        super(title, ISBN, publisher, availableCopies, status);
        this.author = author;
        this.bookType = bookType;
    }

    // GETTERS AND SETTERS

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getBookType(){
        return bookType;
    }

    public void setBookType(String bookType){
        this.bookType = bookType;
    }

    // DISPLAY BOOK INFORMATION
    public String toString(){
        return "Book Information{ " + 
        "title= " + getTitle() +
         ", ISBN= " + getISBN() + 
         ", publisher= " + getPublisher() + 
         ", available copies= " + getAvailableCopies() + 
         ", status= " + getStatus() + 
         ", author= " + getAuthor() + 
         ", book type= " + getBookType() + 
         "}";
    }

}
