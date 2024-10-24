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

    // create static list so authors are kept track of
    // memory/ database is simulated for methods that manage author info
    private static List<Author> authorList = new ArrayList<>();

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
    public static boolean newAuthor(Author author){
        if (!authorList.contains(author)){
            authorList.add(author);
            System.out.println("Author " + author.getName() + "added successfully!");
            return true;
        }System.out.println("Author " + author.getName() + "is already in the system.");
        return false;
    }

    public static boolean editAuthor(String oldName, String newName, Date newDOB){
        for(Author author : authorList){
            if (author.getName().equals(oldName)){
                author.setName(newName);
                author.setDOB(newDOB);
                System.out.println("Author " + newName + "information updated!");
                return true;
            }
        }
        
        System.out.println("Author " + newName + "is not currently in the system. cannot edit.");
        return false;
    }

    public static boolean removeAuthor(String authorName){
        for(Author author : authorList){
            if(author.getName().equals(authorName)){
                authorList.remove(author);
                System.out.println("Author " + authorName + "deleted successfully!");
                return true;
            }
        }System.out.println("Author" + authorName + "is not currently in the system. cannot be deleted.");
        return false;
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
