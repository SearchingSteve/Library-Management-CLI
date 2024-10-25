package main.items;

// IMPORT STATUS ENUM

import main.authors.Author;

// Book class is a subclass of LibraryItem
public class Book extends LibraryItem {
    // ASSIGN ATTRIBUTES
    private String bookType;

    
    // CONSTRUCTOR
    public Book(int itemID, String title, String ISBN, Author author, String publisher, int availableCopies, Status status, String bookType) {
        super(itemID, title, author, ISBN, publisher, availableCopies, status);
        this.bookType = bookType;
    }

    // GETTERS AND SETTERS

    public String getBookType(){
        return bookType;
    }

    public void setBookType(String bookType){
        this.bookType = bookType;
    }

    @Override
    public String getType(){
        return "book";
    }

    // DISPLAY BOOK INFORMATION
    @Override
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
