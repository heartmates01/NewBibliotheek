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

  private final long id;
  private final String title;
  private final String publisher;
  private final String copyEditor;
  private final int pages;
  private final String issn;
  private final int issueNumber;
  private final LocalDate publicationDate;

  protected Magazine(long id, String title, String publisher, String copyEditor, int pages,
      String issn, int issueNumber, LocalDate publicationDate) {
    super(id, title, pages, publicationDate);

    this.id = id;
    this.title = title;
    this.publisher = publisher;
    this.copyEditor = copyEditor;
    this.pages = pages;
    this.issn = issn;
    this.issueNumber = issueNumber;
    this.publicationDate = publicationDate;
  }


  public String getOverviewtext() {
    return "";
  }
}
