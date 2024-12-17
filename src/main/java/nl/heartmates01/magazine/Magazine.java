package nl.heartmates01.magazine;

//- id (long)
//- title (String)
//- publisher (String)
//- copyEditor (String)
//- pages (int)
//- issn (String)
//- issueNumber (int)
//- publicationDate (LocalDate)

import java.time.LocalDate;
import nl.heartmates01.library.Item;

// overerft Item
public class Magazine extends Item {

  private int id;
  private static int count;
  private String title;
  private String publisher;
  private String copyEditor;
  private int pages;
  private boolean borrowed;
  protected static int borrowTime;
  private String issn;
  private int issueNumber;
  private LocalDate publicationDate;

  protected Magazine(int id, String title, String publisher, String copyEditor,
      int pages,
      boolean borrowed, int borrowTime,
      String issn, int issueNumber, LocalDate publicationDate) {
    super(id, title, pages, borrowed, borrowTime, publicationDate);

    this.id = ++count;
    this.title = title;
    this.publisher = publisher;
    this.copyEditor = copyEditor;
    this.pages = pages;
    this.issn = issn;
    this.issueNumber = issueNumber;
    this.publicationDate = publicationDate;
  }

  int getID() {
    return id;
  }

  public String getOverviewText() {
    return "\nISSN: " + issn + "\nTitle: " + title + "\nIssue: " + issueNumber;
  }

  public void borrow() {
    borrowed = true;
    borrowTime = 2;
    System.out.println("Magazine has been borrowed.");
  }

  public void returnn() {
    borrowed = false;
    borrowTime = 0;
    System.out.println("Magazine has been returned.");
  }

  public boolean hasBeenBorrowed() {
    return borrowed;
  }
}
