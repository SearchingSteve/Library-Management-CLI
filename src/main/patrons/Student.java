package main.patrons;
// Student is a subclass of Patron
public class Student extends Patron {

    // CONSTRUCTOR

    public Student(String studentId, String name, String address, String phoneNumber) {
        // include all params of patron
        super("S" + studentId, name, address, phoneNumber);
    }

    // METHODS 

    // edit a student
    public void editStudent(String name, String address, String phoneNumber) {
        super.editPatron(name, address, phoneNumber);
    }

    // DONT NEED - deletePatron() method sets object values to null. Handled by just setting the object to null, and in future garbage collection.
    // // delete a student
    // public void deleteStudent() {
    //     super.deletePatron();
    // }

    // retrieve student based on their ID
    public String getStudentId() {
        return this.getId();
    }

    // type = student
    @Override
    public String getType() {
        return "Student";
    }

    // display values to user 
    @Override
    public String toString() {
        return super.toString() + "\nStudent ID: " + this.getId();
    }
}
