package nl.heartmates01.book;

import static nl.heartmates01.main.Main.authorRepository;
import static nl.heartmates01.main.Main.bookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import nl.heartmates01.main.Main;

public class BookController {

  static final List<Map<String, Runnable>> bookOptions = List.of(
      Map.of("Add a Book", BookController::addBook),
      Map.of("Remove a Book", BookController::removeBook),
      Map.of("List singular Book", BookController::getFromId),
      Map.of("List all Books", BookController::listAllBooks),
      Map.of("Search Books by Author", BookController::searchByAuthor),
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
        (LocalDate) userInput.get("publicationDate")
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
    bookRepository.get(id).ifPresentOrElse(book -> System.out.println(book.getOverviewText()),
        () -> System.out.println("Book not found."));
  }

  public static void listAllBooks() {
    bookRepository.getAll().forEach(book -> System.out.println(book.toString()));
  }

  public static void searchByAuthor() {
    int author = Integer.parseInt(Main.getUserInput("Enter the author's ID: "));
    bookRepository.search(author)
        .ifPresentOrElse(books -> books.forEach(book -> System.out.println(book.getOverviewText())),
            () -> System.out.println("Book not found."));
  }
}
