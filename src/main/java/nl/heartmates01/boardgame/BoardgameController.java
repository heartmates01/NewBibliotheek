package nl.heartmates01.boardgame;

import static nl.heartmates01.main.Main.boardgameRepository;
import static nl.heartmates01.main.Main.publisherRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import nl.heartmates01.main.Main;

public class BoardgameController {

  //sonarcube recommended this
  private BoardgameController() {
    throw new IllegalStateException("Utility class BoardController; java:S1118");
  }

  static final List<Map<String, Runnable>> gameOptions = List.of(
      Map.of("Add a Boardgame", BoardgameController::addGame),
      Map.of("Remove a Boardgame", BoardgameController::removeGame),
      Map.of("List Singular Boardgame", BoardgameController::listSingular),
      Map.of("Borrow or Return Boardgame", BoardgameController::borrowOrReturn),
      Map.of("List All Boardgames", BoardgameController::listAll),
      Map.of("Search Boardgames by Keyword", BoardgameController::searchByKeyword),
      Map.of("Exit", () -> System.exit(0))
  );

  private static void handleGameOptions(int index) {
    gameOptions.get(index).values().forEach(Runnable::run);
  }

  public static void showGameMenu() {
    while (true) {
      System.out.println("Choose an option: ");
      for (int i = 0; i < gameOptions.size(); i++) {
        System.out.println(i + ". " + gameOptions.get(i).keySet().iterator().next());
      }
      int option = Integer.parseInt(Main.getUserInput("Enter the option number: "));

      handleGameOptions(option);
    }
  }

  public static void addGame() {
    Map<String, Object> userInput = new BoardgameForm().getGameInput();

    boardgameRepository.add(new Boardgame(
        (String) userInput.get("title"),
        (String) userInput.get("designer"),
        (String) userInput.get("ean"),
        publisherRepository.get((Integer) userInput.get("pubId")).get(),
        (LocalDate) userInput.get("publicationDate"),
        (Integer) userInput.get("minPlayers"),
        (Integer) userInput.get("maxPlayers"),
        (Boolean) userInput.get("borrowed")
    ));
    System.out.println("Boardgame has been added.");
  }

  public static void removeGame() {
    int id = Integer.parseInt(Main.getUserInput("Enter this boardgame's ID: "));
    boardgameRepository.delete(id);
    System.out.println("Boardgame has been deleted.");
  }

  public static void listSingular() {
    int id = Integer.parseInt(Main.getUserInput("Enter this boardgame's ID: "));
    boardgameRepository.get(id)
        .ifPresentOrElse(boardgame -> System.out.println(boardgame.getOverviewText()),
            () -> System.out.println("Boardgame not found."));
  }

  public static void borrowOrReturn() {
    System.out.println(
        "This will invert the boolean's value, so borrowed becomes returned & returned becomes borrowed.");
    int id = Integer.parseInt(Main.getUserInput("Enter this boardgame's ID: "));
    boardgameRepository.borrowOrReturn(id);
    System.out.println("Done!");
  }

  public static void listAll() {
    boardgameRepository.getAll()
        .forEach(boardgame -> System.out.println(boardgame.getOverviewText()));
  }

  public static void searchByKeyword() {
    String keyword = Main.getUserInput("Enter the keyword: ");
    boardgameRepository.search(keyword)
        .forEach(boardgame -> System.out.println(boardgame.getOverviewText()));
  }
}
