import java.math.BigDecimal;
import java.text.MessageFormat;

public class Truck extends Vehicle {

  public Truck(int year, String brand, BigDecimal pricePerDay) {
    super(year, brand, VehicleType.TRUCK, pricePerDay);
  }

  @Override
  public void rent(Customer customer, int countOfRentDay) {
    if (countOfRentDay < DomainConstants.MINIMUM_TRUCK_RENT_DAY_COUNT) {
      var message = MessageFormat.format("{0} için minimum kiralama süresi {1} gündür",
                                         getVehicleType().name(),
                                         DomainConstants.MINIMUM_TRUCK_RENT_DAY_COUNT);
      System.out.println(message);
      return;
    }
    super.rent(customer, countOfRentDay);
  }

  @Override
  public void returnVehicle() {
    setAvailable(true);
    setCustomer(null);
    setTotalPrice(BigDecimal.ZERO);
  }
}
