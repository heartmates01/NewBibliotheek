package nl.heartmates01.magazine;

import static nl.heartmates01.main.Main.publisherRepository;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class PublisherRepositoryTest {

  @Test
  void add() {
    List<Publisher> publishers = publisherRepository.add(new Publisher(
        13,
        "title",
        "test"
    ));
    publishers.forEach(publisher -> assertNotNull(publisherRepository.get(publisher.getId())));
  }

  @Test
  void delete() {
    int id = 13;
    publisherRepository.delete(id);
    assert (publisherRepository.get(id).isEmpty());
  }

  @Test
  void get() {
    int id = 13;
    publisherRepository.get(id);
    assertNotNull(publisherRepository.get(id));
  }

  @Test
  void getAll() {
    publisherRepository.getAll().forEach(publisher -> System.out.println(publisher.getPubInfo()));
    assertNotNull(publisherRepository.getAll());
  }
}
