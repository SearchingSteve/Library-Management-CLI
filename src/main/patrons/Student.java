package main.patrons;
// Student is a subclass of Patron

/**
 * Student class is a subclass of {@link Patron} that represents a student patron 
 * in the library system. It inherits all attributes and methods from Patron.
 */
public class Student extends Patron {

    // CONSTRUCTOR

    /**
     * Constructs a new Student object with the specified attributes.
     * 
     * @param studentId    unique identifier for the student
     * @param name         name of the student
     * @param address      address of the student
     * @param phoneNumber  phone number of the student
     */
    public Student(String studentId, String name, String address, String phoneNumber) {
        // include all params of patron
        super("S" + studentId, name, address, phoneNumber);
    }

    // METHODS 

    // edit a student

    /**
     * Updates the student's contact information.
     * 
     * @param name         new name for the student
     * @param address      new address for the student
     * @param phoneNumber  new phone number for the student
     */
    public void editStudent(String name, String address, String phoneNumber) {
        super.editPatron(name, address, phoneNumber);
    }

    // delete a student

    /**
     * Deletes the student's information and clears borrowed items.
     */
    public void deleteStudent() {
        super.deletePatron();
    }

    // retrieve student based on their ID

    /**
     * Retrieves the student's unique id.
     * 
     * @return the student ID
     */
    public String getStudentId() {
        return this.getId();
    }

    // type = student

    /**
     * Gets the type of patron, which is "Student" for this class.
     * 
     * @return the type of patron as a String ("Student")
     */
    @Override
    public String getType() {
        return "Student";
    }

    // display values to user 

    /**
     * Returns a string representation of the student, including contact 
     * information and the student ID.
     * 
     * @return a string with detailed information about the student
     */
    @Override
    public String toString() {
        return super.toString() + "\nStudent ID: " + this.getId();
    }
}
