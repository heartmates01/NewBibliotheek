package nl.heartmates01.magazine;

import static nl.heartmates01.main.Main.copyEditorRepository;
import static nl.heartmates01.main.Main.magazineRepository;
import static nl.heartmates01.main.Main.publisherRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import nl.heartmates01.main.Main;

public class MagazineController {

  //sonarqube recommended this
  private MagazineController() {
    throw new IllegalStateException("Utility class MagazineController; java:S1118");
  }

  static final List<Map<String, Runnable>> magazineOptions = List.of(
      Map.of("Add a Magazine", MagazineController::addMag),
      Map.of("Remove a Magazine", MagazineController::removeMag),
      Map.of("List Singular Magazine", MagazineController::listSingular),
      Map.of("Borrow or Return Magazine", MagazineController::borrowOrReturn),
      Map.of("List All Magazines", MagazineController::listAllMags),
      Map.of("Search Magazines by Keyword", MagazineController::searchByKeyword),
      Map.of("Exit", () -> System.exit(0))
  );

  private static void handleMagOptions(int index) {
    magazineOptions.get(index).values().forEach(Runnable::run);
  }

  public static void showMagazineMenu() {
    while (true) {
      System.out.println("Choose an option: ");
      for (int i = 0; i < magazineOptions.size(); i++) {
        System.out.println(i + ". " + magazineOptions.get(i).keySet().iterator().next());
      }
      int option = Integer.parseInt(Main.getUserInput("Enter the option number: "));

      handleMagOptions(option);
    }
  }

  public static void addMag() {
    Map<String, Object> userInput = new MagazineForm().getMagInput();

    magazineRepository.add(new Magazine(
        (String) userInput.get("title"),
        (String) userInput.get("type"),
        publisherRepository.get((Integer) userInput.get("pubId")).get(),
        copyEditorRepository.get((Integer) userInput.get("copyId")).get(),
        (Integer) userInput.get("pages"),
        (Boolean) userInput.get("borrowed"),
        (Integer) userInput.get("issueNumber"),
        (LocalDate) userInput.get("publicationDate"),
        (Integer) userInput.get("issn")
    ));
    System.out.println(userInput.get("title") + " has been added");
  }

  public static void removeMag() {
    int id = Integer.parseInt(Main.getUserInput("Enter this magazine's ID: "));
    magazineRepository.delete(id);
    System.out.println("Magazine has been deleted.");
  }

  public static void listSingular() {
    int id = Integer.parseInt(Main.getUserInput("Enter this magazine's ID: "));
    magazineRepository.get(id)
        .ifPresentOrElse(magazine -> System.out.println(magazine.getOverviewText()),
            () -> System.out.println("Magazine not found."));
  }

  public static void borrowOrReturn() {
    System.out.println(
        "This will invert the boolean's value, so borrowed becomes returned & returned becomes borrowed.");
    int id = Integer.parseInt(Main.getUserInput("Enter this magazine's ID: "));
    magazineRepository.borrowOrReturnMag(id);
    System.out.println("Done!");
  }

  public static void listAllMags() {
    magazineRepository.getAll().forEach(magazine -> System.out.println(magazine.getOverviewText()));
  }

  public static void searchByKeyword() {
    String keyword = Main.getUserInput("Enter the Keyword: ");
    magazineRepository.search(keyword)
        .forEach(magazine -> System.out.println(magazine.getOverviewText()));
  }
}
