package main.patrons;

public class Employee extends Patron {

    public Employee(String employeeId, String name, String address, String phoneNumber) {
        super("E" + employeeId, name, address, phoneNumber); 
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
