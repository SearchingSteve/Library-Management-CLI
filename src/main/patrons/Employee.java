package main.patrons;

/**
 * Employee class is a subclass of {@link Patron} that represents an employee patron 
 * in the library system. It inherits all attributes and methods from Patron.
 */
public class Employee extends Patron {

    /**
     * Constructs a new Employee object with the specified attributes.
     * 
     * @param employeeId   unique identifier for the employee
     * @param name         name of the employee
     * @param address      address of the employee
     * @param phoneNumber  phone number of the employee
     */
    public Employee(String employeeId, String name, String address, String phoneNumber) {
        // include all params of patron
        super("E" + employeeId, name, address, phoneNumber); 
    }

    
    /**
     * Updates the employee's contact information.
     * 
     * @param name         new name for the employee
     * @param address      new address for the employee
     * @param phoneNumber  new phone number for the employee
     */
    public void editEmployee(String name, String address, String phoneNumber) {
        super.editPatron(name, address, phoneNumber);
    }

    /**
     * Deletes the employee's information and clears borrowed items.
     */
    public void deleteEmployee() {
        super.deletePatron();
    }


    /**
     * Retrieves the employee's unique id.
     * 
     * @return the employee ID as a String prefixed with "E" (e.g., "E001")
     */
    public String getEmployeeId() {
        return this.getId();
    }

    /**
     * Gets the type of patron, which is "Employee" for this class.
     * 
     * @return the type of patron as a String ("Employee")
     */
    @Override
    public String getType() {
        return "Employee";
    }

    /**
     * Returns a string representation of the employee, including contact 
     * information and the employee ID.
     * 
     * @return a string with detailed information about the employee, 
     *         including the employee ID prefixed with "E"
     */

    @Override
    public String toString() {
        return super.toString() + "\nEmployee ID: " + this.getId();
    }

}
