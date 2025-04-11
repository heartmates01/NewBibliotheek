package nl.heartmates01.library;

import java.time.LocalDate;

public abstract class Item {

  protected Item(String title,
      LocalDate publicationDate, Boolean borrowed) {
  }

  public abstract String getOverviewText();

  protected void borrow() {
  }

  protected void returnn() {
  }
}
