package main.patrons;

public class Student extends Patron {


    public Student(String studentId, String name, String address, String phoneNumber) {
        super("S" + studentId, name, address, phoneNumber);
    }

    public void editStudent(String name, String address, String phoneNumber) {
        super.editPatron(name, address, phoneNumber);
    }

    public void deleteStudent() {
        super.deletePatron();
    }

    public String getStudentId() {
        return this.getId();
    }

    @Override
    public String getType() {
        return "Student";
    }

    @Override
    public String toString() {
        return super.toString() + "\nStudent ID: " + this.getId();
    }
}
