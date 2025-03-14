package nl.heartmates01.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcSingleton {

  static JdbcSingleton instance;

  private final Connection con;

  private JdbcSingleton() {
    try {
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mariadb", "root",
          "WW@mariadb#01");
    } catch (SQLException e) {
      System.out.println("Database hasn't been started yet.");
      throw new RuntimeException(e);
    }
  }

  public static JdbcSingleton getInstance() {
    if (instance == null) {
      instance = new JdbcSingleton();
    }
    return instance;
  }

  private PreparedStatement prepareStatement(PreparedStatement prepStatement, Object[] parameters)
      throws SQLException {
    int i = 1;
    for (Object parameter : parameters) {
      switch (parameter) {
        case Integer n -> prepStatement.setInt(i++, n);
        case Long l -> prepStatement.setLong(i++, l);
        case Float f -> prepStatement.setFloat(i++, f);
        case String s -> prepStatement.setString(i++, s);
        case Boolean b -> prepStatement.setBoolean(i++, b);
        case LocalDate d -> prepStatement.setString(i++, d.format(DateTimeFormatter.ISO_DATE));
        default -> throw new IllegalStateException("Unexpected value: " + parameter);
      }
    }
    return prepStatement;
  }


  // select
  public Optional<ResultSet> selectQuery(String sql) throws SQLException {
    Statement prepStatement = con.createStatement();
    ResultSet resultSet = prepStatement.executeQuery(sql);

    return resultSet.isBeforeFirst() ? Optional.of(resultSet) : Optional.empty();
  }

  // select w/ where param

  public Optional<ResultSet> selectQuery(String sql, Object... parameters) throws SQLException {
    PreparedStatement prepStatement = con.prepareStatement(sql);
    ResultSet resultSet = prepareStatement(prepStatement, parameters).executeQuery();

    return resultSet.isBeforeFirst() ? Optional.of(resultSet) : Optional.empty();
  }

  // insert
  public List<Integer> insertQuery(String sql, Object[] parameters) throws SQLException {
    PreparedStatement prepStatement = con.prepareStatement(sql,
        Statement.RETURN_GENERATED_KEYS);
    List<Integer> ids = new ArrayList<>();
    // gets ids of inserted rows
    try (ResultSet generatedKeys = prepStatement.getGeneratedKeys()) {
      while (generatedKeys.next()) {
        ids.add(generatedKeys.getInt(1));
      }
    }
    // executes query
    int affectedRows = prepareStatement(prepStatement, parameters).executeUpdate();
    // returns empty list if no rows were affected
    if (affectedRows == 0) {
      return List.of();
    }
    return ids;
  }

  // delete
  public int deleteQuery(String sql, Object... parameters) throws SQLException {
    PreparedStatement prepStatement = con.prepareStatement(sql);

    return prepareStatement(prepStatement, parameters).executeUpdate();
  }
}
