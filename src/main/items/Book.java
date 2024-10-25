package main.items;

// IMPORT STATUS ENUM
import main.items.Status;

// Book class is a subclass of LibraryItem
public class Book extends LibraryItem {
    // ASSIGN ATTRIBUTES
    private String bookType;

    
    // CONSTRUCTOR
    public Book(String title, String ISBN, Author author, String publisher, int availableCopies, Status status, String bookType) {
        super(title, ISBN, author, publisher, availableCopies, status);
        this.bookType = bookType;
    }

    // GETTERS AND SETTERS

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
         ", author= " + getAuthor().getName() + 
         ", publisher= " + getPublisher() + 
         ", available copies= " + getAvailableCopies() + 
         ", book type= " + getBookType() + 
         "}";
    }

}
