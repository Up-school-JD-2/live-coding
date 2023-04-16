import java.util.Scanner;

public class Main {

  private static final UserService userService = new UserService();

  private static final FilmService filmService = new FilmService();

  private static final Scanner scanner = new Scanner(System.in);

  private static int exitCode = 1;

  public static void main(String[] args) {
    initData();
    System.out.println("########### HOŞGELDİNİZ #############");
    while (exitCode > 0) {
      writeMenu();
    }
    System.out.println("İyi günler");
  }

  public static void writeMenu() {
    if (userService.isLoggedIn()) {
      int choice;
      do {
        System.out.println("########## ANA MENÜ ###########");
        System.out.println("#  1 - Kullanıcılar");
        System.out.println("#  2 - Filmler");
        System.out.println("# -1 - Sistemden çıkış yap");
        System.out.println("###############################");
        choice = scanner.nextInt();
        switch (choice) {
          case 1 -> {
            // KULLANICILAR
            int innerChoice;
            do {
              System.out.println("###### Kullanıcı Menüsü #######");
              System.out.println("# 1 - Çıkış yap(" + userService.getCurrentUsername() + ")");
              System.out.println("# 2 - Kullanıcı ara");
              System.out.println("# 3 - Kullanıcı sil");
              System.out.println("# 4 - Sevdiğim film ekle");
              System.out.println("# 5 - Kullanıcıları listele");
              System.out.println("# 6 - Sevdiğim filmleri listele");
              System.out.println("# 0 - Üst menüye dön");
              System.out.println("#################################");
              innerChoice = scanner.nextInt();
              switch (innerChoice) {
                case 1 -> {
                  // KULLANICILAR - Çıkış yap
                  userService.logout();
                  innerChoice = 0;
                  choice = -1;
                }
                case 2 -> {
                  // KULLANICILAR - Kullanıcı ara
                  System.out.print("Kullanıcı adını giriniz: ");
                  String username = scanner.next();
                  userService.searchUser(username);
                }
                case 3 -> {
                  // KULLANICILAR - Kullanıcı sil
                  System.out.print("Kullanıcı adını giriniz: ");
                  String username = scanner.next();
                  userService.removeUser(username);
                }
                case 4 -> {
                  // KULLANICILAR - sevdiğim film ekle
                  scanner.nextLine();
                  System.out.print("Eklenecek film adını giriniz: ");
                  String filmName = scanner.nextLine();
                  Film film = filmService.searchFilm(filmName);
                  userService.addLikedFilms(film);
                }
                case 5 -> {
                  // KULLANICILAR - Kullanıcıları listele
                  userService.listUsers();
                }
                case 6 -> {
                  // KULLANICILAR - Sevdiğim filmleri listele
                  userService.listCurrentUserLikedFilms();
                }
                case 0 -> {
                  // Üst menüye dön
                }
                default -> System.out.println("Hatalı giriş yaptınız, üst menü için 0'a basınız.");
              }
            } while (innerChoice != 0);
          }
          case 2 -> {
            // FİLMLER
            int innerChoice;
            do {
              System.out.println("###### Film Menüsü #######");
              System.out.println("# 1 - Filmleri listele");
              System.out.println("# 2 - Film ara");
              System.out.println("# 3 - Film sil");
              System.out.println("# 4 - Film ekle");
              System.out.println("# 0 - Üst menüye dön");
              System.out.println("#################################");
              innerChoice = scanner.nextInt();
              switch (innerChoice) {
                case 1 -> {
                  // FİLMLER - Filmleri listele
                  filmService.listFilm();
                }
                case 2 -> {
                  // FİLMLER - film ara
                  scanner.nextLine();
                  System.out.print("Film adını giriniz: ");
                  String filmName = scanner.nextLine();
                  Film film = filmService.searchFilm(filmName);
                  if (film != null) {
                    System.out.println(film);
                  }
                }
                case 3 -> {
                  // FİLMLER - film sil
                  scanner.nextLine();
                  System.out.print("Silinecek film adını giriniz: ");
                  String filmName = scanner.nextLine();
                 // UserRole userRole = userService.getUserRole();
                 // filmService.removeFilm(filmName,userRole);
                  filmService.removeFilm(filmName);
                }
                case 4 -> {
                  // FİLMLER - film ekle
                  scanner.nextLine();
                  System.out.print("Film adını giriniz: ");
                  String name = scanner.nextLine();

                  userService.listUsersByOccupation(Occupation.DIRECTOR);
                  System.out.print("Yönetmeni giriniz: ");
                  String directorUsername = scanner.nextLine();
                  User director = userService.getUser(directorUsername);

                  userService.listUsersByOccupation(Occupation.ACTOR);
                  System.out.print("Oyuncuları aralarına virgül koyarak giriniz: ");
                  String[] actorsUsernames = scanner.nextLine().split(",");
                  User[] actors = userService.getUsersByUsername(actorsUsernames);

                  System.out.print("Yayınlanma tarihini giriniz (dd-MM-yyyy): ");
                  String releaseDate = scanner.next();
                  scanner.nextLine();
                  System.out.print("Açıklama giriniz: ");
                  String description = scanner.nextLine();

                  Genre.listGenres();
                  System.out.println("Türünü aralarına virgül koyarak giriniz: ");
                  String[] inputGenres = scanner.nextLine().split(",");
                  Genre[] genres = Genre.getGenres(inputGenres);

                  Film film = new Film(name, director, actors, releaseDate, description, genres);
                  filmService.addFilm(film);
                }
                case 0 -> {
                  // Üst menüye dön
                }
                default -> System.out.println("Hatalı giriş yaptınız, üst menü için 0'a basınız.");
              }
            } while (innerChoice != 0);
          }
          case -1 -> {
            Main.exitCode = -1;
          }
          default -> {
            System.out.println("Hatalı giriş yaptınız, çıkmak için -1'e basınız.");
          }
        }
      } while (choice != -1);
    } else {
      System.out.println("#######################");
      System.out.println("#  1 - Giriş yap");
      System.out.println("#  2 - Üye Ol");
      System.out.println("# -1 - Sistemden çıkış yap");
      System.out.println("#######################");
      System.out.print("Seçiminiz: ");
      int choice = scanner.nextInt();
      switch (choice) {
        case 1 -> {
          // GİRİŞ YAP
          System.out.print("Kullanıcı adınız: ");
          String username = scanner.next();
          System.out.print("Şifreiniz : ");
          String password = scanner.next();
          userService.login(username, password);
        }
        case 2 -> {
          // ÜYE OL
          scanner.nextLine();
          System.out.print("Kullanıcı adınız : ");
          String username = scanner.next();
          while (userService.isUserAlreadyAdded(username)) {
            System.out.print("Kullanıcı adı alınmış başka bir kullanıcı adı giriniz: ");
            username = scanner.next();
          }
          scanner.nextLine();
          System.out.print("Adınız soyadınız : ");
          String fullName = scanner.nextLine();

          System.out.print("Şifreniz : ");
          String password = scanner.next();

          Occupation.listOccupations();
          System.out.print("Mesleğiniz: ");
          String occupationInput = scanner.next();
          Occupation occupation = Occupation.getOccupation(occupationInput);

          User user = new User(fullName, username, password, occupation);
          if (user.getUsername().contains("asim")) {
            user.setUserRole(UserRole.ADMIN);
          }
          userService.addUser(user);
        }
        case -1 -> {
          // Sistemden çıkış yap
          Main.exitCode = -1;
        }
        default -> {
          System.out.println("Hatalı giriş yaptınız");
        }
      }
    }
  }

