package nl.heartmates01.magazine;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.management.ObjectInstance;

public class MagazineRepository {

  // dailymag


  static List<Magazine> allMags = new ArrayList<>();

  void addDailyMag(int id, String title, String publisher, String copyEditor, int pages,
      boolean borrowed,
      String issn, int issueNumber, LocalDate publicationDate) {
    allMags.add(
        new DailyMag(id, title, publisher, copyEditor, pages, borrowed, issn, issueNumber,
            publicationDate));
  }

  List<Magazine> getDailyMags() {
    return allMags.stream().filter(DailyMag.class::isInstance).toList();
  }

  // weeklymag

  void addWeeklyMag(int id, String title, String publisher, String copyEditor, int pages,
      boolean borrowed,
      String issn, int issueNumber, LocalDate publicationDate) {
    allMags.add(
        new WeeklyMag(id, title, publisher, copyEditor, pages, borrowed, issn, issueNumber,
            publicationDate));
  }

  List<Magazine> getWeeklyMags() {
    return allMags.stream().filter(WeeklyMag.class::isInstance).toList();
  }

  // monthlymag

  void addMonthlyMag(int id, String title, String publisher, String copyEditor, int pages,
      boolean borrowed,
      String issn, int issueNumber, LocalDate publicationDate) {
    allMags.add(
        new MonthlyMag(id, title, publisher, copyEditor, pages, borrowed, issn, issueNumber,
            publicationDate));
  }

  List<Magazine> getMonthlyMags() {
    return allMags.stream().filter(MonthlyMag.class::isInstance).toList();
  }

  Magazine findID(long id) {
    for (Magazine magazine : allMags) {
      if (id == magazine.getID()) {
        return magazine;
      }
    }
    return null;
  }

  void removeMag(long id) {
    Magazine foundMag = findID(id);
    if (foundMag != null) {
      allMags.remove(foundMag);
    }
  }
}
