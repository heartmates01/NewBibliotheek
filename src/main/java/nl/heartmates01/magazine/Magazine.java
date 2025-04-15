package nl.heartmates01.magazine;

import java.time.LocalDate;
import nl.heartmates01.library.Item;

public class Magazine extends Item {

  protected int id;
  private final String title;
  private final String type;
  private final Publisher publisher;
  private final CopyEditor copyEditor;
  protected int pages;
  private final boolean borrowed;
  private final int issueNumber;
  private final LocalDate publicationDate;
  private final int issn;

  Magazine(int id, String title, String type, Publisher publisher, CopyEditor copyEditor,
      int pages,
      boolean borrowed, int issueNumber, LocalDate publicationDate, int issn) {
    super(title, pages, borrowed, publicationDate);

    this.id = id;
    this.title = title;
    this.type = type;
    this.publisher = publisher;
    this.copyEditor = copyEditor;
    this.pages = pages;
    this.borrowed = borrowed;
    this.issueNumber = issueNumber;
    this.publicationDate = publicationDate;
    this.issn = issn;
  }

  Magazine(String title, String type, Publisher publisher, CopyEditor copyEditor, int pages,
      boolean borrowed, int issueNumber, LocalDate publicationDate, int issn) {
    super(title, pages, borrowed, publicationDate);

    this.title = title;
    this.type = type;
    this.publisher = publisher;
    this.copyEditor = copyEditor;
    this.pages = pages;
    this.borrowed = borrowed;
    this.issueNumber = issueNumber;
    this.publicationDate = publicationDate;
    this.issn = issn;
  }

  public String getOverviewText() {
    return "\nISSN: " + issn + "\nTitle: " + title + "\nType: " + type + "\nIssue: " + issueNumber
        + "\n";
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

  String getType() {
    return type;
  }

  Publisher getPublisher() {
    return publisher;
  }

  CopyEditor getCopyEditor() {
    return copyEditor;
  }

  int getPages() {
    return pages;
  }

  int getIssn() {
    return issn;
  }

  int getIssueNumber() {
    return issueNumber;
  }

  LocalDate getPublicationDate() {
    return publicationDate;
  }

  public boolean hasBeenBorrowed() {
    return borrowed;
  }
}
