package main.authors;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import main.items.LibraryItem;
import main.library.Library;
import main.library.LibraryMenu;

/**
 * Author class represents an author in the library system, containing
 * attributes for the author's name, date of birth, and associated library
 * items.
 * It also provides methods to manage authors in a simulated database.
 */
public class Author {
    /** The name of the author. */
    private String author_name;
    /** The date of birth of the author. */
    private Date DOB;
    /** List of library items authored by this author. */
    private List<LibraryItem> authoredItems;

    /**
     * Static list to keep track of all authors in the system.
     * Simulates an in-memory database.
     */
    public static List<Author> authorList = new ArrayList<>();

    /**
     * Constructs an Author with the given name and date of birth.
     * 
     * @param author_name The author's name.
     * @param DOB         The author's date of birth.
     */
    public Author(String author_name, Date DOB) {
        this.author_name = author_name;
        this.DOB = DOB;

        // authoredItems tracks LibraryItem s for gievn author
        this.authoredItems = new ArrayList<>();
    }

    /**
     * Sets the name of the author.
     *
     * @param author_name The author's name.
     */
    public void setName(String author_name) {
        this.author_name = author_name;
    }

    /**
     * Gets the author's date of birth.
     *
     * @return The author's date of birth.
     */
    public Date getDOB() {
        return DOB;
    }

    /**
     * Sets the author's date of birth.
     *
     * @param DOB The new date of birth for the author.
     */
    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    /**
     * Gets the author's name.
     *
     * @return The author's name.
     */
    public String getName() {
        return author_name;
    }

    /**
     * Retrieves a list of all authors currently in the system.
     *
     * @return A list of all authors.
     */
    public List<Author> getAllAuthors() {
        return new ArrayList<>(Author.authorList);
    }

    /**
     * Retrieves the list of items written by given author.
     *
     * @return A list of authored items.
     */
    public List<LibraryItem> getAuthoredItems() {
        return authoredItems;
    }

    public static void displayAuthoredItems(String authorName) {
        Author author = getAuthorByName(authorName);
        if (author != null) {
            List<LibraryItem> authoredItems = author.getAuthoredItems();
            if (authoredItems.isEmpty()) {
                System.out.println("Author " + authorName + " has not authored any items.");
            } else {
                System.out.println("Items authored by " + authorName + ":");
                for (LibraryItem item : authoredItems) {
                    System.out.println(item.getTitle() + " - " + item.getItemID());
                }
            }
        }
        else {
            System.out.println("Author with name: " + authorName + " not found.");
        }
    }
    

