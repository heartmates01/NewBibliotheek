package nl.heartmates01.magazine;

import static nl.heartmates01.Main.userInput;
import static nl.heartmates01.Main.magazineRepository;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class MagazineController {

  public static void showMagazineMenu() {

    while (true) {
      System.out.println("""
           \s
           Library Management System
           \s
           1. Manage Daily Magazines
           2. Manage Weekly Magazines
           3. Manage Monthly Magazines
           4. Exit to main menu
          """);

      String userMagChoice = userInput("Choose an option from the list:",
          Pattern.compile("[0-4]"),
          "Choose a valid option");

      switch (userMagChoice) {
        case "1":
          handleDailyMags();
          break;

        case "2":
          handleWeeklyMags();
          break;

        case "3":
          handleMonthlyMags();
          break;

        case "4":
          System.out.println("Exiting to main menu.");
          return;
      }
    }
  }

  static void handleDailyMags() {
    while (true) {
      System.out.println("""
              \s
              Library Management System
              \s
              1. Add
              2. Remove
              3. Show
              4. Show All
              5. Borrow or Return
              6. Exit
          """);

      String userDailyChoice = userInput("Choose an option from the list:",
          Pattern.compile("[0-5]"),
          "Choose a valid option");

      switch (userDailyChoice) {
        case "1":
          addDailyMagazine();
          break;

        case "2":
          removeMagazine();
          break;

        case "3":
          long showID = Long.parseLong(
              userInput("Magazine ID:", Pattern.compile("\\d"), "Invalid ID."));
          showMagazine(showID);
          break;

        case "4":
          showAllDailyMagazines();
          break;

        case "5":
          borrowOrReturn();
          break;

        case "6":
          System.out.println("Exiting to previous menu.");
          return;
      }
    }
  }

  static void handleWeeklyMags() {
    while (true) {
      System.out.println("""
              \s
              Library Management System
              \s
              1. Add
              2. Remove
              3. Show
              5. Borrow or Return
              6. Exit
          """);

      String userWeeklyChoice = userInput("Choose an option from the list:",
          Pattern.compile("[0-5]"),
          "Choose a valid option");

      switch (userWeeklyChoice) {
        case "1":
          addWeeklyMagazine();
          break;

        case "2":
          removeMagazine();
          break;

        case "3":
          long showID = Long.parseLong(
              userInput("Magazine ID:", Pattern.compile("\\d"), "Invalid ID."));
          showMagazine(showID);
          break;

        case "4":
          showAllWeeklyMagazines();
          break;

        case "5":
          borrowOrReturn();
          break;

        case "6":
          System.out.println("Exiting to previous menu.");
          return;
      }
    }
  }


  static void handleMonthlyMags() {
    while (true) {
      System.out.println("""
              \s
              Library Management System
              \s
              1. Add
              2. Remove
              3. Show
              5. Borrow or Return
              6. Exit
          """);

      String userMonthlyChoice = userInput("Choose an option from the list:",
          Pattern.compile("[0-5]"),
          "Choose a valid option");

      switch (userMonthlyChoice) {
        case "1":
          addMonthlyMagazine();
          break;

        case "2":
          removeMagazine();
          break;

        case "3":
          long showID = Long.parseLong(
              userInput("Magazine ID:", Pattern.compile("\\d"), "Invalid ID."));
          showMagazine(showID);
          break;

        case "4":
          showAllMonthlyMagazines();
          break;

        case "5":
          borrowOrReturn();
          break;

        case "6":
          System.out.println("Exiting to previous menu.");
          return;
      }
    }
  }

  static void addDailyMagazine() {
    int dailyID = DailyMag.id;
    String dailyTitle = userInput("Magazine Title:", null, null);
    String dailyPublisher = userInput("Magazine Publisher:", null, null);
    String dailyCopyEditor = userInput("Magazine Copyeditor:", null, null);
    int dailyPages = Integer.parseInt(
        userInput("Number of Pages;", null, "Invalid number."));
    String dailyISSN = userInput("Magazine ISSN:", null, null);
    int dailyIssueNumber = Integer.parseInt(
        userInput("Magazine Issue Number (3 Int Max.):", Pattern.compile("\\d{0,3}"),
            "Invalid issue number."));
    LocalDate dailyPubDate = LocalDate.parse(userInput("Date of Publication(YYYY-MM-DD):",
        Pattern.compile("\\d{4}-\\d{2}-\\d{2}"),
        "Invalid date."));
    boolean dailyBorrowed = Boolean.parseBoolean(
        userInput("Currently being Borrowed(T/F): ", null, null));
    magazineRepository.addDailyMag(dailyID, dailyTitle, dailyPublisher, dailyCopyEditor, dailyPages,
        dailyBorrowed,
        dailyISSN, dailyIssueNumber, dailyPubDate);
    System.out.println("This magazine's assigned ID is " + dailyID);
  }

  static void addWeeklyMagazine() {
    int weeklyID = WeeklyMag.id;
    String weeklyTitle = userInput("Magazine Title:", null, null);
    String weeklyPublisher = userInput("Magazine Publisher:", null, null);
    String weeklyCopyEditor = userInput("Magazine Copyeditor:", null, null);
    int weeklyPages = Integer.parseInt(
        userInput("Number of Pages;", null, "Invalid number."));
    String weeklyISSN = userInput("Magazine ISSN:", null, null);
    int weeklyIssueNumber = Integer.parseInt(
        userInput("Magazine Issue/Week Number (3 Int Max.):", Pattern.compile("\\d{0,3}"),
            "Invalid issue/week number."));
    LocalDate weeklyPubDate = LocalDate.parse(userInput("Date of Publication(YYYY-MM-DD):",
        Pattern.compile("\\d{4}-\\d{2}-\\d{2}"),
        "Invalid date."));
    boolean weeklyBorrowed = Boolean.parseBoolean(
        userInput("Currently being Borrowed(T/F): ", null, null));
    magazineRepository.addWeeklyMag(weeklyID, weeklyTitle, weeklyPublisher, weeklyCopyEditor,
        weeklyPages, weeklyBorrowed,
        weeklyISSN, weeklyIssueNumber, weeklyPubDate);
    System.out.println("This magazine's assigned ID is " + weeklyID);
  }

  static void addMonthlyMagazine() {
    int monthlyID = MonthlyMag.id;
    String monthlyTitle = userInput("Magazine Title:", null, null);
    String monthlyPublisher = userInput("Magazine Publisher:", null, null);
    String monthlyCopyEditor = userInput("Magazine Copyeditor:", null, null);
    int monthlyPages = Integer.parseInt(
        userInput("Number of Pages;", null, "Invalid number."));
    String monthlyISSN = userInput("Magazine ISSN:", null, null);
    int monthlyIssueNumber = Integer.parseInt(
        userInput("Magazine Issue/Month Number (3 Int Max.):", Pattern.compile("\\d{0,3}"),
            "Invalid issue/month number."));
    LocalDate monthlyPubDate = LocalDate.parse(userInput("Date of Publication(YYYY-MM-DD):",
        Pattern.compile("\\d{4}-\\d{2}-\\d{2}"),
        "Invalid date."));
    boolean monthlyBorrowed = Boolean.parseBoolean(
        userInput("Currently being Borrowed(T/F): ", null, null));
    magazineRepository.addMonthlyMag(monthlyID, monthlyTitle, monthlyPublisher, monthlyCopyEditor,
        monthlyPages, monthlyBorrowed,
        monthlyISSN, monthlyIssueNumber, monthlyPubDate);
    System.out.println("This magazine's assigned ID is " + monthlyID);
  }

  static void showAllDailyMagazines() {
    String result = "";
    for (Magazine magazine : magazineRepository.getDailyMags()) {
      result += magazine.getOverviewText();
    }
    System.out.println(result);
  }

  static void showAllWeeklyMagazines() {
    String result = "";
    for (Magazine magazine : magazineRepository.getWeeklyMags()) {
      result += magazine.getOverviewText();
    }
    System.out.println(result);
  }

  static void showAllMonthlyMagazines() {
    String result = "";
    for (Magazine magazine : magazineRepository.getMonthlyMags()) {
      result += magazine.getOverviewText();
    }
    System.out.println(result);
  }

  static void removeMagazine() {
    long id = Long.parseLong(
        userInput("Magazine ID:", Pattern.compile("\\d"), "Invalid ID."));
    magazineRepository.removeMag(id);
    System.out.println("Magazine removed from list.");
  }

  static void showMagazine(long id) {
    Magazine magazine = magazineRepository.findID(id);
    if (magazine != null) {
      System.out.println(magazine.getOverviewText());
    } else {
      System.out.println("Magazine not in list.");
    }
  }

  static void borrowOrReturn() {
    long id = Long.parseLong(
        userInput("Magazine ID:", Pattern.compile("\\d"), "Invalid ID."));
    System.out.println("""
        \s
        Library Management System
        \s
        1. Borrow
        2. Return
        3. Exit to previous menu""");

    int borrowOrReturn = Integer.parseInt(
        userInput("Choose an option from the list.", Pattern.compile("[0-3]"),
            "Choose a valid option."));
    if (borrowOrReturn == 3) {
      System.out.println("Exiting to previous menu.");
      handleDailyMags();

    } else if (borrowOrReturn == 1) {
      borrowMag(id);

    } else if (borrowOrReturn == 2) {
      returnMag(id);
    }
  }

  static void borrowMag(long id) {
    Magazine magazine = magazineRepository.findID(id);
    if (magazine != null) {
      magazine.borrowMagazine();
      System.out.println("Magazine has been borrowed for 2 days.");
    } else {
      System.out.println("Magazine not in list.");
    }
  }

  static void returnMag(long id) {
    Magazine magazine = magazineRepository.findID(id);
    if (magazine != null) {
      magazine.returnMagazine();
      System.out.println("Magazine has been returned.");
    } else {
      System.out.println("Magazine not in list.");
    }
  }
}
