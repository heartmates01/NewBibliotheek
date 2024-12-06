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
          removeDailyMagazine();
          break;

        case "3":
          long showDailyID = Long.parseLong(
              userInput("Magazine ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
          showDailyMagazine(showDailyID);
          break;

        case "4":
          showAllDailyMagazines();
          break;

        case "5":
          borrowOrReturnDaily();
          break;
        case "6":
          System.out.println("Exiting to previous menu.");
          return;
      }
    }
  }

  //addDailyMagazine
  //removeDailyMagazine
  //showDailyMagazine
  //showAllDailyMagazines

  static void addDailyMagazine() {
    long dailyID = Long.parseLong(
        userInput("Magazine ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
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
  }

  static void removeDailyMagazine() {
    long id = Long.parseLong(
        userInput("Magazine ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
    magazineRepository.removeDailyMag(id);
    System.out.println("Magazine removed from list.");
  }

  static void showDailyMagazine(long id) {
    Magazine magazine = magazineRepository.findDailyID(id);
    if (magazine != null) {
      System.out.println(magazine.getOverviewText());
    } else {
      System.out.println("Magazine not in list.");
    }
  }

  static void showAllDailyMagazines() {
    String result = "";
    for (Magazine magazine : magazineRepository.getDailyMags()) {
      result += magazine.getOverviewText();
    }
    System.out.println(result);
  }

  static void borrowOrReturnDaily() {
    long dailyID = Long.parseLong(
        userInput("Magazine ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
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
      borrowDailyMag(dailyID);

    } else if (borrowOrReturn == 2) {
      returnDailyMag(dailyID);
    }
  }

  static void borrowDailyMag(long id) {
    Magazine magazine = magazineRepository.findDailyID(id);
    if (magazine != null) {
      magazine.borrowMagazine();
      System.out.println("Magazine has been borrowed for 2 days.");
    } else {
      System.out.println("Magazine not in list.");
    }
  }

  static void returnDailyMag(long id) {
    Magazine magazine = magazineRepository.findDailyID(id);
    if (magazine != null) {
      magazine.returnMagazine();
      System.out.println("Magazine has been returned.");
    } else {
      System.out.println("Magazine not in list.");
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
          removeWeeklyMagazine();
          break;
        case "3":
          long showWeeklyID = Long.parseLong(
              userInput("Magazine ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
          showWeeklyMagazine(showWeeklyID);
          break;
        case "4":
          showAllWeeklyMagazines();
          break;
        case "5":
          borrowOrReturnWeekly();
          break;
        case "6":
          System.out.println("Exiting to previous menu.");
          return;
      }
    }
  }

  //addWeeklyMagazine
  //removeWeeklyMagazine
  //showWeeklyMagazine
  //showAllWeeklyMagazines

  static void addWeeklyMagazine() {
    long weeklyID = Long.parseLong(
        userInput("Magazine ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
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
  }

  static void removeWeeklyMagazine() {
    long id = Long.parseLong(
        userInput("Magazine ID:", Pattern.compile("\\d{2}"), "Invalid ID."));
    magazineRepository.removeWeeklyMag(id);
    System.out.println("Magazine removed from list.");
  }

  static void showWeeklyMagazine(long id) {
    Magazine magazine = magazineRepository.findWeeklyID(id);
    if (magazine != null) {
      System.out.println(magazine.getOverviewText());
    } else {
      System.out.println("Magazine not in list.");
    }
  }

  static void showAllWeeklyMagazines() {
    String result = "";
    for (Magazine magazine : magazineRepository.getWeeklyMags()) {
      result += magazine.getOverviewText();
    }
    System.out.println(result);
  }

  static void borrowOrReturnWeekly() {
    long weeklyID = Long.parseLong(
        userInput("Magazine ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
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
      MagazineController.handleWeeklyMags();

    } else if (borrowOrReturn == 1) {
      borrowWeeklyMag(weeklyID);

    } else if (borrowOrReturn == 2) {
      returnWeeklyMag(weeklyID);
    }
  }

  static void borrowWeeklyMag(long id) {
    Magazine magazine = magazineRepository.findWeeklyID(id);
    if (magazine != null) {
      magazine.borrowMagazine();
      System.out.println("Magazine has been borrowed for 5 days.");
    } else {
      System.out.println("Magazine not in list.");
    }
  }

  static void returnWeeklyMag(long id) {
    Magazine magazine = magazineRepository.findWeeklyID(id);
    if (magazine != null) {
      magazine.returnMagazine();
      System.out.println("Magazine has been returned.");
    } else {
      System.out.println("Magazine not in list.");
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
          removeMonthlyMagazine();
          break;
        case "3":
          long showMonthlyID = Long.parseLong(
              userInput("Magazine ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
          showMonthlyMagazine(showMonthlyID);
          break;
        case "4":
          showAllMonthlyMagazines();
          break;
        case "5":
          borrowOrReturnMonthly();
          break;
        case "6":
          System.out.println("Exiting to previous menu.");
          return;
      }
    }
  }
  // addMonthlyMagazine
  // removeMonthlyMagazine
  // showMonthlyMagazine
  // showAllMonthlyMagazines

  static void addMonthlyMagazine() {
    long monthlyID = Long.parseLong(
        userInput("Magazine ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
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
  }

  static void removeMonthlyMagazine() {
    long id = Long.parseLong(
        userInput("Magazine ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
    magazineRepository.removeMonthlyMag(id);
    System.out.println("Magazine removed from list.");
  }

  static void showMonthlyMagazine(long id) {
    Magazine magazine = magazineRepository.findMonthlyID(id);
    if (magazine != null) {
      System.out.println(magazine.getOverviewText());
    } else {
      System.out.println("Magazine not in list.");
    }
  }

  static void showAllMonthlyMagazines() {
    String result = "";
    for (Magazine magazine : magazineRepository.getMonthlyMags()) {
      result += magazine.getOverviewText();
    }
    System.out.println(result);
  }

  static void borrowOrReturnMonthly() {
    long monthlyID = Long.parseLong(
        userInput("Magazine ID:", Pattern.compile("\\d{10}"), "Invalid ID."));
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
      MagazineController.handleMonthlyMags();

    } else if (borrowOrReturn == 1) {
      borrowMonthlyMag(monthlyID);

    } else if (borrowOrReturn == 2) {
      returnMonthlyMag(monthlyID);
    }
  }

  static void borrowMonthlyMag(long id) {
    Magazine magazine = magazineRepository.findMonthlyID(id);
    if (magazine != null) {
      magazine.borrowMagazine();
      System.out.println("Magazine has been borrowed for 7 days.");
    } else {
      System.out.println("Magazine not in list.");
    }
  }

  static void returnMonthlyMag(long id) {
    Magazine magazine = magazineRepository.findMonthlyID(id);
    if (magazine != null) {
      magazine.returnMagazine();
      System.out.println("Magazine has been returned.");
    } else {
      System.out.println("Magazine not in list.");
    }
  }
}
