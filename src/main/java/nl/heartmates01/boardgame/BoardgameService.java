package nl.heartmates01.boardgame;

import static nl.heartmates01.main.Main.boardgameRepository;

import java.util.List;

public class BoardgameService {

  public List<Boardgame> getAll() {
    return boardgameRepository.getAll();
  }

  public List<Boardgame> search(String keyword) {
    return boardgameRepository.search(keyword);
  }
}
