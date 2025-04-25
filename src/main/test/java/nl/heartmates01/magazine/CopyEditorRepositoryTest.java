package nl.heartmates01.magazine;

import static nl.heartmates01.main.Main.copyEditorRepository;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class CopyEditorRepositoryTest {

  @Test
  void add() {
    List<CopyEditor> copyEditors = copyEditorRepository.add(new CopyEditor(
        4,
        "test",
        LocalDate.of(2025, 4, 22)
    ));
    copyEditors.forEach(copyEditor -> assertNotNull(copyEditorRepository.get(copyEditor.getId())));
  }

  @Test
  void delete() {
    int id = 4;
    copyEditorRepository.delete(id);
    assert (copyEditorRepository.get(id).isEmpty());
  }

  @Test
  void get() {
    int id = 4;
    copyEditorRepository.get(id);
    assertNotNull(copyEditorRepository.get(id));
  }

  @Test
  void getAll() {
    copyEditorRepository.getAll()
        .forEach(copyEditor -> System.out.println(copyEditor.getCopyInfo()));
    assertNotNull(copyEditorRepository.getAll());
  }
}
