package nl.heartmates01.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.heartmates01.main.JdbcSingleton;

public class AuthorRepository {

  private final JdbcSingleton jdbcSingleton = JdbcSingleton.getInstance();

  public List<Author> add(Author... authors) {
    if (authors.length == 0) {
      return new ArrayList<>();
    }

    StringBuilder multipleInserts = new StringBuilder();
    List<Object> parameters = new ArrayList<>();
    String insertQueryString = "INSERT INTO authors (authorId, name, dateOfBirth) VALUES (?, ?, ?)";

    int authorIndex = 0;
    for (Author author : authors) {
      if (authors.length > 1 && authorIndex == 0) {
        multipleInserts.append(",");
      }

      multipleInserts.append(insertQueryString);

      parameters.addAll(List.of(
          author.getId(),
          author.getName(),
          author.getDateOfBirth()
      ));
      authorIndex++;

      List<Integer> ids;
      try {
        ids = jdbcSingleton.insertQuery(multipleInserts.toString(),
            parameters.toArray());
        for (int i = 1; i <= ids.size(); i++) {
          author.setId(ids.get(i));
        }
      } catch (SQLException e) {
        //
      }
    }
    return List.of(authors);
  }

  public int delete(int id) {
    int result = id;
    try {
      result = jdbcSingleton.deleteQuery("DELETE FROM authors WHERE authorId = ?", id);
    } catch (SQLException e) {
      //
    }
    return result;
  }

  public Optional<Author> get(int id) {
    Optional<ResultSet> result = Optional.empty();
    try {
      result = jdbcSingleton.selectQuery("SELECT * FROM authors WHERE authorId = ?", id);
    } catch (SQLException e) {
      //
    }
    if (result.isEmpty()) {
      return Optional.empty();
    }
    ResultSet resultSet = result.get();
    try {
      resultSet.next();
      return Optional.of(new Author(
          resultSet.getInt("authorId"),
          resultSet.getString("name"),
          resultSet.getDate("dateOfBirth").toLocalDate()
      ));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  public List<Author> getAll() {
    Optional<ResultSet> results = Optional.empty();
    try {
      results = jdbcSingleton.selectQuery("SELECT * FROM authors");
    } catch (SQLException e) {
      //
    }
    if (results.isEmpty()) {
      return new ArrayList<>();
    }
    ResultSet resultSet = results.get();
    List<Author> authors = new ArrayList<>();
    try {
      while (resultSet.next()) {
        authors.add(new Author(
            resultSet.getInt("authorId"),
            resultSet.getString("name"),
            resultSet.getDate("dateOfBirth").toLocalDate()
        ));
      }
    } catch (SQLException e) {
      //
    }
    return authors;
  }
}
