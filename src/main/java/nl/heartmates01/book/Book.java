package nl.heartmates01.book;

// De class Book heeft de volgende members (velden)
// LETOP: Andere classes mogen niet direct bij deze members kunnen:

// id (long)
// title (String)
// author (String)
// pages (int)
// isbn (String)
// borrowed (boolean)

// De class Book heeft de volgende methods:

// long getId()

// boolean hasBeenBorrowed()
// Deze method geeft de waarde van borrowed terug.

// String getTitleWithAuthor()
// Deze method geeft de titel en de auteur van het boek terug als een String.

// String toString()
// Deze method geeft alle informatie van het boek terug als een String
// (tip: gebruik een String template """).'

// void borrowBook()
// Deze method zet de borrowed waarde op true.

// void returnBook()
// Deze method zet de borrowed waarde op false.

// + all under (String) toString()

// (long) id
// |_ (long) getId()

// (String) title
// |_ (String) getTitleWithAuthor()

// (String) author
// |_ (String) getTitleWithAuthor()

// (int) pages

// (String) isbn

// (boolean) borrowed
// |_ (boolean) hasBeenBorrowed()
// |_ (void) borrowBook()
// |_ (void) returnBook()

import java.time.LocalDate;
import nl.heartmates01.library.Item;

// overerft Item
public class Book extends Item {

  protected static int id;
  private static int count;
  private String title;
  private String author;
  private int pages;
  private boolean borrowed;
  private long isbn;
  protected static int borrowTime;
  private LocalDate publicationDate;

  Book(int id, String title, String author, int pages, boolean borrowed, long isbn, int borrowTime,
      LocalDate publicationDate) {
    super(id, title, pages, borrowed, borrowTime, publicationDate);

    Book.id = ++count;
    this.title = title;
    this.author = author;
    this.pages = pages;
    this.borrowed = borrowed;
    this.isbn = isbn;
    Book.borrowTime = 0;
    this.publicationDate = publicationDate;
  }

  // Voor Book: "Isbn: {isbn} - Title: {title} - Author: {author}"
  public String getOverviewText() {
    return "Isbn: " + isbn + "\nTitle :" + title + "\nAuthor: " + author;
  }

  long getId() {
    return id;
  }

  // Deze method geeft de waarde van borrowed terug.
  public boolean hasBeenBorrowed() {
    return borrowed;
  }

  // Deze method geeft de titel en de auteur van het boek terug als een String.
  public String getTitleWithAuthor() {
    return "\n" + title + "\n" + author + "\n";
  }

  // Deze method geeft alle informatie van het boek terug als een String
// (tip: gebruik een String template """).'
  public String toString() {
    return "\n" + "ID = " + id + "\n" + "Title = " + title + "\n" + "Author = " + author + "\n"
        + "Pages = " + pages + "\n" + "ISBN = " + isbn + "\n" + "Borrowed = " + borrowed + "\n"
        + "\n" + "Publication date = " + publicationDate;
  }

  public void borrow() {
    borrowed = true;
    borrowTime = 14;
    System.out.println("Book has been borrowed.");
  }

  public void returnn() {
    borrowed = false;
    borrowTime = 0;
    System.out.println("Book has been returned.");
  }
}
