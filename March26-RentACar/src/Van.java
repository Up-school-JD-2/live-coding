import java.math.BigDecimal;

public class Van extends Vehicle{

  public Van(int year, String brand,  BigDecimal pricePerDay) {
    super(year, brand, VehicleType.VAN, pricePerDay);
  }

  @Override
  public void returnVehicle() {
    setAvailable(true);
    setCustomer(null);
    setTotalPrice(BigDecimal.ZERO);
  }
}
