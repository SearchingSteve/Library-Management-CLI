package main.patrons;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class representing a patron in the library system, 
 * which can either be a student or an employee.
 * Each patron has basic contact information and a list of borrowed items.
 */
public abstract class Patron {
      /** Unique identifier for the patron. */
    private String id;
    /** Name of the patron. */
    private String name;
     /** Address of the patron. */
    private String address;
      /** Phone number of the patron. */
    private String phoneNumber;
    /** Map of borrowed library items, where the key is the item ID and the value is the quantity borrowed. */
    private Map<String, Integer> borrowedLibraryItems; // String == itemID, Integer = quantity


    /**
     * Constructs a new Patron object with the specified attributes.
     * 
     * @param id          unique identifier for the patron
     * @param name        name of the patron
     * @param address     address of the patron
     * @param phoneNumber phone number of the patron
     */
    public Patron(String id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowedLibraryItems = new HashMap<>();
    }

       /**
     * Gets the unique identifier for the patron.
     * 
     * @return the patron's ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the name of the patron.
     * 
     * @return the patron's name
     */
    public String getName() {
        return this.name;
    }

     /**
     * Sets the name of the patron.
     * 
     * @param name the name to set
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the address of the patron.
     * 
     * @return the patron's address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address of the patron.
     * 
     * @param address the address to set
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Gets the phone number of the patron.
     * 
     * @return the patron's phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Sets the phone number of the patron.
     * 
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the map of borrowed library items.
     * 
     * @return the map of borrowed items with item ID as the key and quantity as the value
     */
    public Map<String, Integer> getBorrowedLibraryItems() {
        return this.borrowedLibraryItems;
    }

     /**
     * Abstract method to get the type of patron (e.g., student, employee).
     * 
     * @return the type of patron as a String
     */
    public abstract String getType();

    /**
     * Updates the patron's contact information.
     * 
     * @param name        new name for the patron
     * @param address     new address for the patron
     * @param phoneNumber new phone number for the patron
     */
    public void editPatron(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public void addBorrowedItems(String itemID, int quantity) {
        this.borrowedLibraryItems.put(itemID, (this.checkQuantityBorrowed(itemID) + quantity));
    }

    /**
     * Returns borrowed items, reducing the quantity or removing the item if the quantity is zero.
     * 
     * @param itemID  ID of the returned item
     * @param quantity quantity of the item being returned
     */
    public void returnBorrowedItem(String itemID, int quantity) {
        this.borrowedLibraryItems.remove(itemID, quantity);
        if (this.borrowedLibraryItems.containsKey(itemID)) {
            int currentQuantity = this.borrowedLibraryItems.get(itemID);
            if (currentQuantity > quantity) {
                this.borrowedLibraryItems.put(itemID, currentQuantity - quantity);
            } else {
                this.borrowedLibraryItems.remove(itemID);
            }
        }
    }

    /**
     * Checks the quantity of a specific item that the patron has borrowed.
     * 
     * @param itemID ID of the item to check
     * @return the quantity borrowed of the specified item, or 0 if not found
     */
    public int checkQuantityBorrowed(String itemID) {
        return this.borrowedLibraryItems.getOrDefault(itemID, 0); // Return 0 if itemID is not found
    }

    /**
     * Returns a string representation of the patron, including their name, address, phone number, and borrowed items.
     * 
     * @return a string with detailed information about the patron
     */
    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nPhone Number: " + phoneNumber
                + "\nList of Borrowed Items: "
                + borrowedLibraryItems;
    }
}
