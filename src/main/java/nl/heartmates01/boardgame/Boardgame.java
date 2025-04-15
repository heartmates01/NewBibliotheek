package nl.heartmates01.boardgame;

import java.time.LocalDate;
import nl.heartmates01.library.Item;
import nl.heartmates01.magazine.Publisher;

public class Boardgame extends Item {

  protected int id;
  private final String title;
  private final String designer;
  private final String ean;
  private final Publisher publisher;
  private final LocalDate publicationDate;
  private final int minPlayers;
  private final int maxPlayers;
  private boolean borrowed;

  //without id for boardgameform
  Boardgame(String title, String designer, String ean, Publisher publisher,
      LocalDate publicationDate, int minPlayers,
      int maxPlayers, boolean borrowed) {
    super(title, publicationDate, borrowed);
    this.title = title;
    this.designer = designer;
    this.ean = ean;
    this.publisher = publisher;
    this.publicationDate = publicationDate;
    this.minPlayers = minPlayers;
    this.maxPlayers = maxPlayers;
  }

  //with id for add
  Boardgame(int id, String title, String designer, String ean, Publisher publisher,
      LocalDate publicationDate, int minPlayers,
      int maxPlayers, boolean borrowed) {
    super(title, publicationDate, borrowed);
    this.id = id;
    this.title = title;
    this.designer = designer;
    this.ean = ean;
    this.publisher = publisher;
    this.publicationDate = publicationDate;
    this.minPlayers = minPlayers;
    this.maxPlayers = maxPlayers;
  }

  public String getOverviewText() {
    return "\nID: " + id + "\nTitle: " + title + "\nDesigner: " + designer + "\nPublisher: "
        + publisher.getName() + "\nPublication Date: " + publicationDate + "\nEAN: " + ean
        + "\nMin. Players: " + minPlayers + "\nMax. Players: " + maxPlayers;
  }

  public String toString() {
    return "\nTitle: " + title + "\nDesigner: " + designer + "\nPublisher: "
        + publisher.getName() + "\nPublication Date: " + publicationDate + "\nEAN: " + ean + "\n";
  }

  int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  String getTitle() {
    return title;
  }

  String getDesigner() {
    return designer;
  }

  String getEan() {
    return ean;
  }

  Publisher getPublisher() {
    return publisher;
  }

  LocalDate getPublicationDate() {
    return publicationDate;
  }

  int getMinPlayers() {
    return minPlayers;
  }

  int getMaxPlayers() {
    return maxPlayers;
  }

  boolean getBorrowed() {
    return borrowed;
  }
}
