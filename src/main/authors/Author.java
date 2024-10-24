package main.authors;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import main.items.LibraryItem;
public class Author {
    // ASSIGN ATTRIBUTES
    private String author_name;
    private Date DOB;
    private List<LibraryItem> listOfItems;

    // CONSTRUCTOR
    public Author(String author_name, Date DOB){
        this.author_name = author_name;
        this.DOB = DOB;
        // listOfItems tracks LibraryItem s for gievn author
        this.listOfItems = new ArrayList<>();
    }

    // GETTERS AND SETTERS 
    public void setName(String author_name){
        this.author_name = author_name;
    }

    public Date getDOB(){
        return DOB;
    }

    public void setDOB(Date DOB){
        this.DOB = DOB;
    }

    public String getName(){
        return author_name;
    }

    public List<LibraryItem> getListOfItems(){
        return listOfItems;
    }

    // METHODS
    // newAuthor, editAuthor and removeAuthor manage author's info
    // becuase the system is not connected to a memory or database, the 
    // author managment methods simulate information being updated by
    // printing success messages 
    public void newAuthor(){
        System.out.println("Author " + author_name + "added successfully!");
    }

    public void editAuthor(String newName, Date newDOB){
        this.author_name = newName;
        this.DOB = newDOB;
        
        System.out.println("Author " + newName + "information updated!");
    }

    public void removeAuthor(){
        System.out.println("Author " + author_name + "deleted successfully!");
    }

    // addLibraryItem and removeLibraryItem manage library items associated
    // with given author 
    public void addLibraryItem(LibraryItem item){
        listOfItems.add(item);

        System.out.println("Library item " + item.getTitle() + "added successfully!");
    }

    public void removeLibraryItem(LibraryItem item){
        listOfItems.remove(item);

        System.out.println("Library item " + item.getTitle() + "removed successfully!");
    }
}
