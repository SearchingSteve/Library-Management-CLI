package main.patrons;

public abstract class Patron {
    protected String name;
    protected String address;
    protected String phoneNumber;
    protected ArrayList<LibraryItem> borrowedLibraryItems;

    public Patron(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        borrowedLibraryItems = new ArrayList<LibraryItem>();
    }

    public void borrowLibraryItem(LibraryItem libraryItem) {
        borrowedLibraryItems.add(libraryItem);
    }

    public void returnLibraryItem(LibraryItem libraryItem) {
        borrowedLibraryItems.remove(libraryItem);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<LibraryItem> getBorrowedLibraryItems() {
        return borrowedLibraryItems;
    }

    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nPhone Number: " + phoneNumber;
    }
}
