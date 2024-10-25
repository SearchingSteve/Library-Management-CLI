package main.library;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


import main.items.LibraryItem;
import main.authors.Author;
import main.patrons.Patron;

// Main class for the library
public class Library {
    private Map<String, LibraryItem> itemMap; // String = itemID
    private Map<String, Patron> patronMap; // String = patronID

    // Constructor
    public Library(){
        this.itemMap = new HashMap<>();
        this.patronMap = new HashMap<>();
    }

    
    public List<LibraryItem> searchByTitle(String title) {
        return itemMap.values().stream()
                .filter(item -> item.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<LibraryItem> searchByAuthor(Author author) {
        return itemMap.values().stream()
                .filter(item -> item.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<LibraryItem> searchByISBN(String ISBN) {
        return itemMap.values().stream()
                .filter(item -> item.getISBN().equals(ISBN))
                .collect(Collectors.toList());
    }

    // Add a library item
    public void addLibraryItem(LibraryItem item){
        if (itemMap.containsKey(item.getItemID())){
            System.out.println("Item already exists in the library");
        } else {
            itemMap.put(item.getItemID(), item);
        }
    }

    // Remove a library item
    public void removeLibraryItem(LibraryItem item){
        if (!itemMap.containsKey(item.getItemID())){
            System.err.println("Item does not exist in the library");
        } else {
            itemMap.remove(item.getItemID());
        }
    }

    // **SARA**
    // Edit a library item
    public void editLibraryItem(LibraryItem item){
        // Add code to edit a library item
        // Can do something simple like in case 1. Build all the item parameters in LibraryMenu,
        // then call this method to edit the item in the map.
    }

    // Add a patron to the library
    public void addPatron(Patron patron){
        if (patronMap.containsKey(patron.getId())){
            System.out.println("Patron already exists in the library");
        } else {
            patronMap.put(patron.getId(), patron);
        }
    }

    // Remove a patron from the library
    public void removePatron(Patron patron){
        if (!patronMap.containsKey(patron.getId())){
            System.err.println("Patron does not exist in the library");
        } else {  
            // Remove patron from the library referece from patron class. - Might not need 
            patron.deletePatron();
            patronMap.remove(patron.getId());
        }

 
    }


    // **NASSER**
    // PLACEHOLDER for borrowing a library item
    public void borrowLibraryItem(LibraryItem libraryItem) {
        // Add logic to check if the library item is available based on quantity
        
        // if LibraryItem.getAvailableCopies() > 0 and item not null, then decrement available copies by 1
        // else, item is not available
    }

    // PLACEHOLDER for returning a library item
    public void returnLibraryItem(LibraryItem libraryItem) {
        // 
    }


    



    
}
