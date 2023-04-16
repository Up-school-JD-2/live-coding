import java.util.UUID;

public class User {

  private final UUID id = UUID.randomUUID();

  private String fullName;

  private String username;

  private String password;

  private Occupation occupation;

  private Film[] likedFilms = new Film[DomainConstants.ARRAY_INITIALIZE_SIZE];

  private UserRole userRole = UserRole.USER;

  public User(String fullName, String username, String password, Occupation occupation) {
    this.fullName = fullName;
    this.username = username;
    this.password = password;
    this.occupation = occupation;
  }

  public UUID getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Occupation getOccupation() {
    return occupation;
  }

  public void setOccupation(Occupation occupation) {
    this.occupation = occupation;
  }

  public Film[] getLikedFilms() {
    return likedFilms;
  }

  public void setLikedFilms(Film[] likedFilms) {
    this.likedFilms = likedFilms;
  }

  public UserRole getUserRole() {
    return userRole;
  }

  public void setUserRole(UserRole userRole) {
    this.userRole = userRole;
  }

  @Override
  public String toString() {
    return "User{" +
           "id=" + id +
           ", username='" + username + '\'' +
           '}';
  }
}
