package nl.heartmates01.book;

import static nl.heartmates01.Main.userInput;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class BookController {

  static BookRepository bookRepository = new BookRepository();

  public static void showBookMenu() {

    while (true) {
      System.out.println("""
           \s
           Library Management System
           \s
           1. Manage Singular Book
           2. Manage Multiple Books
           3. Exit to main menu
          """);

      String userBookChoice = userInput("Choose an option from the list:",
          Pattern.compile("[0-3]"),
          "Choose a valid option");

      switch (userBookChoice) {
        case "1":
          handleSingularBook();
          break;

        case "2":
          handleMultipleBooks();
          break;
        //etc
        case "3":
          System.out.println("Exiting to main menu.");
          return;
      }
    }
  }

  public static void handleSingularBook() {
    while (true) {
      System.out.println("""
          \s
          Library Management System
          \s
          1. Add a Book
          2. Remove a Book
          3. Borrow or Return a Book
          4. Show a Book's information
          5. Exit to previous menu
          """);
      String userSingularChoice = userInput("Choose an option from the list.",
          Pattern.compile("[0-5]"), "Choose a valid option.");

      switch (userSingularChoice) {
        case "1":
          addBook();
          break;

        case "2":
          removeBook();
          break;

        case "3":
          handleBookActions();
          break;

        case "4":
          long showSingularBookID = Long.parseLong(
              userInput("Book ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
          showBook(showSingularBookID);
          break;

        case "5":
          System.out.println("Exiting to previous menu.");
          return;
      }
    }
  }

  public static void handleBookActions() {

    long singularBookID = Long.parseLong(
        userInput("Book ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
    System.out.println("""
        \s
        Library Management System
        \s
        1. Borrow
        2. Return
        3. Exit to previous menu""");

    int borrowOrReturn = Integer.parseInt(
        userInput("Choose an option from the list.", Pattern.compile("[0-3]"),
            "Choose a valid option."));
    if (borrowOrReturn == 3) {
      System.out.println("Exiting to previous menu.");
      BookController.handleSingularBook();

    } else if (borrowOrReturn == 1) {
      showBorrowBook(singularBookID);

    } else if (borrowOrReturn == 2) {
      showReturnBook(singularBookID);
    }
  }

  static void handleMultipleBooks() {
    while (true) {
      System.out.println("""
          \s
          Library Management Menu
          \s
          1. Show all Books
          2. Show all borrowed Books
          3. Show all available Books
          4. Exit to previous menu""");

      String userMultipleChoice = userInput("Choose an option from the list.",
          Pattern.compile("[0-4]"), "Choose a valid option.");

      switch (userMultipleChoice) {
        case "1":
          showBooks();
          break;
        case "2":
          showBorrowedBooks();
          break;
        case "3":
          showAvailableBooks();
          break;
        case "4":
          System.out.println("Exiting to previous menu.");
          return;
      }
    }
  }

  static void addBook() {
    long bookID = Long.parseLong(userInput("Book ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
    String bookTitle = userInput("Book Title:", null, null);
    String bookAuthor = userInput("Book Author:", null, null);
    int bookPages = Integer.parseInt(
        userInput("Number of Pages;", null, "Invalid number."));
    long bookISBN = Long.parseLong(
        userInput("Book ISBN:", Pattern.compile("\\d{13}"), "Invalid ISBN."));
    boolean bookBorrowed = Boolean.parseBoolean(
        userInput("Currently being borrowed (Y/N):", null, null));
    LocalDate bookPubDate = LocalDate.parse(userInput("Date of Publication(YYYY-MM-DD):",
        Pattern.compile("\\d{4}-\\d{2}-\\d{2}"),
        "Invalid date."));
    bookRepository.add(bookID, bookTitle, bookAuthor, bookPages, bookBorrowed, bookISBN,
        bookPubDate);
  }

  static void removeBook() {
    long id = Long.parseLong(
        userInput("The ID of the book:", Pattern.compile("\\d{10}"), "Invalid ID."));
    bookRepository.removeBook(id);
  }


  static void showBook(long id) {
    Book book = bookRepository.findID(id);
    if (book != null) {
      System.out.println(book.getTitleWithAuthor());
    } else {
      System.out.println("Book not in list.");
    }
  }

  // methods zonder directe toegang tot Books

  static void showBooks() {
    String result = "";
    for (Book book : bookRepository.getBooks()) {
      System.out.println(result + book.getTitleWithAuthor());
    }
  }


  static void showBorrowedBooks() {
    String result = "";
    for (Book book : bookRepository.getBorrowedBooks()) {
      System.out.println(result + book.getTitleWithAuthor());
    }
  }

  static void showAvailableBooks() {
    String result = "";
    for (Book book : bookRepository.getAvailableBooks()) {
      System.out.println(result + book.getTitleWithAuthor());
    }
  }

  public static void showBorrowBook(long id) {
    Book book = bookRepository.findID(id);
    if (book != null) {
      book.borrowBook();
      System.out.println("Book has been borrowed.");
    } else {
      System.out.println("Book not in list.");
    }
  }

  public static void showReturnBook(long id) {
    Book book = bookRepository.findID(id);
    if (book != null) {
      book.returnBook();
      System.out.println("Book has been returned.");
    } else {
      System.out.println("Book not in list.");
    }
  }
}
