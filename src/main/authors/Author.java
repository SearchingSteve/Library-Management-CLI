package main.authors;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import main.items.LibraryItem;
import main.library.Library;
import main.library.LibraryMenu;

public class Author {
    // ASSIGN ATTRIBUTES
    private String author_name;
    private Date DOB;
    private List<LibraryItem> authoredItems;

    // create static list so authors are kept track of
    // memory/ database is simulated for methods that manage author info
    public static List<Author> authorList = new ArrayList<>();

    // CONSTRUCTOR
    public Author(String author_name, Date DOB) {
        this.author_name = author_name;
        this.DOB = DOB;

        // authoredItems tracks LibraryItem s for gievn author
        this.authoredItems = new ArrayList<>();
    }

    // GETTERS AND SETTERS
    public void setName(String author_name) {
        this.author_name = author_name;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getName() {
        return author_name;
    }

    public List<Author> getAllAuthors() {
        return new ArrayList<>(Author.authorList);
    }

    public List<LibraryItem> getAuthoredItems() {
        return authoredItems;
    }

    // validate author DOB object for menu error handling
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

    // METHODS
    // newAuthor, editAuthor and removeAuthor manage author's info
    // becuase the system is not connected to a memory or database, the
    // author managment methods simulate information being updated by
    // printing success messages
    public static boolean newAuthor(Author author) {
        if (!authorList.contains(author)) {
            authorList.add(author);
            System.out.println("Author " + author.getName() + "added successfully!");
            return true;
        }
        System.out.println("Author " + author.getName() + "is already in the system.");
        return false;
    }

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

    // Remove author and associated items
    public static boolean removeAuthor(String authorName) {
        boolean itemCanBeRemoved = false;
        for (Author author : authorList) {
            if (author.getName().equals(authorName)) {
                // Prompt user to confirm deletion (deletion will remove all author items
                while (true) {
                    System.out.println("Are you sure you want to delete author " + authorName + "? (Y/N)");
                    String response = System.console().readLine();
                    if (response.equalsIgnoreCase("Y")) {
                        itemCanBeRemoved = true;
                        authorList.remove(author);
                        System.out.println("Author " + authorName + "deleted successfully!");
                        System.out.println("All authored items by " + authorName + "have been removed.");
                        break;
                    } else if (response.equalsIgnoreCase("N")) {
                        System.out.println("Author " + authorName + "deletion cancelled.");
                        break;
                    } else {
                        System.out.println("Invalid response. Please enter Y or N.");
                    }
                }
                return itemCanBeRemoved;
            }
        }
        System.out.println("Author" + authorName + "is not currently in the system. cannot be deleted.");
        return itemCanBeRemoved;
    }

    // addLibraryItem and removeLibraryItem manage library items associated
    // with given author
    public void addAuthoredItem(LibraryItem item) {
        authoredItems.add(item);

        System.out.println("Library item " + item.getTitle() + "added successfully!");
    }

    public void removeAuthoredItem(LibraryItem item) {
        authoredItems.remove(item);

        System.out.println("Library item " + item.getTitle() + "removed successfully!");
    }

    public static Author getAuthorByName(String authorName) {
        for (Author author : authorList) {
            if (author.getName().equals(authorName)) {
                return author;
            }
        }
        return null;
    }

    // displayAuthors method displays all authors or authors with given names
    public static void displayAuthors(String... authorNames) {
        if (authorNames.length == 0) {
            // No author names provided, display all authors
            authorList.forEach(author -> System.out.println(author));
        } else {
            // Display authors for the provided names
            for (String name : authorNames) {
                Author foundAuthor = authorList.stream()
                        .filter(author -> author.getName().equalsIgnoreCase(name))
                        .findFirst()
                        .orElse(null);
                if (foundAuthor != null) {
                    System.out.println(foundAuthor);
                } else {
                    System.out.println("Author with name: " + name + " not found.");
                }
            }
        }
    }
}
