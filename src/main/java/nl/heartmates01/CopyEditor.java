package nl.heartmates01.magazine;

import java.time.LocalDate;

public class CopyEditor {

  protected int copyId;
  private final String name;
  private final LocalDate dateOfBirth;

  CopyEditor(String name, LocalDate dateOfBirth) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
  }

  CopyEditor(int copyId, String name, LocalDate dateOfBirth) {
    this.copyId = copyId;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
  }

  String getCopyInfo() {
    return "\nID: " + copyId + "\nName: " + name + "\nDate of Birth: " + dateOfBirth + "\n";
  }

  int getId() {
    return copyId;
  }

  void setCopyId(int id) {
    this.copyId = id;
  }

  String getName() {
    return name;
  }

  LocalDate getDateOfBirth() {
    return dateOfBirth;
  }
}
