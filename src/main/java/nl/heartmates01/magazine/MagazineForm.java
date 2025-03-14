package nl.heartmates01.magazine;

import java.time.LocalDate;
import java.util.Map;
import nl.heartmates01.main.Main;

public class MagazineForm {

  //magazine
  public Map<String, Object> getMagInput() {
    return Map.of(
        "title", Main.getUserInput("Enter the magazine's title: "),
        "type", Main.getUserInput("Enter the magazine's type (daily, weekly, monthly): "),
        "pubId", Integer.parseInt(Main.getUserInput("Enter this magazine's publisher ID: ")),
        "copyId", Integer.parseInt(Main.getUserInput("Enter this magazine's copy editor ID: ")),
        "pages", Integer.parseInt(Main.getUserInput("Enter this magazine's number of pages: ")),
        "borrowed",
        Boolean.parseBoolean(Main.getUserInput("Enter if this magazine's being borrowed (T/F): ")),
        "publicationDate",
        LocalDate.parse(Main.getUserInput("Enter this magazine's publication date (YYYY-MM-DD): ")),
        "issueNumber",
        Integer.parseInt(Main.getUserInput("Enter how many times this magazine's been issued: ")),
        "issn", Integer.parseInt(Main.getUserInput("Enter this magazine's ISSN: "))
    );
  }

  //publisher
  public Map<String, Object> getPubInput() {
    return Map.of(
        "name", Main.getUserInput("Enter the publisher's name: "),
        "dateOfBirth",
        LocalDate.parse(Main.getUserInput("Enter the publisher's date of birth (YYYY-MM-DD): "))
    );
  }

  //copyeditor
  public Map<String, Object> getCopyInput() {
    return Map.of(
        "name", Main.getUserInput("Enter the copy editor's name: "),
        "dateOfBirth",
        LocalDate.parse(Main.getUserInput("Enter the copy editor's date of birth (YYYY-MM-DD): "))
    );
  }
}
