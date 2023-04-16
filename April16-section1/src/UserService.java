public class UserService {

  private User[] users = new User[DomainConstants.ARRAY_INITIALIZE_SIZE];

  private User currentUser = null;

  private int userCount = 0;

  public void addUser(User user) {
    if (userCount == users.length) {
      reInitializeArray();
    }
    boolean userAlreadyAdded = isUserAlreadyAdded(user.getUsername());
    if (!userAlreadyAdded) {
      users[userCount] = user;
      userCount++;
      System.out.println("User başarıyla eklendi, " + user);
    } else {
      System.out.println("Kullanıcı zaten kayıtlı!");
    }
  }

  public void removeUser(String username) {
    if (!isCurrentUserAdmin()) {
      return;
    }
    int index = -1;
    for (int i = 0; i < userCount; i++) {
      if (users[i].getFullName().equalsIgnoreCase(username)) {
        index = i;
        break;
      }
    }
    if (index != -1) {
      for (int i = index; i < userCount; i++) {
        users[i] = users[i + 1];
      }
      users[userCount - 1] = null;
      userCount--;
      System.out.println(username + " başarıyla silindi.");
    } else {
      System.out.println(username + " bulunamadı");
    }
  }

  public void searchUser(String username) {
    if (!isCurrentUserAdmin()) {
      return;
    }
    User user = getUser(username);
    if (user != null) {
      System.out.println(user);
    } else {
      System.out.println("Kullanıcı bulunamadı!");
    }
  }

  public User getUser(String username) {
    User searchedUser = null;
    for (int i = 0; i < userCount; i++) {
      if (users[i].getUsername().equalsIgnoreCase(username)) {
        searchedUser = users[i];
      }
    }
    return searchedUser;
  }

  public void login(String username, String password) {
    User user = getUser(username);
    if (user != null) {
      if (user.getPassword().equals(password)) {
        currentUser = user;
        System.out.println(username + " başarıyla giriş yapıldı.");
        return;
      }
    }
    System.out.println("Kullanıcı adı veya şifre hatalı");
  }

  public void logout() {
    currentUser = null;
  }

  public void addLikedFilms(Film film) {
    if (film == null) {
      return;
    }
    Film[] likedFilms = currentUser.getLikedFilms();
    boolean isFilmAdded = false;
    for (int i = 0; i < likedFilms.length; i++) {
      if (likedFilms[i] == null) {
        likedFilms[i] = film;
        isFilmAdded = true;
        break;
      }
    }
    if (!isFilmAdded) {
      for (int i = likedFilms.length - 2; i > 0; i--) {
        likedFilms[i + 1] = likedFilms[i];
      }
      likedFilms[0] = film;
    }
  }

  public boolean isLoggedIn() {
    return !(currentUser == null);
  }

  public String getCurrentUsername() {
    if (isLoggedIn()) {
      return currentUser.getUsername();
    }
    return DomainConstants.EMPTY_STRING;
  }

  public void listUsers() {
    isCurrentUserAdmin();
    for (int i = 0; i < userCount; i++) {
      System.out.println(users[i]);
    }
  }

  public void listCurrentUserLikedFilms() {
    if (!isLoggedIn()) {
      return;
    }
    Film[] likedFilms = currentUser.getLikedFilms();
    for (Film likedFilm : likedFilms) {
      if (likedFilm != null) {
        System.out.println(likedFilm);
      }
    }
  }

  public void listUsersByOccupation(Occupation occupation) {
    System.out.println("--- " + occupation.name() + " ----");
    for (int i = 0; i < userCount; i++) {
      User user = users[i];
      if (occupation.equals(user.getOccupation())) {
        System.out.println(user);
      }
    }
    System.out.println("------------------");
  }

  public User[] getUsersByUsername(String[] userNames) {
    User[] users = new User[userNames.length];
    for (int i = 0; i < userNames.length; i++) {
      users[i] = getUser(userNames[i]);
    }
    return users;
  }

  public boolean isUserAlreadyAdded(String username) {
    for (int i = 0; i < userCount; i++) {
      if (users[i].getUsername().equalsIgnoreCase(username)) {
        return true;
      }
    }
    return false;
  }

  private boolean isCurrentUserAdmin() {
    if (!isLoggedIn()) {
      System.out.println("Login olmanız gerekmelidir.");
      return false;
    }
    boolean isValid = currentUser.getUserRole().equals(UserRole.ADMIN);
    if (!isValid) {
      System.out.println("Bu işlemi yapmaya yetkiniz yok.");
      return false;
    }
    return true;
  }

  private void reInitializeArray() {
    User[] newUserArray = new User[users.length + DomainConstants.ARRAY_UPGRADE_SIZE];
    System.arraycopy(users, 0, newUserArray, 0, users.length);
    users = newUserArray;
  }
}
