package main.items;

import main.authors.Author;
/**
 * Abstract base class representing a generic library item, such as a book or
 * periodical. Contains common attributes like title, author, publisher, and
 * copies available.
 */
public abstract class LibraryItem {
    /** Unique identifier for the library item. */
    private String itemID;
    /** Title of the library item. */
    private String title;
    /** Author associated with the library item. */
    private Author author;
      /** ISBN of the library item, specific to books. */
    private String ISBN;
    /** Publisher of the library item. */
    private String publisher;
     /** Number of copies currently available for loan. */
    private int availableCopies;
     /** Total number of copies of the item in the library. */
    private int totalCopies;

    
    /**
     * Constructs a new LibraryItem with the specified attributes.
     * 
     * @param itemID          unique identifier for the library item
     * @param title           title of the library item
     * @param author          author associated with the library item
     * @param ISBN            ISBN of the library item
     * @param publisher       publisher of the library item
     * @param availableCopies number of available copies of the item
     * @param totalCopies     total number of copies of the item in the library
     * @param status          current status of the library item
     */
    public LibraryItem(String itemID, String title, Author author, String ISBN, String publisher, int availableCopies,
            int totalCopies) {
        this.itemID = itemID;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
    }

    /**
     * Gets the unique identifier for the library item.
     * 
     * @return the item ID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Sets the unique identifier for the library item.
     * 
     * @param itemID the item ID to set
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * Gets the title of the library item.
     * 
     * @return the title of the item
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the library item.
     * 
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author of the library item.
     * 
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

     /**
     * Sets the author of the library item.
     * 
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Gets the ISBN of the library item.
     * 
     * @return the ISBN
     */
    public String getISBN() {
        return ISBN;
    }

     /**
     * Sets the ISBN of the library item.
     * 
     * @param ISBN the ISBN to set
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Gets the publisher of the library item.
     * 
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher of the library item.
     * 
     * @param publisher the publisher to set
     */

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets the number of available copies of the library item.
     * 
     * @return the number of available copies
     */
    public int getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Sets the number of available copies of the library item.
     * 
     * @param availableCopies the available copies to set
     */
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    /**
     * Gets the total number of copies of the library item.
     * 
     * @return the total copies
     */
    public int getTotalCopies() {
        return totalCopies;
    }

    /**
     * Sets the total number of copies of the library item.
     * 
     * @param totalCopies the total copies to set
     */
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    /**
     * Gets the type of the library item (e.g., book, periodical). This method is
     * abstract and must be implemented in subclasses.
     * 
     * @return the type of item as a String
     */
    public abstract String getItemType(); 

    // ADDITIONAL METHODS FOR BOOK/PERIODICAL SUBCLASSES
}
