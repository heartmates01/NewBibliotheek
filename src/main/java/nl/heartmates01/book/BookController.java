package nl.heartmates01.book;

import static nl.heartmates01.Main.userInput;
import static nl.heartmates01.Main.bookRepository;

import java.time.LocalDate;
import java.util.regex.Pattern;
import nl.heartmates01.library.BorrowService;

public class BookController {

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
          int ID = Integer.parseInt(
              userInput("Magazine ID:", Pattern.compile("\\d"), "Invalid ID."));
          Book book = bookRepository.findID(ID);
          borrowOrReturn(book);
          break;

        case "4":
          int id = Integer.parseInt(
              userInput("Book ID:", Pattern.compile("\\d"), "Invalid ID."));
          showBook(id);
          break;

        case "5":
          System.out.println("Exiting to previous menu.");
          return;
      }
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
    int bookID = Book.id;
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
    int bookBorrowTime = Book.borrowTime;
    bookRepository.add(bookID, bookTitle, bookAuthor, bookPages, bookBorrowed, bookBorrowTime,
        bookISBN,
        bookPubDate);
    System.out.println("This book's assigned ID is " + bookID);
  }

  static void removeBook() {
    int id = Integer.parseInt(
        userInput("The ID of the book:", Pattern.compile("\\d"), "Invalid ID."));
    bookRepository.removeBook(id);
  }

  static void borrowOrReturn(Book book) {
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
      System.out.println("Exiting to menu.");
      return;

    } else if (borrowOrReturn == 1) {
      BorrowService.borrowItem(book);

    } else if (borrowOrReturn == 2) {
      BorrowService.returnItem(book);
    }
  }

  static void showBook(int id) {
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
    for (Book book : bookRepository.getAll()) {
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
}
