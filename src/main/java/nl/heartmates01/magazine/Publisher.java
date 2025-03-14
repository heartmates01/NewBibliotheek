package nl.heartmates01.magazine;

import java.time.LocalDate;

public class Publisher {

  protected int pubId;
  private final String name;
  private final LocalDate dateOfBirth;

  Publisher(String name, LocalDate dateOfBirth) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
  }

  Publisher(int pubId, String name, LocalDate dateOfBirth) {
    this.pubId = pubId;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
  }

  String getPubInfo() {
    return "\nID: " + pubId + "\nName: " + name + "\nDate of Birth: " + dateOfBirth + "\n";
  }

  int getId() {
    return pubId;
  }

  void setPubId(int id) {
    this.pubId = id;
  }

  String getName() {
    return name;
  }

  LocalDate getDateOfBirth() {
    return dateOfBirth;
  }
}
