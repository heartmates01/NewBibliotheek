package nl.heartmates01.magazine;

// Monthly Magazine
//- id (long)
//- title (String)
//- publisher (String)
//- copyEditor (String)
//- pages (int)
//- issn (String)
//- publicationDate (LocalDate)
//- issueNumber (int)
//
//---- Methods
//- int getMonthNumber()

import java.time.LocalDate;

public class MonthlyMag extends Magazine {

  private final long id;
  private final String title;
  private final String publisher;
  private final String copyEditor;
  private final int pages;
  private final String issn;
  private final int issueNumber;
  private final LocalDate publicationDate;

  protected MonthlyMag(long id, String title, String publisher, String copyEditor, int pages,
      String issn, int issueNumber, LocalDate publicationDate) {
    super(id, title, publisher, copyEditor, pages, issn, issueNumber, publicationDate);

    this.id = id;
    this.title = title;
    this.publisher = publisher;
    this.copyEditor = copyEditor;
    this.pages = pages;
    this.issn = issn;
    this.issueNumber = issueNumber;
    this.publicationDate = publicationDate;
  }

  public int getMonthNumber() {
    return 0;
  }

  // Voor MonthlyMagazine: "Issn: {issn} - Title: {title} - Month: {monthNumber}"
  public String getOverviewtext() {
    return "Issn:" + issn + "Title:" + title + "Issue:" + issueNumber + "Month:" + getMonthNumber();
  }
}
