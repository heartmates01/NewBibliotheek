package nl.heartmates01.library;

//acts as repository
public class BorrowService {

  //sonarqube recommended this
  private BorrowService() {
    throw new IllegalStateException("Utility class BorrowService; java:S1118");
  }

  public static void borrowItem(Item item) {
    item.borrow();
  }

  public static void returnItem(Item item) {
    item.returnn();
  }
}
