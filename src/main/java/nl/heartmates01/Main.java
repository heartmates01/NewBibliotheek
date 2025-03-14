package nl.heartmates01.main;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import nl.heartmates01.book.AuthorRepository;
import nl.heartmates01.book.BookController;
import nl.heartmates01.book.BookRepository;
import nl.heartmates01.magazine.CopyEditorController;
import nl.heartmates01.magazine.CopyEditorRepository;
import nl.heartmates01.magazine.MagazineController;
import nl.heartmates01.magazine.MagazineRepository;
import nl.heartmates01.book.AuthorController;
import nl.heartmates01.magazine.PublisherController;
import nl.heartmates01.magazine.PublisherRepository;

public class Main {

  public static final MagazineRepository magazineRepository = new MagazineRepository();
  public static final BookRepository bookRepository = new BookRepository();
  public static final AuthorRepository authorRepository = new AuthorRepository();
  public static final PublisherRepository publisherRepository = new PublisherRepository();
  public static final CopyEditorRepository copyEditorRepository = new CopyEditorRepository();

  static final Scanner scanner = new Scanner(System.in);

  static final List<Map<String, Runnable>> options = List.of(
      Map.of("Manage Books", BookController::showBookMenu),
      Map.of("Manage Authors", AuthorController::showAuthorMenu),
      Map.of("Manage Magazines", MagazineController::showMagazineMenu),
      Map.of("Manage Publishers", PublisherController::showPublisherMenu),
      Map.of("Manage Copy Editors", CopyEditorController::showCopyMenu),
      Map.of("Exit", () -> System.exit(0))
  );

  public static void main(String[] args) {
    // starts the application
    while (true) {
      System.out.println("Choose an option: ");
      // loops through options
      for (int i = 0; i < options.size(); i++) {
        System.out.println(i + ". " + options.get(i).keySet().iterator().next());
      }
      // gets users chosen option
      int option = Integer.parseInt(getUserInput("Enter the option's number: "));

      // handles users chosen option
      handleOption(option);
    }
  }

  // handles users chosen option
  private static void handleOption(int index) {
    options.get(index).values().forEach(Runnable::run);
  }

  public static String getUserInput(String question) {
    System.out.println(question);
    return scanner.nextLine();
  }

  public static String userInput(String question, Pattern pattern, String errorMessage) {
    System.out.println(question);

    if (pattern != null) {
      while (!scanner.hasNext(pattern)) {
        System.out.println(errorMessage);
        scanner.next();
      }
    }
    return scanner.nextLine();
  }
}
