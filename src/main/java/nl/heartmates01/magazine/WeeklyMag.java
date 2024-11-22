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

  private final long id;
  private final String title;
  private final String publisher;
  private final String copyEditor;
  private final int pages;
  private boolean borrowed;
  private final String issn;
  private final int issueNumber;
  private final LocalDate publicationDate;

  protected WeeklyMag(long id, String title, String publisher, String copyEditor, int pages,
      boolean borrowed,
      String issn, int issueNumber, LocalDate publicationDate) {
    super(id, title, publisher, copyEditor, pages, borrowed, issn, issueNumber, publicationDate);

    this.id = id;
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
