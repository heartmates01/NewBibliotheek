package nl.heartmates01.book;

import static nl.heartmates01.main.Main.authorRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.heartmates01.main.JdbcSingleton;
import java.sql.ResultSet;

public class BookRepository {

  private final JdbcSingleton jdbcSingleton = JdbcSingleton.getInstance();

  // add
  public List<Book> add(Book... books) {

    if (books.length == 0) {
      return new ArrayList<>();
    }

    StringBuilder multipleInserts = new StringBuilder();
    List<Object> parameters = new ArrayList<>();
    String insertQueryString = "INSERT INTO books (id, title, authorId, pages, borrowed, isbn, publicationDate) VALUES (?, ?, ?, ?, ?, ?, ?)";

    int bookIndex = 0;
    for (Book book : books) {
      if (books.length > 1 && bookIndex == 0) {
        multipleInserts.append(",");
      }

      multipleInserts.append(insertQueryString);

      parameters.addAll(List.of(
          book.getId(),
          book.getTitle(),
          book.getAuthor().getId(),
          book.getPages(),
          book.getBorrowed(),
          book.getIsbn(),
          book.getPublicationDate()
      ));
      bookIndex++;

      List<Integer> ids;
      try {
        ids = jdbcSingleton.insertQuery(multipleInserts.toString(),
            parameters.toArray());
        for (int i = 1; i <= ids.size(); i++) {
          book.setId(ids.get(i));
        }
      } catch (SQLException e) {
        //
      }
    }

    return List.of(books);
  }

  // remove
  public int delete(int id) {
    int result = id;
    try {
      result = jdbcSingleton.deleteQuery("DELETE FROM books WHERE id = ?", id);
    } catch (SQLException e) {
      //
    }
    return result;
  }


  // get singular from id
  public Optional<Book> get(int id) {
    Optional<ResultSet> result = Optional.empty();
    try {
      result = jdbcSingleton.selectQuery("SELECT * FROM books WHERE id = ?", id);
    } catch (SQLException e) {
      //
    }
    if (result.isEmpty()) {
      return Optional.empty();
    }
    ResultSet resultSet = result.get();
    try {
      resultSet.next();
      return Optional.of(new Book(
          resultSet.getInt("id"),
          resultSet.getString("title"),
          authorRepository.get(resultSet.getInt("authorId")).get(),
          resultSet.getInt("pages"),
          resultSet.getBoolean("borrowed"),
          resultSet.getLong("isbn"),
          resultSet.getDate("publicationDate").toLocalDate()
      ));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  // get all
  public List<Book> getAll() {
    Optional<ResultSet> results = Optional.empty();
    try {
      results = jdbcSingleton.selectQuery("SELECT * FROM books");
    } catch (SQLException e) {
      //
    }

    if (results.isEmpty()) {
      return new ArrayList<>();
    }
    // converts to list of books
    ResultSet resultSet = results.get();
    List<Book> books = new ArrayList<>();
    try {
      while (resultSet.next()) {
        books.add(new Book(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            authorRepository.get(resultSet.getInt("authorId")).get(),
            resultSet.getInt("pages"),
            resultSet.getBoolean("borrowed"),
            resultSet.getLong("isbn"),
            resultSet.getDate("publicationDate").toLocalDate()
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return books;
  }

  // search all by author
  public Optional<List<Book>> search(int author) {
    Optional<ResultSet> results = Optional.empty();
    try {
      results = jdbcSingleton.selectQuery("SELECT * FROM books WHERE authorId = ?",
          author);
    } catch (SQLException e) {
      //
    }

    if (results.isEmpty()) {
      return Optional.empty();
    }
    ResultSet resultSet = results.get();
    List<Book> books = new ArrayList<>();
    try {
      while (resultSet.next()) {
        books.add(new Book(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            authorRepository.get(resultSet.getInt("authorId")).get(),
            resultSet.getInt("pages"),
            resultSet.getBoolean("borrowed"),
            resultSet.getLong("isbn"),
            resultSet.getDate("publicationDate").toLocalDate()
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.of(books);
  }
}
