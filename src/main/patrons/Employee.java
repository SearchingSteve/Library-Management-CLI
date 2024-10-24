package main.patrons;

public class Employee extends Patron {
    private static int employeeIdCounter = 100; // Counter to keep track of the id of the Employee
    // private int employeeId;
    // Initalize variable id to only represent id of Employee

    public Employee(String employeeId, String name, String address, String phoneNumber) {
        super("E" + employeeIdCounter, name, address, phoneNumber); // Call the constructor of the superclass Student to
                                                                    // initialize the Employee fields
        employeeIdCounter++;
    }

    public void editEmployee(String name, String address, String phoneNumber) {
        super.editPatron(name, address, phoneNumber);
    }

    public void deleteEmployee() {
        super.deletePatron();
    }

    public String getEmployeeId() {
        return this.getId();
    }

    @Override
    public String getType() {
        return "Employee";
    }

    @Override
    public String toString() {
        return super.toString() + "\nEmployee ID: " + this.getId();
    }

}
