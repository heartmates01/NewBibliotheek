package nl.heartmates01.magazine;

import static nl.heartmates01.main.Main.publisherRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import nl.heartmates01.main.Main;

public class PublisherController {

  static final List<Map<String, Runnable>> publisherOptions = List.of(
      Map.of("Add a Publisher", PublisherController::addPublisher),
      Map.of("Remove a Publisher", PublisherController::removePublisher),
      Map.of("List Singular Publisher", PublisherController::listSingular),
      Map.of("List All Publishers", PublisherController::listAll),
      Map.of("Exit", () -> System.exit(0))
  );

  private static void handlePublisherOptions(int index) {
    publisherOptions.get(index).values().forEach(Runnable::run);
  }

  public static void showPublisherMenu() {
    while (true) {
      System.out.println("Choose an option: ");
      for (int i = 0; i < publisherOptions.size(); i++) {
        System.out.println(i + ". " + publisherOptions.get(i).keySet().iterator().next());
      }
      int option = Integer.parseInt(Main.getUserInput("Enter the option number: "));
      handlePublisherOptions(option);
    }
  }

  public static void addPublisher() {
    Map<String, Object> userInput = new MagazineForm().getPubInput();

    publisherRepository.add(new Publisher(
        (String) userInput.get("name"),
        (LocalDate) userInput.get("dateOfBirth")
    ));
    System.out.println(userInput.get("name") + " has been added.");
  }

  public static void removePublisher() {
    int id = Integer.parseInt(Main.getUserInput("Enter this publisher's ID: "));
    publisherRepository.delete(id);
    System.out.println("Publisher has been deleted.");
  }

  public static void listSingular() {
    int id = Integer.parseInt(Main.getUserInput("Enter this publisher's ID: "));
    publisherRepository.get(id)
        .ifPresentOrElse(publisher -> System.out.println(publisher.getPubInfo()),
            () -> System.out.println("Could not find publisher."));
  }

  public static void listAll() {
    publisherRepository.getAll().forEach(publisher -> System.out.println(publisher.getPubInfo()));
  }
}
