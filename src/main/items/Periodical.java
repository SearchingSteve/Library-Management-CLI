package main.items;
import main.authors.Author;


// Periodical class is a subclass of LibraryItem

/**
 * Periodical class is a subclass of {@link LibraryItem} that represents a type of library item, 
 * such as a magazine, journal, or newspaper. It includes all attributes from LibraryItem 
 * and adds a specific attribute for the type of periodical.
 */
public class Periodical extends LibraryItem {
    // ASSIGN ATTRIBUTE
    /** Type of the periodical (e.g., printed, electronic). */
    private String periodicalType;

    // CONSTRUCTOR
    // include all libraryitem params and periodicalType (specifc to this subclass)

    /**
     * Constructs a new Periodical object with the specified attributes, 
     * including all attributes from LibraryItem and an additional periodical type.
     *
     * @param itemID          unique identifier for the periodical
     * @param title           title of the periodical
     * @param author          author associated with the periodical
     * @param ISBN            ISBN of the periodical
     * @param publisher       publisher of the periodical
     * @param availableCopies number of available copies of the periodical
     * @param totalCopies     total number of copies of the periodical
     * @param status          current status of the periodical
     * @param periodicalType  specific type of periodical (e.g., magazine, journal)
     */
    public Periodical(String itemID, String title, Author author,  String ISBN, String publisher, int availableCopies, int totalCopies,  Status status,
            String periodicalType) {
        super("P" + itemID, title, author, ISBN, publisher, availableCopies, totalCopies, status);
        this.periodicalType = periodicalType;
    }

    // GETTER AND SETTERS

    /**
     * Gets the specific type of the periodical.
     *
     * @return the type of the periodical
     */
    public String getPeriodicalType(){
        return periodicalType;
    }
    
    /**
     * Sets the specific type of the periodical.
     *
     * @param periodicalType the type of the periodical to set
     */

    public void setPeriodicalType(String periodicalType){
        this.periodicalType = periodicalType;
    }
    
    // IMPLEMENTATION OF ABSTRACT METHOD

    /**
     * Gets the item type for this library item, which is "Periodical".
     *
     * @return the type of this item as a String ("Periodical")
     */
    @Override
    public String getItemType() {
        return "Periodical";
    }



    // DISPLAY PERIODICAL DETAILS

    /**
     * Returns a string representation of the periodical, including its title, author, ISBN,
     * publisher, available and total copies, status, and periodical type.
     *
     * @return a string with detailed information about the periodical
     */
    @Override 
    public String toString(){
        return "Periodical{ " + 
        "title= " + getTitle() +
        "author= " +  getAuthor().getName() +
        ", ISBN= " + getISBN() + 
        ", publisher= " + getPublisher() +
        ", available copies= " + getAvailableCopies() + 
        ", total copies= " + getTotalCopies() +
        ", status= " + getStatus() + 
        ", periodical type= " + getPeriodicalType() +
        "}";
    }

}
