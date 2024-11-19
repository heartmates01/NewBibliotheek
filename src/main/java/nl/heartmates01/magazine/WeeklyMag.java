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

public class WeeklyMag extends Magazine {

  private final long id;
  private final String title;
  private final String publisher;
  private final String copyEditor;
  private final int pages;
  private final String issn;
  private final int issueNumber;
  private final LocalDate publicationDate;

  protected WeeklyMag(long id, String title, String publisher, String copyEditor, int pages,
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

  public int getWeekNumber() {
    return issueNumber;
  }

  // Voor WeeklyMagazine: "Issn: {issn} - Title: {title} - Week: {weekNumber}"
  public String getOverviewText() {
    return "Issn: " + issn + "\nTitle: " + title + "\nWeek: " + getWeekNumber();
  }
}

  // Voor WeeklyMagazine: "Issn: {issn} - Title: {title} - Week: {weekNumber}"
  public String getOverviewtext() {
    return "Issn:" + issn + "Title:" + title + "Week:" + getWeekNumber();
  }
}
