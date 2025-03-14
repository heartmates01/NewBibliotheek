package nl.heartmates01.magazine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.heartmates01.main.JdbcSingleton;

public class PublisherRepository {

  private final JdbcSingleton jdbcSingleton = JdbcSingleton.getInstance();

  public List<Publisher> add(Publisher... publishers) {
    if (publishers.length == 0) {
      return new ArrayList<>();
    }

    StringBuilder multipleInserts = new StringBuilder();
    List<Object> parameters = new ArrayList<>();
    String insertQueryString = "INSERT INTO publishers (pubId, name, dateOfBirth) VALUES (?, ?, ?)";

    int pubIndex = 0;
    for (Publisher publisher : publishers) {
      if (publishers.length > 1 && pubIndex == 0) {
        multipleInserts.append(",");
      }

      multipleInserts.append(insertQueryString);
      parameters.addAll(List.of(
          publisher.getId(),
          publisher.getName(),
          publisher.getDateOfBirth()
      ));
      pubIndex++;

      List<Integer> ids;
      try {
        ids = jdbcSingleton.insertQuery(multipleInserts.toString(),
            parameters.toArray());
        for (int i = 1; i <= ids.size(); i++) {
          publisher.setPubId(ids.get(i));
        }
      } catch (SQLException e) {
        //
      }
    }
    return List.of(publishers);
  }

  public int delete(int id) {
    int result = id;
    try {
      result = jdbcSingleton.deleteQuery("DELETE FROM publishers WHERE pubId = ?", id);
    } catch (SQLException e) {
      //
    }
    return result;
  }

  public Optional<Publisher> get(int id) {
    Optional<ResultSet> result = Optional.empty();
    try {
      result = jdbcSingleton.selectQuery("SELECT * FROM publishers WHERE pubId = ?", id);
    } catch (SQLException e) {
      //
    }
    if (result.isEmpty()) {
      return Optional.empty();
    }
    ResultSet resultSet = result.get();
    try {
      resultSet.next();
      return Optional.of(new Publisher(
          resultSet.getInt("pubId"),
          resultSet.getString("name"),
          resultSet.getDate("dateOfBirth").toLocalDate()
      ));
    } catch (SQLException e) {
      //
    }
    return Optional.empty();
  }

  public List<Publisher> getAll() {
    Optional<ResultSet> results = Optional.empty();
    try {
      results = jdbcSingleton.selectQuery("SELECT * FROM publishers");
    } catch (SQLException e) {
      //
    }
    if (results.isEmpty()) {
      return new ArrayList<>();
    }
    ResultSet resultSet = results.get();
    List<Publisher> publishers = new ArrayList<>();
    try {
      while (resultSet.next()) {
        publishers.add(new Publisher(
            resultSet.getInt("pubId"),
            resultSet.getString("name"),
            resultSet.getDate("dateOfBirth").toLocalDate()
        ));
      }
    } catch (SQLException e) {
      //
    }
    return publishers;
  }
}