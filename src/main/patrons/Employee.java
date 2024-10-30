package main.patrons;
// employee is a subclass of Patron
public class Employee extends Patron {

    // CONSTRUCTOR
    public Employee(String employeeId, String name, String address, String phoneNumber) {
        // include all params of patron
        super("E" + employeeId, name, address, phoneNumber); 
    }

    // METHODS

    // edit employee
    public void editEmployee(String name, String address, String phoneNumber) {
        super.editPatron(name, address, phoneNumber);
    }

    // DONT NEED - deletePatron() method sets object values to null. Handled by just setting the object to null, and in future garbage collection.
    // // delete employee
    // public void deleteEmployee() {
    //     super.deletePatron();
    // }

    // retrieve employee based on their ID
    public String getEmployeeId() {
        return this.getId();
    }

    // type = employee
    @Override
    public String getType() {
        return "Employee";
    }

    // display values to user 
    @Override
    public String toString() {
        return super.toString() + "\nEmployee ID: " + this.getId();
    }

}
