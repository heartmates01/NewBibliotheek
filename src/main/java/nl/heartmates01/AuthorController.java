package nl.heartmates01.book;

import static nl.heartmates01.main.Main.authorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import nl.heartmates01.main.Main;

public class AuthorController {

  static final List<Map<String, Runnable>> authorOptions = List.of(
      Map.of("Add an Author", AuthorController::addAuthor),
      Map.of("Remove an Author", AuthorController::removeAuthor),
      Map.of("List Singular Author", AuthorController::listSingular),
      Map.of("List All Authors", AuthorController::listAll),
      Map.of("Exit", () -> System.exit(0)));

  private static void handleAuthorOptions(int index) {
    authorOptions.get(index).values().forEach(Runnable::run);
  }

  public static void showAuthorMenu() {
    while (true) {
      System.out.println("Choose an option: ");
      for (int i = 0; i < authorOptions.size(); i++) {
        System.out.println(i + ". " + authorOptions.get(i).keySet().iterator().next());
      }
      int option = Integer.parseInt(Main.getUserInput("Enter the option number: "));
      handleAuthorOptions(option);
    }
  }

  public static void addAuthor() {
    Map<String, Object> userInput = new BookForm().getAuthorInput();

    authorRepository.add(new Author(
        (String) userInput.get("name"),
        (LocalDate) userInput.get("dateOfBirth")
    ));
    System.out.println(userInput.get("name") + " has been added.");
  }

  public static void removeAuthor() {
    int id = Integer.parseInt(Main.getUserInput("Enter this author's ID: "));
    authorRepository.delete(id);
    System.out.println("Author has been deleted.");
  }

  public static void listSingular() {
    int id = Integer.parseInt(Main.getUserInput("Enter this author's ID: "));
    authorRepository.get(id).ifPresentOrElse(author -> System.out.println(author.getAuthorInfo()),
        () -> System.out.println("Could not find author."));
  }

  public static void listAll() {
    authorRepository.getAll().forEach(author -> System.out.println(author.getAuthorInfo()));
  }
}