  private static void initData() {

    User nolan = new User("Christopher Nolan", "nolan", "nolan123", Occupation.DIRECTOR);
    User coppola = new User("Francis Ford Coppola", "coppola", "coppola123", Occupation.DIRECTOR);
    User zemeckis = new User("Robert Zemeckis", "zemeckis", "zemeckis123", Occupation.DIRECTOR);
    User cameron = new User("James Cameron", "cameron", "cameron123", Occupation.DIRECTOR);
    User bale = new User("Christian Bale", "bale", "bale123", Occupation.ACTOR);
    User ledger = new User("Heath Ledger", "ledger", "ledger123", Occupation.ACTOR);
    User eckhart = new User("Aaron Eckhart", "eckhart", "eckhart123", Occupation.ACTOR);
    User leo = new User("Leonardo DiCaprio", "leo", "leo123", Occupation.ACTOR);
    User tom = new User("Tom Hardy", "tom", "tom123", Occupation.ACTOR);
    User brando = new User("Marlon Brando", "brando", "brando123", Occupation.ACTOR);
    User pacino = new User("Al Pacino", "pacino", "pacino123", Occupation.ACTOR);
    User caan = new User("James Caan", "caan", "caan123", Occupation.ACTOR);
    User hanks = new User("Tom Hanks", "hanks", "hanks123", Occupation.ACTOR);
    User winslet = new User("Kate Winslet", "winslet", "winslet123", Occupation.ACTOR);
    User zane = new User("Billy Zane", "zane", "zane123", Occupation.ACTOR);
    User[] users =
        new User[] {nolan, coppola, zemeckis, cameron, bale, ledger, eckhart, leo, tom, brando, pacino, caan, hanks,
                    winslet, zane,};
    for (User user : users) {
      userService.addUser(user);
    }

    Film[] films = new Film[] {
        new Film("The Dark Knight", nolan, new User[] {bale, ledger, eckhart}, "2008",
                 "Gotham'ın şehir sokaklarında suçla savaşan Batman, Joker'ın kargaşa planlarına karşı koymaya "
                 + "çalışır.",
                 new Genre[] {Genre.ACTION, Genre.DRAMA, Genre.CRIME}),
        new Film("Inception", nolan, new User[] {leo, tom}, "2010",
                 "Dom Cobb, zihinlerin derinliklerinde gizli olan sırları çalmak için tehlikeli bir görevi kabul eder.",
                 new Genre[] {Genre.ACTION, Genre.SCIENCE_FICTION}),
        new Film("The Godfather", coppola, new User[] {brando, pacino, caan}, "1972",
                 "Baba Vito Corleone'nin ailesi, New York'ta suç dünyasında etkin bir şekilde yer alır.",
                 new Genre[] {Genre.CRIME, Genre.DRAMA}),
        new Film("Forrest Gump", zemeckis, new User[] {hanks}, "1994",
                 "Zeka seviyesi düşük bir adam olan Forrest Gump, Amerika'nın tarihi olaylarına şahitlik eder.",
                 new Genre[] {Genre.COMEDY, Genre.ROMANCE, Genre.DRAMA}),
        new Film("Titanic", cameron, new User[] {leo, winslet}, "1997",
                 "1912'de geçen bu film, farklı sınıflardan gelen iki insanın birbirine aşık olmasını anlatır. Ancak "
                 + "Titanic gemisinin batmasıyla dramatik bir sonla biter.",
                 new Genre[] {Genre.ROMANCE, Genre.DRAMA})
    };
    for (Film film : films) {
      filmService.addFilm(film);
    }
  }
}

