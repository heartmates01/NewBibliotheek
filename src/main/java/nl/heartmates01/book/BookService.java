package nl.heartmates01.book;

import static nl.heartmates01.main.Main.bookRepository;

import java.util.List;

public class BookService {

  public List<Book> getAll() {
    return bookRepository.getAll();
  }

  public List<Book> search(String keyword) {
    return bookRepository.search(keyword);
  }
}
