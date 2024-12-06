package nl.heartmates01.magazine;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class MagazineRepository {

  // dailymag
  
  static List<Magazine> allDailyMags = new ArrayList<>();

  void addDailyMag(long id, String title, String publisher, String copyEditor, int pages,
      boolean borrowed,
      String issn, int issueNumber, LocalDate publicationDate) {
    allDailyMags.add(
        new DailyMag(id, title, publisher, copyEditor, pages, borrowed, issn, issueNumber,
            publicationDate));
  }

  Magazine findDailyID(long id) {
    for (Magazine magazine : allDailyMags) {
      if (id == magazine.getID()) {
        return magazine;
      }
    }
    return null;
  }

  void removeDailyMag(long id) {
    Magazine foundMag = findDailyID(id);
    if (foundMag != null) {
      allDailyMags.remove(foundMag);
    }
  }

  List<Magazine> getDailyMags() {
    return allDailyMags;
  }

  // weeklymag

  List<Magazine> allWeeklyMags = new ArrayList<>();

  void addWeeklyMag(long id, String title, String publisher, String copyEditor, int pages,
      boolean borrowed,
      String issn, int issueNumber, LocalDate publicationDate) {
    allWeeklyMags.add(
        new WeeklyMag(id, title, publisher, copyEditor, pages, borrowed, issn, issueNumber,
            publicationDate));
  }

  Magazine findWeeklyID(long id) {
    for (Magazine magazine : allWeeklyMags) {
      if (id == magazine.getID()) {
        return magazine;
      }
    }
    return null;
  }

  void removeWeeklyMag(long id) {
    Magazine foundMag = findWeeklyID(id);
    if (foundMag != null) {
      allWeeklyMags.remove(foundMag);
    }
  }

  List<Magazine> getWeeklyMags() {
    return allWeeklyMags;
  }

  // monthlymag

  List<Magazine> allMonthlyMags = new ArrayList<>();

  void addMonthlyMag(long id, String title, String publisher, String copyEditor, int pages,
      boolean borrowed,
      String issn, int issueNumber, LocalDate publicationDate) {
    allMonthlyMags.add(
        new MonthlyMag(id, title, publisher, copyEditor, pages, borrowed, issn, issueNumber,
            publicationDate));
  }

  Magazine findMonthlyID(long id) {
    for (Magazine magazine : allMonthlyMags) {
      if (id == magazine.getID()) {
        return magazine;
      }
    }
    return null;
  }

  void removeMonthlyMag(long id) {
    Magazine foundMag = findMonthlyID(id);
    if (foundMag != null) {
      allMonthlyMags.remove(foundMag);
    }
  }

  List<Magazine> getMonthlyMags() {
    return allMonthlyMags;
  }
}
