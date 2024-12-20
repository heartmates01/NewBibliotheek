package nl.heartmates01.magazine

import static nl.heartmates01.Main.userInput;
import static nl.heartmates01.Main.magazineRepository;

import java.time.LocalDate;
import java.util.regex.Pattern;
import nl.heartmates01.library.BorrowService;

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
           4. Manage All Magazines
           5. Exit to main menu
          """);

      String userMagChoice = userInput("Choose an option from the list:",
          Pattern.compile("[0-5]"),
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
          handleAllMags();

        case "5":
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
              4. Show All Daily
              5. Borrow or Return
              6. Exit
          """);

      String userChoice = userInput("Choose an option from the list:",
          Pattern.compile("[0-6]"),
          "Choose a valid option");

      switch (userChoice) {
        case "1":
          addDailyMagazine();
          break;

        case "2":
          removeMagazine();
          break;

        case "3":
          int showID = Integer.parseInt(
              userInput("Magazine ID:", Pattern.compile("\\d+"), "Invalid ID."));
          showMagazine(showID);
          break;

        case "4":
          showAllDailyMagazines();
          break;

        case "5":
          int id = Integer.parseInt(
              userInput("Magazine ID:", Pattern.compile("\\d+"), "Invalid ID."));
          DailyMag magazine = (DailyMag) magazineRepository.findID(id);
          BorrowService.borrowOrReturn(magazine);
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
              4. Show All Weekly
              5. Borrow or Return
              6. Exit
          """);

      String userChoice = userInput("Choose an option from the list:",
          Pattern.compile("[0-6]"),
          "Choose a valid option");

      switch (userChoice) {
        case "1":
          addWeeklyMagazine();
          break;

        case "2":
          removeMagazine();
          break;

        case "3":
          int showID = Integer.parseInt(
              userInput("Magazine ID:", Pattern.compile("\\d+"), "Invalid ID."));
          showMagazine(showID);
          break;

        case "4":
          showAllWeeklyMagazines();
          break;

        case "5":
          int id = Integer.parseInt(
              userInput("Magazine ID: ", Pattern.compile("\\d+"), "Invalid ID."));
          WeeklyMag magazine = (WeeklyMag) magazineRepository.findID(id);
          BorrowService.borrowOrReturn(magazine);
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
              4. Show All Monthly
              5. Borrow or Return
              6. Exit
          """);

      String userChoice = userInput("Choose an option from the list:",
          Pattern.compile("[0-6]"),
          "Choose a valid option");

      switch (userChoice) {
        case "1":
          addMonthlyMagazine();
          break;

        case "2":
          removeMagazine();
          break;

        case "3":
          int showID = Integer.parseInt(
              userInput("Magazine ID:", Pattern.compile("\\d+"), "Invalid ID."));
          showMagazine(showID);
          break;

        case "4":
          showAllMonthlyMagazines();
          break;

        case "5":
          int id = Integer.parseInt(
              userInput("Magazine ID:", Pattern.compile("\\d+"), "Invalid ID."));
          MonthlyMag magazine = (MonthlyMag) magazineRepository.findID(id);
          BorrowService.borrowOrReturn(magazine);
          break;

        case "6":
          System.out.println("Exiting to previous menu.");
          return;
      }
    }
  }

  static void handleAllMags() {
    while (true) {
      System.out.println("""
              \s
              Library Management System
              \s
              1. Show All
              2. Show All Borrowed
              3. Show All Available
              4. Exit
          """);

      String userChoice = userInput("Choose an option from the list:",
          Pattern.compile("[0-4]"),
          "Choose a valid option");

      switch (userChoice) {
        case "1":
          showMags();
          break;
        case "2":
          showBorrowedMags();
          break;
        case "3":
          showAvailableMags();
          break;
        case "4":
          System.out.println("Exiting.");
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
        userInput("Number of Pages;", Pattern.compile("\\d+"), "Invalid input."));
    String dailyISSN = userInput("Magazine ISSN:", null, null);
    int dailyIssueNumber = Integer.parseInt(
        userInput("Magazine Issue Number (3 Int Max.):", Pattern.compile("\\d{0,3}"),
            "Invalid issue number."));
    LocalDate dailyPubDate = LocalDate.parse(userInput("Date of Publication(YYYY-MM-DD):",
        Pattern.compile("\\d{4}-\\d{2}-\\d{2}"),
        "Invalid date."));
    boolean dailyBorrowed = Boolean.parseBoolean(
        userInput("Currently being Borrowed(T/F): ", Pattern.compile("true|True|false|False"),
            "Invalid input."));
    int dailyBorrowTime = MonthlyMag.borrowTime;
    magazineRepository.addDailyMag(dailyID, dailyTitle, dailyPublisher, dailyCopyEditor, dailyPages,
        dailyBorrowed, dailyBorrowTime,
        dailyISSN, dailyIssueNumber, dailyPubDate);
    System.out.println("This magazine's assigned ID is: " + dailyID + 1);
  }

  static void addWeeklyMagazine() {
    int weeklyID = WeeklyMag.id;
    String weeklyTitle = userInput("Magazine Title:", null, null);
    String weeklyPublisher = userInput("Magazine Publisher:", null, null);
    String weeklyCopyEditor = userInput("Magazine Copyeditor:", null, null);
    int weeklyPages = Integer.parseInt(
        userInput("Number of Pages;", Pattern.compile("\\d+"), "Invalid input."));
    String weeklyISSN = userInput("Magazine ISSN:", null, null);
    int weeklyIssueNumber = Integer.parseInt(
        userInput("Magazine Issue/Week Number (3 Int Max.):", Pattern.compile("\\d{0,3}"),
            "Invalid issue/week number."));
    LocalDate weeklyPubDate = LocalDate.parse(userInput("Date of Publication(YYYY-MM-DD):",
        Pattern.compile("\\d{4}-\\d{2}-\\d{2}"),
        "Invalid date."));
    boolean weeklyBorrowed = Boolean.parseBoolean(
        userInput("Currently being Borrowed(T/F): ", Pattern.compile("true|True|false|False"),
            "Invalid input."));
    int weeklyBorrowTime = WeeklyMag.borrowTime;
    magazineRepository.addWeeklyMag(weeklyID, weeklyTitle, weeklyPublisher, weeklyCopyEditor,
        weeklyPages, weeklyBorrowed, weeklyBorrowTime,
        weeklyISSN, weeklyIssueNumber, weeklyPubDate);
    System.out.println("This magazine's assigned ID is: " + weeklyID + 1);
  }

  static void addMonthlyMagazine() {
    int monthlyID = MonthlyMag.id;
    String monthlyTitle = userInput("Magazine Title:", null, null);
    String monthlyPublisher = userInput("Magazine Publisher:", null, null);
    String monthlyCopyEditor = userInput("Magazine Copyeditor:", null, null);
    int monthlyPages = Integer.parseInt(
        userInput("Number of Pages;", Pattern.compile("\\d+"), "Invalid input."));
    String monthlyISSN = userInput("Magazine ISSN:", null, null);
    int monthlyIssueNumber = Integer.parseInt(
        userInput("Magazine Issue/Month Number (3 Int Max.):", Pattern.compile("\\d{0,3}"),
            "Invalid issue/month number."));
    LocalDate monthlyPubDate = LocalDate.parse(userInput("Date of Publication(YYYY-MM-DD):",
        Pattern.compile("\\d{4}-\\d{2}-\\d{2}"),
        "Invalid input."));
    boolean monthlyBorrowed = Boolean.parseBoolean(
        userInput("Currently being Borrowed(T/F): ", Pattern.compile("true|True|false|False"),
            "Invalid answer."));
    int monthlyTime = MonthlyMag.borrowTime;
    magazineRepository.addMonthlyMag(monthlyID, monthlyTitle, monthlyPublisher, monthlyCopyEditor,
        monthlyPages, monthlyBorrowed, monthlyTime,
        monthlyISSN, monthlyIssueNumber, monthlyPubDate);
    System.out.println("This magazine's assigned ID is: " + monthlyID + 1);
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
    int id = Integer.parseInt(
        userInput("Magazine ID:", Pattern.compile("\\d+"), "Invalid ID."));
    magazineRepository.removeMag(id);
    System.out.println("Magazine removed from list.");
  }

  static void showMagazine(int id) {
    Magazine magazine = magazineRepository.findID(id);
    if (magazine != null) {
      System.out.println(magazine.getOverviewText());
    } else {
      System.out.println("Magazine not in list.");
    }
  }

  static void showMags() {
    String result = "";
    for (Magazine magazine : magazineRepository.getAll()) {
      System.out.println(result + magazine.getOverviewText());
    }
  }

  static void showBorrowedMags() {
    String result = "";
    for (Magazine magazine : magazineRepository.getBorrowedMags()) {
      System.out.println(result + magazine.getOverviewText());
    }
  }

  static void showAvailableMags() {
    String result = "";
    for (Magazine magazine : magazineRepository.getAvailableMags()) {
      System.out.println(result + magazine.getOverviewText());
    }
  }
}