    /**
     * Validates the date of birth input for an author based on specified
     * limitations (date format and that it is not a future date).
     *
     * @param DOB The date of birth as a string in the format "YYYY-MM-DD".
     * @return True if the date of birth is valid; otherwise, false.
     */
    public static boolean validateAuthorDOB(String DOB) {
        Date currentDate = new Date();
        Date parsedDate = null;

        if (DOB == null || DOB.isEmpty()) {
            System.out.println("\nDate of birth cannot be empty.");
            System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
            return false;
        }

        if (!DOB.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("\nInvalid date format. Please use the format YYYY-MM-DD.");
            System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
            return false;
        }

        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);
        } catch (ParseException e) {
            // e.printStackTrace();
            System.err.println("\nInvalid date format. Please use the format YYYY-MM-DD.");
            System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
            return false;
        }

        if (parsedDate.after(currentDate)) {
            System.out.println("\nDate of birth cannot be in the future.");
            System.out.print("Enter author Date of Birth (YYYY-MM-DD): ");
            return false;
        }

        String[] dateParts = DOB.split("-");
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        if (month < 1 || month > 12) {
            System.out.println("\nMonth must be between 1 and 12.");
            System.out.print("Enter author date of birth (YYYY-MM-DD): ");
            return false;
        } else if (day < 1 || day > 31) {
            System.out.println("\nDay must be between 1 and 31.");
            System.out.print("Enter author date of birth (YYYY-MM-DD): ");
            return false;
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
            System.out.println("\nMonth " + month + " does not have 31 days.");
            System.out.print("Enter author date of birth (YYYY-MM-DD): ");
            return false;
        } else if (month == 2) {
            int year = Integer.parseInt(dateParts[0]);
            if (day > 29 || (day == 29 && !((year % 4 == 0 && year % 100 != 0) || year % 400 == 0))) {
                System.out.println("\nFebruary " + year + " does not have " + day + " days.");
                System.out.print("Enter author date of birth (YYYY-MM-DD): ");
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a new author to the author list if they are not already present.
     *
     * @param author The author to be added.
     * @return True if the author was added successfully; otherwise, false.
     */
    public static boolean newAuthor(Author author) {
        if (!authorList.contains(author)) {
            authorList.add(author);
            System.out.println("Author " + author.getName() + "added successfully!");
            return true;
        }
        System.out.println("Author " + author.getName() + "is already in the system.");
        return false;
    }

    /**
     * Edits the information of an existing author.
     *
     * @param oldName The current name of the author to be edited.
     * @param newName The new name for the author.
     * @param newDOB  The new date of birth for the author.
     * @return True if the author was edited successfully; otherwise, false.
     */
    public static boolean editAuthor(String oldName, String newName, Date newDOB) {
        for (Author author : authorList) {
            if (author.getName().equals(oldName)) {
                author.setName(newName);
                author.setDOB(newDOB);
                System.out.println("Author " + newName + "information updated!");
                return true;
            }
        }
        System.out.println("Author " + newName + "is not currently in the system. cannot edit.");
        return false;
    }

    /**
     * Removes an author and their associated items from the system after
     * confirmation.
     *
     * @param authorName The name of the author to be removed.
     * @return True if the author was removed successfully; otherwise, false.
     */
    /**
 * Removes an author and their associated items from the system after
 * confirmation.
 *
 * @param authorName The name of the author to be removed.
 * @return True if the author was removed successfully; otherwise, false.
 */
public static boolean removeAuthor(String authorName) {
    for (Author author : authorList) {
        if (author.getName().equalsIgnoreCase(authorName)) {
            // Prompt user to confirm deletion (deletion will remove all author items)
            System.out.print("Are you sure you want to delete author " + authorName + " and all associated items? (Y/N) ");
            String response = System.console().readLine();
            if (response.equalsIgnoreCase("Y")) {
                Library library = new Library();
                library.removeAuthorItems(author);  // Remove all items associated with the author
                authorList.remove(author);  // Remove the author from the list
                System.out.println("Author " + authorName + " and all associated items have been deleted.");
                return true;
            } else {
                System.out.println("Deletion cancelled.");
            }
            return false;
        }
    }
    System.out.println("Author " + authorName + " is not currently in the system.");
    return false;
}


    /**
     * Adds a library item to the author's list of authored items.
     *
     * @param item The library item to be added.
     */
    public void addAuthoredItem(LibraryItem item) {
        authoredItems.add(item);
        System.out.println("Library item " + item.getTitle() + "added successfully!");
    }

    /**
     * Removes a library item from the author's list of authored items.
     *
     * @param item The library item to be removed.
     */
    public void removeAuthoredItem(LibraryItem item) {
        authoredItems.remove(item);
        System.out.println("Library item " + item.getTitle() + "removed successfully!");
    }

    /**
     * Finds and returns an author by their name from the author list.
     *
     * @param authorName The name of the author to be retrieved.
     * @return The author with the specified name, or null if not found.
     */
    public static Author getAuthorByName(String authorName) {
        for (Author author : authorList) {
            if (author.getName().equals(authorName)) {
                return author;
            }
        }
        return null;
    }

    /**
     * Displays information of authors with the specified names.
     *
     * @param authorNames Optional names of authors to display; if empty or blank,
     *                    displays all authors.
     */
    public static void displayAuthors(String... authorNames) {
        // Check if input is empty or consists of a single blank entry
        if (authorNames.length == 0 || (authorNames.length == 1 && authorNames[0].isBlank())) {
            // No author names provided or a blank input, display all authors
            authorList.forEach(author -> System.out.println(author.getName() + " - Date of Birth: " + author.getDOB()));
        } else {
            // Display authors for the provided names
            for (String name : authorNames) {
                final String trimmedName = name.trim(); // Trim to handle any extra spaces
                if (!trimmedName.isEmpty()) { // Only attempt lookup for non-empty names
                    Author foundAuthor = authorList.stream()
                            .filter(author -> author.getName().equalsIgnoreCase(trimmedName))
                            .findFirst()
                            .orElse(null);
                    if (foundAuthor != null) {
                        System.out.println(foundAuthor.getName() + " - Date of Birth: " + foundAuthor.getDOB());
                    } else {
                        System.out.println("Author with name: " + trimmedName + " not found.");
                    }
                }
            }
        }
    }
}
