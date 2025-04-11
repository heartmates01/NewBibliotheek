package nl.heartmates01.library;

import nl.heartmates01.main.Main;

public class ItemController {

  private static final ItemService itemService = new ItemService();

  //sonarqube recommended this
  private ItemController() {
    throw new IllegalStateException("Utility class ItemController; java:S1118");
  }

  public static void showAll() {
    itemService.showAll().forEach(item -> System.out.println(item.toString()));
  }

  public static void search() {
    String keyword = Main.getUserInput("Enter the Keyword: ");
    itemService.search(keyword).forEach(item -> System.out.println(item.toString()));
  }
}
