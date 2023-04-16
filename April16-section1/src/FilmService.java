public class FilmService {

  private Film[] films = new Film[DomainConstants.ARRAY_INITIALIZE_SIZE];

  private int filmCount = 0;

  public void addFilm(Film film) {
    if (filmCount == films.length) {
      reInitializeArray();
    }
    boolean filmAlreadyAdded = isFilmAlreadyAdded(film);
    if (!filmAlreadyAdded) {
      films[filmCount] = film;
      filmCount++;
      System.out.println("Film başarıyla eklendi," + film);
    }
  }
// UserRole geçilecek.
  public void removeFilm(String name) {
    int index = -1;
    for (int i = 0; i < filmCount; i++) {
      if (films[i].getName().equalsIgnoreCase(name)) {
        index = i;
        break;
      }
    }
    if (index != -1) {
      for (int i = index; i < filmCount - 1; i++) {
        films[i] = films[i + 1];
      }
      films[filmCount - 1] = null;
      filmCount--;
      System.out.println(name + " başarıyla silindi.");
    } else {
      System.out.println(name + " bulunamadı");
    }
  }

  public Film searchFilm(String name) {
    Film searchedFilm = null;
    for (int i = 0; i < filmCount; i++) {
      if (films[i].getName().equalsIgnoreCase(name)) {
        searchedFilm = films[i];
      }
    }
    if (searchedFilm == null) {
      System.out.println("Aradığınız film bulunamadı");
    }
    return searchedFilm;
  }

  public void listFilm() {
    for (int i = 0; i < filmCount; i++) {
      System.out.println(films[i]);
    }
  }

  private void reInitializeArray() {
    Film[] newFilmArray = new Film[films.length + DomainConstants.ARRAY_UPGRADE_SIZE];
    System.arraycopy(films, 0, newFilmArray, 0, films.length);
    films = newFilmArray;
  }

  private boolean isFilmAlreadyAdded(Film film) {
    for (int i = 0; i < filmCount; i++) {
      if (films[i].getName().equalsIgnoreCase(film.getName())) {
        return true;
      }
    }
    return false;
  }
}
