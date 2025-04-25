package nl.heartmates01.book;

import static nl.heartmates01.main.Main.authorRepository;
import static nl.heartmates01.main.Main.bookRepository;
import static nl.heartmates01.main.Main.publisherRepository;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class BookRepositoryTest {

  @Test
  void add() {

    List<Book> books = bookRepository.add(new Book(
        10,
        "test",
        authorRepository.getAll().getFirst(),
        200,
        false,
        132L,
        LocalDate.of(2025, 2, 11),
        publisherRepository.getAll().getFirst()
    ));

    books.forEach(b -> assertNotNull(bookRepository.get(b.getId())));
  }

  @Test
  void delete() {
    int id = 10;
    bookRepository.delete(id);
    assert (bookRepository.get(id).isEmpty());
  }

  @Test
  void get() {
    int id = 10;
    bookRepository.get(id);
    assertNotNull(bookRepository.get(id));
  }

  @Test
  void borrowOrReturn() {
    int id = 10;
    bookRepository.borrowOrReturn(id);
    //dont know which assert to use
  }

  @Test
  void getAll() {
    bookRepository.getAll().forEach(book -> System.out.println(book.toString()));
    assertNotNull(bookRepository.getAll());
  }

  @Test
  void search() {
    String keyword = "the";
    bookRepository.search(keyword).forEach(book -> System.out.println(book.toString()));
    //dont know which assert to use
  }
}
