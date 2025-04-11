package nl.heartmates01.magazine;

import static nl.heartmates01.main.Main.publisherRepository;
import static nl.heartmates01.main.Main.copyEditorRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.heartmates01.main.JdbcSingleton;

public class MagazineRepository {

  private final JdbcSingleton jdbcSingleton = JdbcSingleton.getInstance();

  public List<Magazine> add(Magazine... magazines) {

    if (magazines.length == 0) {
      return new ArrayList<>();
    }

    StringBuilder multipleInserts = new StringBuilder();
    List<Object> parameters = new ArrayList<>();
    String insertQueryString = "INSERT INTO magazines (id, pubId, type, copyId, pages, title, borrowed, publicationDate, issueNumber, issn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    int magIndex = 0;
    for (Magazine magazine : magazines) {
      if (magazines.length > 1 && magIndex == 0) {
        multipleInserts.append(",");
      }
      multipleInserts.append(insertQueryString);

      parameters.addAll(List.of(
          magazine.getId(),
          magazine.getPublisher().getId(),
          magazine.getType(),
          magazine.getCopyEditor().getId(),
          magazine.getPages(),
          magazine.getTitle(),
          magazine.getBorrowed(),
          magazine.getPublicationDate(),
          magazine.getIssueNumber(),
          magazine.getIssn()
      ));
      magIndex++;

      List<Integer> ids;
      try {
        ids = jdbcSingleton.insertQuery(multipleInserts.toString(), parameters.toArray());

        for (int i = 1; i <= ids.size(); i++) {
          magazine.setId(ids.get(i));
        }

      } catch (SQLException e) {
        //
      }
    }
    return List.of(magazines);
  }

  public int delete(int id) {
    int result = id;
    try {
      result = jdbcSingleton.deleteQuery("DELETE FROM magazines WHERE id = ?", id);
    } catch (SQLException e) {
      //
    }
    return result;
  }

  public Optional<Magazine> get(int id) {
    Optional<ResultSet> result = Optional.empty();
    try {
      result = jdbcSingleton.selectQuery("SELECT * FROM magazines WHERE id = ?", id);
    } catch (SQLException e) {
      //
    }
    if (result.isEmpty()) {
      return Optional.empty();
    }
    ResultSet resultSet = result.get();
    try {
      resultSet.next();
      return Optional.of(new Magazine(
          resultSet.getInt("id"),
          resultSet.getString("title"),
          resultSet.getString("type"),
          publisherRepository.get(resultSet.getInt("pubId")).get(),
          copyEditorRepository.get(resultSet.getInt("copyId")).get(),
          resultSet.getInt("pages"),
          resultSet.getBoolean("borrowed"),
          resultSet.getInt("issueNumber"),
          resultSet.getDate("publicationDate").toLocalDate(),
          resultSet.getInt("issn")
      ));
    } catch (SQLException e) {
      //
    }
    return Optional.empty();
  }

  public List<Magazine> getAll() {
    Optional<ResultSet> results = Optional.empty();
    try {
      results = jdbcSingleton.selectQuery("SELECT * FROM magazines");
    } catch (SQLException e) {
      //
    }

    if (results.isEmpty()) {
      return new ArrayList<>();
    }
    // converts to list of books
    ResultSet resultSet = results.get();
    List<Magazine> magazines = new ArrayList<>();
    try {
      while (resultSet.next()) {
        magazines.add(new Magazine(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getString("type"),
            publisherRepository.get(resultSet.getInt("pubId")).get(),
            copyEditorRepository.get(resultSet.getInt("copyId")).get(),
            resultSet.getInt("pages"),
            resultSet.getBoolean("borrowed"),
            resultSet.getInt("issueNumber"),
            resultSet.getDate("publicationDate").toLocalDate(),
            resultSet.getInt("issn")
        ));
      }
    } catch (SQLException e) {
      //
    }
    return magazines;
  }

  public List<Magazine> search(String keyword) {
    Optional<ResultSet> results = Optional.empty();
    try {
      results = jdbcSingleton.selectQuery(
          "SELECT magazines.* FROM magazines LEFT JOIN publishers ON publishers.pubId = magazines.pubId WHERE title LIKE CONCAT ('%', ?, '%') OR publishers.name LIKE CONCAT ('%', ? '%')",
          keyword, keyword);
    } catch (SQLException e) {
      //
    }

    if (results.isEmpty()) {
      System.out.println("No results found for Magazines.");
      return new ArrayList<>();
    }
    ResultSet resultSet = results.get();
    List<Magazine> magazines = new ArrayList<>();
    try {
      while (resultSet.next()) {
        magazines.add(new Magazine(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getString("type"),
            publisherRepository.get(resultSet.getInt("pubId")).get(),
            copyEditorRepository.get(resultSet.getInt("copyId")).get(),
            resultSet.getInt("pages"),
            resultSet.getBoolean("borrowed"),
            resultSet.getInt("issueNumber"),
            resultSet.getDate("publicationDate").toLocalDate(),
            resultSet.getInt("issn")
        ));
      }
    } catch (SQLException e) {
      //
    }
    return magazines;
  }
}
