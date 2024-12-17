package nl.heartmates01.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BookRepository {

  // deze method maakt een nieuw Book object aan en voegt deze toe aan de books List.
  public static List<Book> allBooks = new ArrayList<>();

  void add(int id, String title, String author, int pages, boolean borrowed, int borrowTime,
      long isbn,
      LocalDate publicationDate) {
    allBooks.add(new Book(id, title, author, pages, borrowed, isbn, borrowTime, publicationDate));
  }

  // Deze method zoekt het boek op in de books List en verwijderd deze uit de List.
  void removeBook(int id) {
    Book foundBook = findID(id);
    if (foundBook != null) {
      allBooks.remove(foundBook);
    }
  }

  // zoekt id van book
  public Book findID(int id) {
    for (Book book : allBooks) {
      if (id == book.getId()) {
        return book;
      }
    }
    return null;
  }

  // methods zonder directe toegang tot Books

  List<Book> getAll() {
    return allBooks;
  }

  List<Book> getBorrowedBooks() {
    return allBooks.stream().filter(Book::hasBeenBorrowed).toList();
  }

  List<Book> getAvailableBooks() {
    return allBooks.stream().filter(Predicate.not(Book::hasBeenBorrowed)).toList();
  }
}
