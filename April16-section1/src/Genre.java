import java.util.EnumSet;

public enum Genre {
  ACTION,
  ADVENTURE,
  COMEDY,
  CRIME,
  DRAMA,
  HORROR,
  ROMANCE,
  SCIENCE_FICTION,
  THRILLER;

  public static Genre getGenre(String genre) {
    EnumSet<Genre> genres = EnumSet.allOf(Genre.class);
    for (Genre g : genres) {
      if (g.name().equalsIgnoreCase(genre)) {
        return g;
      }
    }
    return null;
  }

  public static Genre[] getGenres(String[] genreNames) {
    Genre[] genres = new Genre[genreNames.length];
    for (int i = 0; i < genreNames.length; i++) {
      genres[i] = getGenre(genreNames[i]);
    }
    return genres;
  }

  public static void listGenres() {
    System.out.println("------ Türler -------");
    EnumSet<Genre> genres = EnumSet.allOf(Genre.class);
    for (Genre g : genres) {
      System.out.println(g.name());
    }
    System.out.println("---------------------");
  }
}