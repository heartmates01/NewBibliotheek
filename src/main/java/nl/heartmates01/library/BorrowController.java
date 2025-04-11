package nl.heartmates01.library;

import java.util.Scanner;
import java.util.regex.Pattern;

public class BorrowController {

  static final Scanner scanner = new Scanner(System.in);

  public void borrowOrReturn(Item item) {
    System.out.println("""
              
        0. Borrow
        1. Return
        2. Exit
                
        """);

    int borrowOrReturn = Integer.parseInt(
        userInput("Choose an option from the list.", Pattern.compile("[0-3]"),
            "Choose a valid option."));
    if (borrowOrReturn == 3) {
      System.out.println("Exiting.");
      System.exit(0);

    } else if (borrowOrReturn == 1) {
      BorrowController.borrow(item);

    } else if (borrowOrReturn == 2) {
      BorrowController.returnn(item);
    }
  }

  public static void borrow(Item item) {
    BorrowService.borrowItem(item);
    System.out.println("Borrow Succeeded (hopefully)");
  }

  public static void returnn(Item item) {
    BorrowService.returnItem(item);
    System.out.println("Return Succeeded (hopefully)");
  }


  public static String userInput(String question, Pattern pattern, String errorMessage) {
    System.out.println(question);

    if (pattern != null) {
      while (!scanner.hasNext(pattern)) {
        System.out.println(errorMessage);
        scanner.next();
      }
    }
    return scanner.nextLine();
  }
}
