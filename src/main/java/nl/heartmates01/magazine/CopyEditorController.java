package nl.heartmates01.magazine;

import static nl.heartmates01.main.Main.copyEditorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import nl.heartmates01.main.Main;

public class CopyEditorController {
  
  static final List<Map<String, Runnable>> copyEditorOptions = List.of(
      Map.of("Add a Copy Editor", CopyEditorController::addCopyEditor),
      Map.of("Remove a Copy Editor", CopyEditorController::removeCopyEditor),
      Map.of("List Singular Copy Editor", CopyEditorController::listSingular),
      Map.of("List All Copy Editors", CopyEditorController::listAll),
      Map.of("Exit", () -> System.exit(0))
  );

  private static void handleCopyOptions(int index) {
    copyEditorOptions.get(index).values().forEach(Runnable::run);
  }

  public static void showCopyMenu() {
    while (true) {
      System.out.println("Choose an option: ");
      for (int i = 0; i < copyEditorOptions.size(); i++) {
        System.out.println(i + ". " + copyEditorOptions.get(i).keySet().iterator().next());
      }
      int option = Integer.parseInt(Main.getUserInput("Enter the option number: "));
      handleCopyOptions(option);
    }
  }

  public static void addCopyEditor() {
    Map<String, Object> userInput = new MagazineForm().getCopyInput();

    copyEditorRepository.add(new CopyEditor(
        (String) userInput.get("name"),
        (LocalDate) userInput.get("dateOfBirth")
    ));
    System.out.println(userInput.get("name") + " has been added.");
  }

  public static void removeCopyEditor() {
    int id = Integer.parseInt(Main.getUserInput("Enter this copy editor's ID: "));
    copyEditorRepository.delete(id);
    System.out.println("Copy Editor has been deleted.");
  }

  public static void listSingular() {
    int id = Integer.parseInt(Main.getUserInput("Enter this copy editor's ID: "));
    copyEditorRepository.get(id)
        .ifPresentOrElse(copyEditor -> System.out.println(copyEditor.getCopyInfo()),
            () -> System.out.println("Could not find copy editor."));
  }

  public static void listAll() {
    copyEditorRepository.getAll()
        .forEach(copyEditor -> System.out.println(copyEditor.getCopyInfo()));
  }
}
