package main.patrons;

import main.items.LibraryItem;
import java.util.ArrayList;
import java.util.List;

public abstract class Patron {
    // Initialize instance variables for the Patron class
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private List<LibraryItem> borrowedLibraryItems;

    // Constructor for the Patron class
    public Patron(String id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowedLibraryItems = new ArrayList<>();
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

    public List<LibraryItem> getBorrowedLibraryItems() {
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
    public void addBorrowedItems(LibraryItem libraryItem) {
        this.borrowedLibraryItems.add(libraryItem);
    }

    public void returnBorrowedItems(LibraryItem libraryItem) {
        this.borrowedLibraryItems.remove(libraryItem);
    }

    // Displays the Patron's information in a string format
    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nPhone Number: " + phoneNumber;
    }
}
