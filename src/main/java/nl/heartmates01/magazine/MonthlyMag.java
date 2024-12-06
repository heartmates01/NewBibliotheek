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

  protected static int id;
  private static int count;
  private String title;
  private String publisher;
  private String copyEditor;
  private int pages;
  private boolean borrowed;
  private String issn;
  private int issueNumber;
  private LocalDate publicationDate;

  protected MonthlyMag(int id, String title, String publisher, String copyEditor,
      int pages,
      boolean borrowed,
      String issn, int issueNumber, LocalDate publicationDate) {
    super(id, title, publisher, copyEditor, pages, borrowed, issn, issueNumber,
        publicationDate);

    this.id = ++count;
    this.title = title;
    this.publisher = publisher;
    this.copyEditor = copyEditor;
    this.pages = pages;
    this.borrowed = borrowed;
    this.issn = issn;
    this.issueNumber = issueNumber;
    this.publicationDate = publicationDate;
  }

  public int getMonthNumber() {
    return publicationDate.getMonthValue();
  }

  // Voor MonthlyMagazine: "Issn: {issn} - Title: {title} - Month: {monthNumber}"
  public String getOverviewText() {
    return "\nIssn: " + issn + "\nTitle: " + title + "\nIssue: " + issueNumber + "\nMonth: "
        + getMonthNumber() + "\nBorrowed: " + borrowed;
  }
}
