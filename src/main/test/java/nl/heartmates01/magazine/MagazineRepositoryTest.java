package nl.heartmates01.magazine;

import static nl.heartmates01.main.Main.copyEditorRepository;
import static nl.heartmates01.main.Main.magazineRepository;
import static nl.heartmates01.main.Main.publisherRepository;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class MagazineRepositoryTest {

  @Test
  void add() {
    List<Magazine> magazines = magazineRepository.add(new Magazine(
        9,
        "title",
        "daily",
        publisherRepository.getAll().getFirst(),
        copyEditorRepository.getAll().getFirst(),
        123,
        true,
        50,
        LocalDate.of(2025, 4, 22),
        1234567890
    ));
    magazines.forEach(magazine -> assertNotNull(magazineRepository.get(magazine.getId())));
  }

  @Test
  void delete() {
    int id = 9;
    magazineRepository.delete(id);
    assert (magazineRepository.get(id).isEmpty());
  }

  @Test
  void get() {
    int id = 9;
    magazineRepository.get(id);
    assertNotNull(magazineRepository.get(id));
  }

  @Test
  void borrowOrReturn() {
    int id = 9;
    magazineRepository.borrowOrReturn(id);
  }

  @Test
  void getAll() {
    magazineRepository.getAll().forEach(magazine -> System.out.println(magazine.getTitle()));
    assertNotNull(magazineRepository.getAll());
  }

  @Test
  void search() {
    String keyword = "mag";
    magazineRepository.search(keyword).forEach(magazine -> System.out.println(magazine.toString()));
  }
}
