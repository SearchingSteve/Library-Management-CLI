package main.items;
import main.authors.Author;


// Book class is a subclass of LibraryItem
public class Book extends LibraryItem {
    // ASSIGN ATTRIBUTES
    private String bookType;

    
    // CONSTRUCTOR
    public Book(String itemID, String title, Author author, String ISBN, String publisher, int availableCopies, Status status, String bookType) {
        super(itemID, title, author, ISBN, publisher, availableCopies, status);
        this.bookType = bookType;
    }

    // GETTERS AND SETTERS
    public Author getAuthor(){
        return super.getAuthor();
    }

    public void setAuthor(Author author){
        super.setAuthor(author);
    }

    public String getBookType(){
        return bookType;
    }

    public void setBookType(String bookType){
        this.bookType = bookType;
    }

    @Override
    public String getItemType() {
        return "Book";
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
