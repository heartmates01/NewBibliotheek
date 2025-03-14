package nl.heartmates01.book;

import java.time.LocalDate;

public class Author {

  protected int authorId;
  private final String name;
  private final LocalDate dateOfBirth;

  //without id for add
  Author(String name, LocalDate dateOfBirth) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
  }

  Author(int authorId, String name, LocalDate dateOfBirth) {
    this.authorId = authorId;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
  }

  public String getAuthorInfo() {
    return "\nID: " + authorId + "\nName: " + name + "\nDate of Birth: " + dateOfBirth + "\n";
  }

  int getId() {
    return authorId;
  }

  public void setId(int id) {
    this.authorId = id;
  }

  String getName() {
    return name;
  }

  LocalDate getDateOfBirth() {
    return dateOfBirth;
  }
}