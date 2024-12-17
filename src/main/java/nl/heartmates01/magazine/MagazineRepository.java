package nl.heartmates01.magazine;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

public class MagazineRepository {

  // dailymag

  public static List<Magazine> allMags = new ArrayList<>();

  void addDailyMag(int id, String title, String publisher, String copyEditor, int pages,
      boolean borrowed, int borrowTime,
      String issn, int issueNumber, LocalDate publicationDate) {
    allMags.add(
        new DailyMag(id, title, publisher, copyEditor, pages, borrowed, borrowTime, issn,
            issueNumber,
            publicationDate));
  }

  List<Magazine> getDailyMags() {
    return allMags.stream().filter(DailyMag.class::isInstance).toList();
  }

  // weeklymag

  void addWeeklyMag(int id, String title, String publisher, String copyEditor, int pages,
      boolean borrowed, int borrowTime,
      String issn, int issueNumber, LocalDate publicationDate) {
    allMags.add(
        new WeeklyMag(id, title, publisher, copyEditor, pages, borrowed, borrowTime, issn,
            issueNumber,
            publicationDate));
  }

  List<Magazine> getWeeklyMags() {
    return allMags.stream().filter(WeeklyMag.class::isInstance).toList();
  }

  // monthlymag

  void addMonthlyMag(int id, String title, String publisher, String copyEditor, int pages,
      boolean borrowed, int borrowTime,
      String issn, int issueNumber, LocalDate publicationDate) {
    allMags.add(
        new MonthlyMag(id, title, publisher, copyEditor, pages, borrowed, borrowTime, issn,
            issueNumber,
            publicationDate));
  }

  List<Magazine> getMonthlyMags() {
    return allMags.stream().filter(MonthlyMag.class::isInstance).toList();
  }

  public Magazine findID(int id) {
    for (Magazine magazine : allMags) {
      if (id == magazine.getID()) {
        return magazine;
      }
    }
    return null;
  }

  void removeMag(int id) {
    Magazine foundMag = findID(id);
    if (foundMag != null) {
      allMags.remove(foundMag);
    }
  }

  List<Magazine> getAll() {
    return allMags;
  }

  List<Magazine> getBorrowedMags() {
    return allMags.stream().filter(Magazine::hasBeenBorrowed).toList();
  }

  List<Magazine> getAvailableMags() {
    return allMags.stream().filter(Predicate.not(Magazine::hasBeenBorrowed)).toList();
  }
}
