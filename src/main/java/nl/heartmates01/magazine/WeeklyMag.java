package nl.heartmates01.magazine;

// Weekly Magazine
//- id (long)
//- title (String)
//- publisher (String)
//- copyEditor (String)
//- pages (int)
//- issn (String)
//- issueNumber (int)
//- publicationDate (LocalDate)
//
//---- Methods
//- int getWeekNumber()

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class WeeklyMag extends Magazine {

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

  protected WeeklyMag(int id, String title, String publisher, String copyEditor,
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

  // https://www.baeldung.com/java-get-week-number
  public int getWeekNumber() {
    return publicationDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
  }

  // Voor WeeklyMagazine: "Issn: {issn} - Title: {title} - Week: {weekNumber}"
  public String getOverviewText() {
    return "\nIssn: " + issn + "\nTitle: " + title + "\nWeek: " + getWeekNumber() + "\nBorrowed: "
        + borrowed;
  }
}
