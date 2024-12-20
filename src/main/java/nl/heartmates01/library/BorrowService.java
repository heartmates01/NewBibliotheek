package nl.heartmates01.library;

// Maak een class BorrowService.
// Deze class is verantwoordelijk voor het terugbrengen en uitlenen van alle items.
// In plaats van dat de BookController en andere controllers dit doen, gaat het nu via de BorrowService.

import static nl.heartmates01.Main.userInput;

import java.util.regex.Pattern;

public class BorrowService {

  public static void borrowItem(Item item) {
    item.borrow();
  }

  public static void returnItem(Item item) {
    item.returnn();
  }

  public static void borrowOrReturn(Item item) {
    System.out.println("""
        \s
        Library Management System
        \s
        1. Borrow
        2. Return
        3. Exit to magazine menu""");

    int borrowOrReturn = Integer.parseInt(
        userInput("Choose an option from the list.", Pattern.compile("[0-3]"),
            "Choose a valid option."));
    if (borrowOrReturn == 3) {
      System.out.println("Exiting to magazine menu.");
      return;

    } else if (borrowOrReturn == 1) {
      BorrowService.borrowItem(item);

    } else if (borrowOrReturn == 2) {
      BorrowService.returnItem(item);
    }
  }
}
