package manage_library;

import java.util.*;

class Book {
    private String title;
    private String author;
    private String category;
    private boolean isIssued;

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        this.isIssued = true;
    }

    public void returnBook() {
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Category: " + category + ", Issued: " + (isIssued ? "Yes" : "No");
    }
}

class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
}

public class manageLibrary {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();
        while (true) {
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    userMenu();
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    private static void seedData() {
        library.addBook(new Book("it end with us", "Colleen Hoover", "Fiction"));
        library.addBook(new Book("it starts with us", "Colleen Hoover", "Fiction"));
        library.addBook(new Book("Too late", "Colleen Hoover", "Fiction"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction"));
        library.addUser(new User("sirin", "sirin157@gmail.com"));
        library.addUser(new User("zlion", "zlion1317@gmail.com"));
    }

    private static void adminMenu() {
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        if (!"admin".equals(password)) {
            System.out.println("Invalid password.");
            return;
        }

        while (true) {
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    return;
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book category: ");
        String category = scanner.nextLine();

        library.addBook(new Book(title, author, category));
        System.out.println("Book added successfully.");
    }

    private static void viewBooks() {
        for (Book book : library.getBooks()) {
            System.out.println(book);
        }
    }

    private static void userMenu() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        User user = library.findUserByUsername(username);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        while (true) {
            System.out.println("1. View Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    issueBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void issueBook() {
        System.out.print("Enter book title to issue: ");
        String title = scanner.nextLine();
        Book book = library.findBookByTitle(title);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Book is already issued.");
        } else {
            book.issue();
            System.out.println("Book issued successfully.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        Book book = library.findBookByTitle(title);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isIssued()) {
            System.out.println("Book is not issued.");
        } else {
            book.returnBook();
            System.out.println("Book returned successfully.");
        }
    }
}