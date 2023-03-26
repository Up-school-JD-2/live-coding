import java.math.BigDecimal;

public class Car extends Vehicle {

  private boolean hasChildSeat;

  private Customer secondCustomer;

  public Car(int year, String brand, BigDecimal pricePerDay, boolean hasChildSeat) {
    super(year, brand, VehicleType.CAR, pricePerDay);
    this.hasChildSeat = hasChildSeat;
  }

  @Override
  public void returnVehicle() {
    setAvailable(true);
    setCustomer(null);
    secondCustomer = null;
    setTotalPrice(BigDecimal.ZERO);
  }

  public void addSecondCustomer(Customer secondCustomer) {
    if (getCustomer() == secondCustomer) {
      System.out.println("Lütfen farklı bir sürücü seçiniz.");
      return;
    }
    this.secondCustomer = secondCustomer;
    var finalPrice = getTotalPrice().add(BigDecimal.valueOf(100));
    setTotalPrice(finalPrice);
  }

  public boolean isHasChildSeat() {
    return hasChildSeat;
  }

  public void setHasChildSeat(boolean hasChildSeat) {
    this.hasChildSeat = hasChildSeat;
  }

  public Customer getSecondCustomer() {
    return secondCustomer;
  }
}
