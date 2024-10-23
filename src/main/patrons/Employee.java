package main.patrons;

public class Employee extends Patron {
    private int id; // Initalize variable id to only represent id of Employee

    public Employee(String name, String address, String phoneNumber, int id) {
        super(name, address, phoneNumber); // Call the constructor of the superclass Employee to initialize the Employee
                                           // fields
        this.id = id; // Set the id of the Employee
    }

}
