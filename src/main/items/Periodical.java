package main.items;

// Periodical class is a subclass of LibraryItem
public class Periodical extends LibraryItem {
    private String periodicalType;

    // Constructor
    public Periodical(String title, String ISBN, String publisher, int availableCopies, Status status,
            String periodicalType) {
        super(title, ISBN, publisher, availableCopies, status);
        this.periodicalType = periodicalType;
    }
}
