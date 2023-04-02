import java.io.Serializable;
import java.util.Arrays;

public class MusicStoreService implements Serializable {

  //public Song[] allSongs;
  //
  //public Album[] allAlbums;
  //  private User currentUser;

  public Product[] allProducts;

  //region Song and Albums
  private User[] users = new User[] {
      new User(1L, "Asım Kılıç", new Basket()),
      new User(2L, "Ahmet Gültekin", new Basket())
  };

  private Song song1 = new Song(1L, "Vazgeçtim", "Müslüm Gürses", "2009", 3.40, 9.99);

  private Song song2 = new Song(2L, "Gönül", "Müslüm Gürses", "2009", 4.55, 9.99);

  private Song song3 = new Song(3L, "İtirazım var", "Müslüm Gürses", "2009", 4.40, 9.99);

  private Song song4 = new Song(4L, "Tutamıyorum Zaman", "Müslüm Gürses", "2009", 4.27, 9.99);

  private Song song5 = new Song(5L, "Dem", "Can Bonomo", "2013", 3.40, 6.99);

  private Song song6 = new Song(6L, "Tastamam", "Can Bonomo", "2013", 4.55, 6.99);

  private Song song7 = new Song(7L, "Hikayem Bitmedi", "Can Bonomo", "2013", 4.40, 6.99);

  private Song song8 = new Song(8L, "Senin Olmadan Ölemem", "Can Bonomo", "2013", 4.27, 6.99);

  private Song song9 = new Song(9L, "Bulunmam Gerek", "Can Bonomo", "2013", 4.27, 6.99);

  private Song[] muslumGursesSongs = new Song[] {song1, song2, song3, song4};

  private Song[] canBonomoSongs = new Song[] {song5, song6, song7, song8, song9};

  private Album bulunmamGerek = new Album(10L, "Bulunmam Gerek", "Can Bonomo", "2013", 17.52, 39.99, canBonomoSongs);

  private Album sandik = new Album(11L, "Sandık", "Müslüm Gürses", "2009", 17.52, 29.99, muslumGursesSongs);
  //endregion

  public void initializeProducts() {
    //allSongs = new Song[] {song1, song2, song3, song4, song5, song6, song7, song8, song9};
    //allAlbums = new Album[] {bulunmamGerek, sandik};
    allProducts = new Product[] {song1, song2, song3, song4, song5, song6, song7, song8, song9, bulunmamGerek, sandik};
  }

  // search?id=1&productName=tastamam
  // queryDSL criteriaAPI
  // select * from products p where p.id =1 and p.productName = 'tastamam'
  //region search
  public Product searchProductById(Long productId) {
    Product searchedProduct = null;
    for (Product product : allProducts) {
      if (product.getId().equals(productId)) {
        searchedProduct = product;
        break;
      }
    }
    return searchedProduct;
  }

  public Product searchProductByName(String productName) {
    Product searchedProduct = null;
    for (Product product : allProducts) {
      if (product.getName().equalsIgnoreCase(productName)) {
        searchedProduct = product;
        break;
      }
    }
    return searchedProduct;
  }

  public Song searchSongOnProduct(String songName) {
    Song searchedSong = null;
    for (Product product : allProducts) {
      if (product.getName().equalsIgnoreCase(songName)) {
        //if(product.getClass().getName().equals("Song")) {
        //
        //}
        if (product instanceof Song) {
          searchedSong = (Song) product;
          break;
        }
      }
    }
    return searchedSong;
  }

  public Album searchAlbumOnProduct(String albumName) {
    Album searchedAlbum = null;
    for (Product product : allProducts) {
      if (product.getName().equalsIgnoreCase(albumName)) {
        if (product instanceof Album) {
          searchedAlbum = (Album) product;
        }
      }
    }
    return searchedAlbum;
  }
  //endregion

  //region list
  public void listUsers() {
    System.out.println(Arrays.toString(users));
  }

  public void listProducts() {
    for (Product product : allProducts) {
      System.out.println(product);
    }
  }

  public void listSongs() {
    for (Product product : allProducts) {
      if (product instanceof Song) {
        System.out.println(product);
      }
    }
  }

  public void listAlbums() {
    for (Product product : allProducts) {
      if (product instanceof Album) {
        System.out.println(product);
      }
    }
  }

  public void listBasket(Long userId) {
    User user = findUser(userId);
    if (user == null) {
      System.out.println("Kullanıcı bulunamadı");
      return;
    }
    user.getBasket().listBasket();
  }
  //endregion

  public User findUser(Long id) {
    User user = null;
    for (User u : users) {
      if (u.getId().equals(id)) {
        user = u;
      }
    }
    return user;
  }

  public void purchaseProduct(Long productId, Long userId) {
    Product product = searchProductById(productId);
    User user = findUser(userId);
    if (user == null) {
      System.out.println("Kullanıcı bulunamadı");
      return;
    }
    if (product == null) {
      System.out.println("Ürün bulunamadı");
      return;
    }
    user.getBasket().addProduct(product);
  }
}
