package main.patrons;

import main.items.LibraryItem;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public abstract class Patron {
    // Initialize instance variables for the Patron class
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    // private List<LibraryItem> borrowedLibraryItems;
    private Map<String, Integer> borrowedLibraryItems; // String == itemID, Integer = quantity

    // Constructor for the Patron class
    public Patron(String id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowedLibraryItems = new HashMap<>();
    }

    // Basic getters for instance variables of the Patron class
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Map<String, Integer> getBorrowedLibraryItems() {
        return this.borrowedLibraryItems;
    }

    // Abstract method to get the type of the Patron (Student/Employee)
    public abstract String getType();

    // Methods to edit and delete the Patron
    public void editPatron(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void deletePatron() {
        this.name = null;
        this.address = null;
        this.phoneNumber = null;
        borrowedLibraryItems = null;
    }

    // Methods to add and return borrowed items from the Patron's list
    public void addBorrowedItems(String itemID, int quantity) {
        this.borrowedLibraryItems.put(itemID, quantity);
    }

    public void returnBorrowedItems(String itemID, int quantity) {
        this.borrowedLibraryItems.remove(itemID, quantity);
    }

    // Method to check quantity borrowed of a specific item
public int checkQuantityBorrowed(String itemID) {
    return this.borrowedLibraryItems.getOrDefault(itemID, 0); // Return 0 if itemID is not found
}


    // Displays the Patron's information in a string format
    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nPhone Number: " + phoneNumber;
    }
}
