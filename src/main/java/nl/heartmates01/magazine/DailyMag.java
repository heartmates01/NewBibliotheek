package nl.heartmates01.magazine;

// Daily Magazine
//- id (long)
//- title (String)
//- publisher (String)
//- copyEditor (String)
//- pages (int)
//- issn (String)
//- issueNumber (int)
//- publicationDate (LocalDate)

import java.time.LocalDate;

class DailyMag extends Magazine {

  protected static int id;
  private static int count;
  private final String title;
  private final String publisher;
  private final String copyEditor;
  private final int pages;
  private boolean borrowed;
  protected static int borrowTime;
  private final String issn;
  private final int issueNumber;
  private final LocalDate publicationDate;

  protected DailyMag(int id, String title, String publisher, String copyEditor,
      int pages,
      boolean borrowed, int borrowTime,
      String issn, int issueNumber, LocalDate publicationDate) {
    super(id, title, publisher, copyEditor, pages, borrowed, borrowTime, issn, issueNumber,
        publicationDate);

    DailyMag.id = ++count;
    this.title = title;
    this.publisher = publisher;
    this.copyEditor = copyEditor;
    this.pages = pages;
    this.borrowed = borrowed;
    DailyMag.borrowTime = 0;
    this.issn = issn;
    this.issueNumber = issueNumber;
    this.publicationDate = publicationDate;
  }

  // Voor DailyMagazine: "Issn: {issn} - Title: {title} - Issue: {issueNumber}"
  public String getOverviewText() {
    return "\nIssn: " + issn + "\nTitle: " + title + "\nIssue: " + issueNumber + "\nBorrowed: "
        + borrowed;
  }

  public void borrow() {
    borrowed = true;
    borrowTime = 2;
    System.out.println("Magazine has been borrowed for 2 days.");
  }

  public void returnn() {
    borrowed = false;
    borrowTime = 0;
    System.out.println("Magazine has been returned.");
  }
}
