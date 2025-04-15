package nl.heartmates01.boardgame;

import static nl.heartmates01.main.Main.publisherRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nl.heartmates01.main.JdbcSingleton;

public class BoardgameRepository {

  private final JdbcSingleton jdbcSingleton = JdbcSingleton.getInstance();

  public List<Boardgame> add(Boardgame... boardgames) {

    if (boardgames.length == 0) {
      return new ArrayList<>();
    }

    StringBuilder multipleInserts = new StringBuilder();
    List<Object> parameters = new ArrayList<>();
    String insertQueryString = "INSERT INTO boardgames (id, title, designer, ean, pubId, publicationDate, minPlayers, maxPlayers, borrowed) VALUES (?,?,?,?,?,?,?,?,?)";

    int gameIndex = 0;
    for (Boardgame boardgame : boardgames) {
      if (boardgames.length > 1 && gameIndex == 0) {
        multipleInserts.append(",");
      }
      multipleInserts.append(insertQueryString);

      parameters.addAll(List.of(
          boardgame.getId(),
          boardgame.getTitle(),
          boardgame.getDesigner(),
          boardgame.getEan(),
          boardgame.getPublisher().getId(),
          boardgame.getPublicationDate(),
          boardgame.getMinPlayers(),
          boardgame.getMaxPlayers(),
          boardgame.getBorrowed()
      ));
      gameIndex++;

      List<Integer> ids;
      try {
        ids = jdbcSingleton.insertQuery(multipleInserts.toString(), parameters.toArray());

        for (int i = 1; i <= ids.size(); i++) {
          boardgame.setId(ids.get(i));
        }

      } catch (SQLException e) {
        //
      }
    }
    return List.of(boardgames);
  }

  public int delete(int id) {
    int result = id;
    try {
      result = jdbcSingleton.deleteQuery("DELETE FROM boardgames WHERE id = ?", id);
    } catch (SQLException e) {
      //
    }
    return result;
  }

  public int borrowOrReturnGame(int id) {
    int result = id;
    try {
      result = jdbcSingleton.updateQuery(
          "UPDATE boardgames SET borrowed = NOT borrowed WHERE id = ?",
          id);
    } catch (SQLException e) {
      //
    }
    return result;
  }

  public Optional<Boardgame> get(int id) {
    Optional<ResultSet> result = Optional.empty();
    try {
      result = jdbcSingleton.selectQuery("SELECT * FROM boardgames WHERE id = ?", id);
    } catch (SQLException e) {
      //
    }
    if (result.isEmpty()) {
      return Optional.empty();
    }
    ResultSet resultSet = result.get();
    try {
      resultSet.next();
      return Optional.of(new Boardgame(
          resultSet.getInt("id"),
          resultSet.getString("title"),
          resultSet.getString("designer"),
          resultSet.getString("ean"),
          publisherRepository.get(resultSet.getInt("pubId")).get(),
          resultSet.getDate("publicationDate").toLocalDate(),
          resultSet.getInt("minPlayers"),
          resultSet.getInt("maxPlayers"),
          resultSet.getBoolean("borrowed")
      ));

    } catch (SQLException e) {
      //
    }
    return Optional.empty();
  }

  public List<Boardgame> getAll() {
    Optional<ResultSet> result = Optional.empty();
    try {
      result = jdbcSingleton.selectQuery("SELECT * FROM boardgames");
    } catch (SQLException e) {
      //
    }
    if (result.isEmpty()) {
      return new ArrayList<>();
    }
    ResultSet resultSet = result.get();
    List<Boardgame> boardgames = new ArrayList<>();
    try {
      while (resultSet.next()) {
        boardgames.add(new Boardgame(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getString("designer"),
            resultSet.getString("ean"),
            publisherRepository.get(resultSet.getInt("pubId")).get(),
            resultSet.getDate("publicationDate").toLocalDate(),
            resultSet.getInt("minPlayers"),
            resultSet.getInt("maxPlayers"),
            resultSet.getBoolean("borrowed")
        ));
      }
    } catch (SQLException e) {
      //
    }
    return boardgames;
  }

  public List<Boardgame> search(String keyword) {
    Optional<ResultSet> results = Optional.empty();
    try {
      results = jdbcSingleton.selectQuery(
          "SELECT boardgames.* FROM boardgames LEFT JOIN publishers ON publishers.pubId = boardgames.pubId WHERE title LIKE CONCAT ('%', ?, '%') OR publishers.name LIKE CONCAT ('%', ?, '%')",
          keyword, keyword);
    } catch (SQLException e) {
      //
    }
    if (results.isEmpty()) {
      System.out.println("No results found for Boardgames.");
      return new ArrayList<>();
    }
    ResultSet resultSet = results.get();
    List<Boardgame> boardgames = new ArrayList<>();
    try {
      while (resultSet.next()) {
        boardgames.add(new Boardgame(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getString("designer"),
            resultSet.getString("ean"),
            publisherRepository.get(resultSet.getInt("pubId")).get(),
            resultSet.getDate("publicationDate").toLocalDate(),
            resultSet.getInt("minPlayers"),
            resultSet.getInt("maxPlayers"),
            resultSet.getBoolean("borrowed")
        ));
      }
    } catch (SQLException e) {
      //
    }
    return boardgames;
  }
}
