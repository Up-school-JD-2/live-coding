import java.util.UUID;

public class Film {

  private final UUID id = UUID.randomUUID();

  private String name;
  
  private User director;

  private User[] actors;

  private String releaseDate;

  private String description;

  private Genre[] genres;

  public Film(String name,User director, User[] actors, String releaseDate, String description,
              Genre[] genres) {
    this.name = name;

    this.director = director;
    this.actors = actors;
    this.releaseDate = releaseDate;
    this.description = description;
    this.genres = genres;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public User getDirector() {
    return director;
  }

  public void setDirector(User director) {
    this.director = director;
  }

  public User[] getActors() {
    return actors;
  }

  public void setActors(User[] actors) {
    this.actors = actors;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Genre[] getGenres() {
    return genres;
  }

  public void setGenres(Genre[] genres) {
    this.genres = genres;
  }

  @Override
  public String toString() {
    return "Film{" +
           "name='" + name + '\'' +
           ", description='" + description + '\'' +
           '}';
  }
}
