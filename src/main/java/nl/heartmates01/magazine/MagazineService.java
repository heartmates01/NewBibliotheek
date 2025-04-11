package nl.heartmates01.magazine;

import static nl.heartmates01.main.Main.magazineRepository;

import java.util.List;

public class MagazineService {

  public List<Magazine> getAll() {
    return magazineRepository.getAll();
  }

  public List<Magazine> search(String keyword) {
    return magazineRepository.search(keyword);
  }
}
