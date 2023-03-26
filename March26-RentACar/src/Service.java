import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Service {

  public static final List<Vehicle> VEHICLE_LIST = new ArrayList<>();

  public static final List<Customer> CUSTOMERS = new ArrayList<>();

  public static void generateVehiclesAndCustomers() {
    VEHICLE_LIST.add(new Car(2020, "Mercedes C-200", BigDecimal.valueOf(300), true));
    VEHICLE_LIST.add(new Car(2018, "Toyota Corolla", BigDecimal.valueOf(250), true));
    VEHICLE_LIST.add(new Car(2019, "Egea Cross", BigDecimal.valueOf(200), false));
    VEHICLE_LIST.add(new Car(2018, "Clio Symbol", BigDecimal.valueOf(150), false));
    VEHICLE_LIST.add(new Truck(2022, "Kamaz", BigDecimal.valueOf(5000)));
    VEHICLE_LIST.add(new Truck(2022, "Mercedes Unimog", BigDecimal.valueOf(6000)));
    VEHICLE_LIST.add(new Truck(2022, "Ford Cargo", BigDecimal.valueOf(4000)));
    VEHICLE_LIST.add(new Van(2023, "Mercedes Benz Vito", BigDecimal.valueOf(500)));
    VEHICLE_LIST.add(new Van(2022, "Mercedes Benz Vito", BigDecimal.valueOf(400)));

    CUSTOMERS.add(new Customer("Asım", "Kılıç", 7));
    CUSTOMERS.add(new Customer("Ahmet", "Gültekin", 1));
    CUSTOMERS.add(new Customer("Selika", "Tuğ", 4));
    CUSTOMERS.add(new Customer("Elif", "Erol", 3));
    CUSTOMERS.add(new Customer("Sena", "Çaprazlı", 2));
  }

  public static void rentVehicle(String vehicleId, String customerId, int countOfDay) {
    Vehicle vehicle = null;
    Customer customer = null;
    for (Vehicle v : VEHICLE_LIST) {
      if (v.getVehicleId().equals(vehicleId)) {
        vehicle = v;
        break;
      }
    }

    for (Customer c : CUSTOMERS) {
      if (c.getCustomerId().equals(customerId)) {
        customer = c;
        break;
      }
    }
    if (vehicle != null && customer != null) {
      vehicle.rent(customer, countOfDay);
    } else {
      System.out.println("Hatalı bilgi girişi!!");
    }
  }

  public static List<Vehicle> availableVehicles(VehicleType vehicleType) {
    List<Vehicle> availableVehicleList = new ArrayList<>();

    for (var vehicle : VEHICLE_LIST) {
      if (vehicle.isAvailable()) {
        availableVehicleList.add(vehicle);
      }
    }
    var filteredVehicles = filterVehicles(vehicleType, availableVehicleList);
    return filteredVehicles;
  }

  public static List<Vehicle> filterVehicles(VehicleType vehicleType, List<Vehicle> availableVehicles) {
    if (vehicleType == null) {
      return availableVehicles;
    }
    List<Vehicle> filteredVehicleList = new ArrayList<>();
    for (var vehicle : availableVehicles) {
      if (vehicle.getVehicleType() == vehicleType) {
        filteredVehicleList.add(vehicle);
      }
    }
    return filteredVehicleList;
  }

  public static List<Vehicle> rentedVehicles() {
    List<Vehicle> rentedVehicles = new ArrayList<>();
    for (var vehicle : VEHICLE_LIST) {
      if (!vehicle.isAvailable()) {
        rentedVehicles.add(vehicle);
      }
    }
    return rentedVehicles;
  }

  public static void returnVehicle(String vehicleId) {
    Vehicle vehicle = null;
    for (var v : VEHICLE_LIST) {
      if (v.getVehicleId().equals(vehicleId)) {
        vehicle = v;
        break;
      }
    }
   if(vehicle !=null){
     vehicle.returnVehicle();
   }
  }
}
