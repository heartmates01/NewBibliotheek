package nl.heartmates01.book;

import static nl.heartmates01.main.Main.authorRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class AuthorRepositoryTest {

  @Test
  void add() {
    List<Author> authors = authorRepository.add(new Author(
        10,
        "name",
        LocalDate.of(2025, 4, 22)
    ));
    authors.forEach(author -> assertNotNull(authorRepository.get(author.getId())));
  }

  @Test
  void delete() {
    int id = 10;
    authorRepository.delete(id);
    assert (authorRepository.get(id).isEmpty());
  }

  @Test
  void get() {
    int id = 10;
    authorRepository.get(id);
    assertNotNull(authorRepository.get(id));
  }

  @Test
  void getAll() {
    authorRepository.getAll().forEach(author -> System.out.println(author.getAuthorInfo()));
    assertNotNull(authorRepository.getAll());
  }
}
