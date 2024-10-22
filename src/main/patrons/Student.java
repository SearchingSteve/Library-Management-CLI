package main.patrons;

public class Student extends Patron {
    private int id; // Initalize variable id to only represent id of Student (by breaking up classes we can individalize objects like id)
    // For example, the set of student id's will be different from the employee id's
    // student id = 100 will not also represent an employee id = 100, thus we can have a unique set of id's for each class
    // Removes the need to have to implement a check to see if the id is unique across all patrons
    // Somewhat overkill for this project but it is a good practice to follow in OOP
    public Student(int id) {
        super(); // Call the constructor of the superclass Student to initialize the Student fields
        this.id = id; // Set the id of the Student
    }
}
