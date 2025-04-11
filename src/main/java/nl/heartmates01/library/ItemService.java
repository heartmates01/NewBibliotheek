package nl.heartmates01.library;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nl.heartmates01.book.BookService;
import nl.heartmates01.magazine.MagazineService;
import nl.heartmates01.boardgame.BoardgameService;

class ItemService {

  private static final BookService bookService = new BookService();
  private static final MagazineService magazineService = new MagazineService();
  private static final BoardgameService boardgameService = new BoardgameService();

  List<Item> showAll() {
    return Stream.of(bookService.getAll(), magazineService.getAll(),
        boardgameService.getAll()).flatMap(
        Collection::stream).collect(Collectors.toList());
  }

  List<Item> search(String keyword) {
    return Stream.of(bookService.search(keyword), magazineService.search(keyword),
        boardgameService.search(keyword)).flatMap(
        Collection::stream).collect(Collectors.toList());
  }
}
