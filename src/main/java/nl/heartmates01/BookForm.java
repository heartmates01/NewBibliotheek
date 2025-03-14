package nl.heartmates01.book;

import java.time.LocalDate;
import java.util.Map;
import nl.heartmates01.main.Main;

public class BookForm {

  public Map<String, Object> getUserInput() {

    return Map.of(
        "title", Main.getUserInput("Enter the book's title: "),
        "authorId", Integer.parseInt(Main.getUserInput("Enter this book's author ID: ")),
        "pages", Integer.parseInt(Main.getUserInput("Enter the book's number of pages: ")),
        "borrowed",
        Boolean.parseBoolean(Main.getUserInput("Enter if this book's being borrowed (T/F): ")),
        "isbn", Long.parseLong(Main.getUserInput("Enter this book's ISBN (13 CHR): ")),
        "publicationDate",
        LocalDate.parse(Main.getUserInput("Enter this book's publication date (YYYY-MM-DD): "))
    );
  }

  public Map<String, Object> getAuthorInput() {
    return Map.of(
        "name", Main.getUserInput("Enter the author's name: "),
        "dateOfBirth",
        LocalDate.parse(Main.getUserInput("Enter the author's date of birth (YYYY-MM-DD): "))
    );
  }
}