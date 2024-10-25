package main.items;

import main.authors.Author;

// Periodical class is a subclass of LibraryItem
public class Periodical extends LibraryItem {
    // ASSIGN ATTRIBUTE
    private String periodicalType;

    // CONSTRUCTOR
    public Periodical(int itemID, String title, Author author,  String ISBN, String publisher, int availableCopies, Status status,
            String periodicalType) {
        super(itemID, title, author, ISBN, publisher, availableCopies, status);
        this.periodicalType = periodicalType;
    }

    // GETTER AND SETTER 

    public String getPeriodicalType(){
        return periodicalType;
    }

    public void setPeriodicalType(String periodicalType){
        this.periodicalType = periodicalType;
    }

     @Override
    public String getType(){
        return "periodical";
    }


    // DISPLAY PERIODICAL DETAILS

    @Override 
    public String toString(){
        return "Periodical{ " + 
        "title= " + getTitle() +
        "author= " +  getAuthor().getName() +
        ", ISBN= " + getISBN() + 
        ", publisher= " + getPublisher() +
        ", available copies= " + getAvailableCopies() + 
        ", status= " + getStatus() + 
        ", periodical type= " + getPeriodicalType() +
        "}";
    }

}
