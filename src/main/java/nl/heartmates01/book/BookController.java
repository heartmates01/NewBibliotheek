package nl.heartmates01.book;

import static nl.heartmates01.main.Main.authorRepository;
import static nl.heartmates01.main.Main.bookRepository;
import static nl.heartmates01.main.Main.publisherRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import nl.heartmates01.main.Main;

public class BookController {

  //sonarqube recommended this
  private BookController() {
    throw new IllegalStateException("Utility class BookController; java:S1118");
  }

  static final List<Map<String, Runnable>> bookOptions = List.of(
      Map.of("Add a Book", BookController::addBook),
      Map.of("Remove a Book", BookController::removeBook),
      Map.of("List singular Book", BookController::getFromId),
      Map.of("Borrow or Return Book", BookController::borrowOrReturn),
      Map.of("List all Books", BookController::listAllBooks),
      Map.of("Search Books by Keyword", BookController::searchByKeyword),
      Map.of("Exit", () -> System.exit(0))
  );

  private static void handleBookOptions(int index) {
    bookOptions.get(index).values().forEach(Runnable::run);
  }

  public static void showBookMenu() {
    while (true) {
      System.out.println("Choose an option:");
      for (int i = 0; i < bookOptions.size(); i++) {
        System.out.println(i + ". " + bookOptions.get(i).keySet().iterator().next());
      }
      int option = Integer.parseInt(Main.getUserInput("Enter the option number: "));

      handleBookOptions(option);
    }
  }

  public static void addBook() {
    Map<String, Object> userInput = new BookForm().getUserInput();

    bookRepository.add(new Book(
        (String) userInput.get("title"),
        authorRepository.get((Integer) userInput.get("authorId")).get(),
        (Integer) userInput.get("pages"),
        (Boolean) userInput.get("borrowed"),
        (Long) userInput.get("isbn"),
        (LocalDate) userInput.get("publicationDate"),
        publisherRepository.get((Integer) userInput.get("pubId")).get()
    ));
    System.out.println(userInput.get("title") + " has been added.");
  }

  public static void removeBook() {
    int id = Integer.parseInt(Main.getUserInput("Enter this book's ID: "));
    bookRepository.delete(id);
    System.out.println("Book has been deleted.");
  }

  public static void getFromId() {
    int id = Integer.parseInt(Main.getUserInput("Enter this book's ID: "));
    bookRepository.get(id).ifPresent(book -> System.out.println(book.toString()));
  }

  //'Book book' doesn't work 'cause of Optional
  public static void borrowOrReturn() {
    int id = Integer.parseInt(Main.getUserInput("Enter this book's ID: "));
//    Book book = bookRepository.get(id);
//    borrowController.borrowOrReturn(book);
  }

  public static void listAllBooks() {
    bookRepository.getAll().forEach(book -> System.out.println(book.toString()));
  }

  public static void searchByKeyword() {
    String keyword = Main.getUserInput("Enter the Keyword: ");
    bookRepository.search(keyword).forEach(book -> System.out.println(book.toString()));
  }
}
