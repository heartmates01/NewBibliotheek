package nl.heartmates01.boardgame;

import static nl.heartmates01.main.Main.boardgameRepository;
import static nl.heartmates01.main.Main.publisherRepository;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class BoardgameRepositoryTest {

  @Test
  void add() {
    List<Boardgame> boardgames = boardgameRepository.add(new Boardgame(
        5,
        "title",
        "designer",
        "1234567890",
        publisherRepository.getAll().getFirst(),
        LocalDate.of(2025, 2, 11),
        4,
        6,
        false
    ));
    boardgames.forEach(boardgame -> assertNotNull(boardgameRepository.get(boardgame.getId())));
  }

  @Test
  void delete() {
    int id = 5;
    boardgameRepository.delete(id);
    assert (boardgameRepository.get(id).isEmpty());
  }

  @Test
  void get() {
    int id = 5;
    boardgameRepository.get(id);
    assertNotNull(boardgameRepository.get(id));
  }

  @Test
  void borrowOrReturn() {
    int id = 5;
    boardgameRepository.borrowOrReturn(id);

  }

  @Test
  void getAll() {
    boardgameRepository.getAll().forEach(boardgame -> System.out.println(boardgame.toString()));
    assertNotNull(boardgameRepository.getAll());
  }

  @Test
  void search() {
    String keyword = "catan";
    boardgameRepository.search(keyword)
        .forEach(boardgame -> System.out.println(boardgame.toString()));
  }
}
