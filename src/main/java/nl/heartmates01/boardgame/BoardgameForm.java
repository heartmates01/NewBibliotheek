package nl.heartmates01.boardgame;

import java.time.LocalDate;
import java.util.Map;
import nl.heartmates01.main.Main;

public class BoardgameForm {

  public Map<String, Object> getGameInput() {
    return Map.of(
        "title", Main.getUserInput("Enter this game's title: "),
        "designer", Main.getUserInput("Enter this game's designer: "),
        "ean", Main.getUserInput("Enter this game's EAN: "),
        "pubId", Integer.parseInt(Main.getUserInput("Enter this game's publisher ID: ")),
        "publicationDate",
        LocalDate.parse(Main.getUserInput("Enter this game's publication date: ")),
        "minPlayers", Integer.parseInt(Main.getUserInput("Enter this game's minimum players: ")),
        "maxPlayers", Integer.parseInt(Main.getUserInput("Enter this game's maximum players: ")),
        "borrowed",
        Boolean.parseBoolean(Main.getUserInput("Enter if this game is being borrowed: "))
    );
  }
}
