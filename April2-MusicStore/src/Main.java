import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int choice = 0;
    MusicStoreService service = new MusicStoreService();
    service.initializeProducts();
    printMenu();
    do {
      System.out.print("Seçiminiz: ");
      choice = sc.nextInt(); //  \n
      switch (choice) {
        case 1 -> {
          System.out.println("--------------------------------");
          service.listUsers();
          System.out.println("--------------------------------");
        }
        case 2 -> {
          System.out.println("--------------------------------");
          service.listProducts();
          System.out.println("--------------------------------");
        }
        case 3 -> {
          System.out.println("--------------------------------");
          service.listSongs();
          System.out.println("--------------------------------");
        }
        case 4 -> {
          System.out.println("--------------------------------");
          service.listAlbums();
          System.out.println("--------------------------------");
        }
        case 5 -> {
          System.out.println("--------------------------------");
          System.out.println("Ürün id'si giriniz: ");
          Long productId = sc.nextLong();
          System.out.println("UserId giriniz: ");
          Long userId = sc.nextLong();
          service.purchaseProduct(productId, userId);
          service.listBasket(userId);
          System.out.println("--------------------------------");
        }
        case 6 -> {
          System.out.println("--------------------------------");
          System.out.print("Aranacak ürün adını giriniz: ");
          sc.nextLine(); // \n
          String productName = sc.nextLine();
          Product product = service.searchProductByName(productName);
          if (product == null) {
            System.out.println("Ürün bulunamadı");
            break;
          }
          System.out.println(product);
          System.out.println("--------------------------------");
        }
        case 7 -> {
          System.out.println("--------------------------------");
          System.out.println("Aranacak şarkı adını giriniz: ");
          sc.nextLine();
          String songName = sc.next();
          Song song = service.searchSongOnProduct(songName);
          if (song == null) {
            System.out.println("Şarkı bulunamadı.");
            break;
          }
          System.out.println(song);
          System.out.println("--------------------------------");
        }
        case 8 -> {
          System.out.println("--------------------------------");
          System.out.println("Aranacak albüm adını giriniz: ");
          sc.nextLine();
          String albumName = sc.nextLine();
          Album album = service.searchAlbumOnProduct(albumName);
          if (album == null) {
            System.out.println("Albüm bulunamadı.");
            break;
          }
          System.out.println(album);
          System.out.println("--------------------------------");
        }
        case 9 -> {
          System.out.println("--------------------------------");
          System.out.print("UserId giriniz: ");
          Long userId = sc.nextLong();
          service.listBasket(userId);
          System.out.println("--------------------------------");
        }
        case -1 -> {
          System.out.println("İyi günler");
        }
        default -> printMenu();
      }
    } while (choice != -1);
  }

  private static void printMenu() {
    System.out.println("##### Menu #####");
    System.out.println("1: Kullanıcıları listele");
    System.out.println("2: Ürünleri listele");
    System.out.println("3: Şarkıları listele");
    System.out.println("4: Albumleri listele");
    System.out.println("5: Ürün satın al");
    System.out.println("6: Ürün ara");
    System.out.println("7: Şarkı ara");
    System.out.println("8: Albüm ara");
    System.out.println("9: Kullanıcı sepeti listele");
    System.out.println("-1: Çıkış \n\n");
  }
}