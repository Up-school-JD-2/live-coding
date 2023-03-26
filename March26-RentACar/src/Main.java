import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Service.generateVehiclesAndCustomers();
    int choice = 0;
    do {
      System.out.println("###################");
      System.out.println("1- Araç kirala");
      System.out.println("2- Mevcut araçları listele");
      System.out.println("3- Araç iade et");
      System.out.println("4- Müşteriler");
      System.out.println("###################");
      choice = sc.nextInt();
      switch (choice) {
        case 1 -> {
          System.out.print("Araç Id si girin: ");
          var vehicleId = sc.next();
          System.out.println("Müşteri Id si girin: ");
          var customerId = sc.next();
          System.out.println("Kaç gün kiralayacaksınız? : ");
          var countOfDay = sc.nextInt();
          Service.rentVehicle(vehicleId, customerId, countOfDay);
        }
        case 2 -> {
          System.out.println(" Truck / Car / Van / All :");
          var type = sc.next();
          var vehicleType = VehicleType.getVehicleType(type);
          var availableVehicles = Service.availableVehicles(vehicleType);
          System.out.println(availableVehicles);
        }
        case 3 -> {
          var rentedVehicles = Service.rentedVehicles();
          if (rentedVehicles.size() == 0) {
            System.out.println("Teslim edilecek araç yok");
            break;
          }
          System.out.println(rentedVehicles);
          System.out.println("Teslim edilecek araç Id'sini giriniz: ");
          var vehicleId = sc.next();
          Service.returnVehicle(vehicleId);
        }
        case 4 -> {
          System.out.println(Service.CUSTOMERS);
        }
        case -1 -> {
        }
        default -> System.out.println("Hatalı seçim yaptınız, çıkış için -1");
      }
    } while (choice > -1);
    System.out.println("Bizi tercih ettiğiniz için teşekkürler.");
  }
}