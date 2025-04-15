package nl.heartmates01.book;

import java.time.LocalDate;
import nl.heartmates01.library.Item;

public class Book extends Item {

  protected int id;
  private final String title;
  private final Author author;
  private final int pages;
  private boolean borrowed;
  private final long isbn;
  protected int borrowTime;
  private final LocalDate publicationDate;

  // for bookform
  Book(String title, Author author, int pages, boolean borrowed, long isbn,
      LocalDate publicationDate) {
    super(title, pages, borrowed, publicationDate);
    this.title = title;
    this.author = author;
    this.pages = pages;
    this.borrowed = borrowed;
    this.isbn = isbn;
    this.publicationDate = publicationDate;
  }

  Book(int id, String title, Author author, int pages, boolean borrowed, long isbn,
      LocalDate publicationDate) {
    super(title, pages, borrowed, publicationDate);
    this.id = id;
    this.title = title;
    this.author = author;
    this.pages = pages;
    this.borrowed = borrowed;
    this.isbn = isbn;
    this.publicationDate = publicationDate;
  }

  public String getOverviewText() {
    return "\nIsbn: " + isbn + "\nTitle : " + title + "\nAuthor: " + author.getName() + "\n";
  }
  
  int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  String getTitle() {
    return title;
  }

  Author getAuthor() {
    return author;
  }

  int getPages() {
    return pages;
  }

  boolean getBorrowed() {
    return borrowed;
  }

  long getIsbn() {
    return isbn;
  }

  LocalDate getPublicationDate() {
    return publicationDate;
  }

  public boolean hasBeenBorrowed() {
    return borrowed;
  }

  public String getTitleWithAuthor() {
    return "\n" + title + "\n" + author.getName() + "\n";
  }
  
  public String toString() {
    return "\n" + "ID = " + id + "\n" + "Title = " + title + "\n" + "Author = " + author.getName()
        + "\n"
        + "Pages = " + pages + "\n" + "ISBN = " + isbn + "\n" + "Borrowed = " + borrowed + "\n"
        + "Publication Date = " + publicationDate + "\n";
  }
}
