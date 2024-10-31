package main.items;

import main.authors.Author;

// Book class is a subclass of LibraryItem
/**
 * Book class is a subclass of {@link LibraryItem} that represents a specific
 * type of library item, a book.
 * It includes all attributes from LibraryItem and adds a specific attribute for
 * the type of book.
 */
public class Book extends LibraryItem {
    /** Type of the book (e.g., printed, audio, electronic). */
    private String bookType;

    /**
     * Constructs a new Book object with the specified attributes, including all
     * attributes from LibraryItem and an additional book type.
     *
     * @param itemID          unique identifier for the book
     * @param title           title of the book
     * @param author          author of the book
     * @param ISBN            ISBN of the book
     * @param publisher       publisher of the book
     * @param availableCopies number of available copies of the book
     * @param totalCopies     total number of copies of the book
     * @param bookType        specific type of book (e.g., fiction, non-fiction)
     */
    public Book(String itemID, String title, Author author, String ISBN, String publisher, int availableCopies,
            int totalCopies, String bookType) {
        super("B" + itemID, title, author, ISBN, publisher, availableCopies, totalCopies);
        this.bookType = bookType;
    }

    /**
     * Gets the author of the book.
     *
     * @return the author of the book
     */
    public Author getAuthor() {
        return super.getAuthor();
    }

    /**
     * Sets the author of the book.
     *
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        super.setAuthor(author);
    }

    /**
     * Gets the specific type of the book.
     *
     * @return the type of the book
     */
    public String getBookType() {
        return bookType;
    }

    /**
     * Sets the specific type of the book.
     *
     * @param bookType the type of the book to set
     */
    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    /**
     * Gets the item type for this library item, which is "Book".
     *
     * @return the type of this item as a String ("Book")
     */
    @Override
    public String getItemType() {
        return "Book";

    }

    /**
     * Returns a string representation of the book, including its title, ISBN,
     * author, publisher, available and total copies, and book type.
     *
     * @return a string with detailed information about the book
     */
    @Override
    public String toString() {
        return "Book Information{ " +
                "title= " + getTitle() +
                ", ISBN= " + getISBN() +
                ", author= " + getAuthor().getName() +
                ", publisher= " + getPublisher() +
                ", available copies= " + getAvailableCopies() +
                ", total copies= " + getTotalCopies() +
                ", book type= " + getBookType() +
                "}";
    }
}
