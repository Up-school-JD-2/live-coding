import java.math.BigDecimal;
import java.util.UUID;

public abstract class Vehicle {

  private final String vehicleId; // readonly

  private int year;

  private String brand;

  private VehicleType vehicleType;

  private BigDecimal pricePerDay;

  private boolean isAvailable = true;

  private Customer customer;

  private BigDecimal totalPrice = BigDecimal.ZERO;

  private int countOfRentDay;

  public Vehicle(int year, String brand, VehicleType vehicleType, BigDecimal pricePerDay) {
    this.vehicleId = UUID.randomUUID().toString();
    this.year = year;
    this.brand = brand;
    this.vehicleType = vehicleType;
    this.pricePerDay = pricePerDay;
  }

  public void rent(Customer customer, int countOfRentDay) {
    this.countOfRentDay = countOfRentDay;
    if (!isAvailable) {
      System.out.println(vehicleId + " numaralı araç şuan müsait değil!");
      return;
    }
    if (customer.getLicenceYear() < vehicleType.getMinLicenceYear()) {
      System.out.println(vehicleType.getErrorMessage());
      return;
    }
    // new BigDecimal("5");
    // totalPrice = 50;
    // totalPrice = 50+ 1500;

    var priceForRent = pricePerDay.multiply(BigDecimal.valueOf(countOfRentDay));
    totalPrice = totalPrice.add(priceForRent);
    isAvailable = false;
    System.out.println(vehicleId
                       + " numaralı araç, "
                       + customer.getName()
                       + " adına kiralanmıştır, toplam fiyat: "
                       + totalPrice
                       + "₺");
  }

  public abstract void returnVehicle();

  public String getVehicleId() {
    return vehicleId;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public BigDecimal getPricePerDay() {
    return pricePerDay;
  }

  public void setPricePerDay(BigDecimal pricePerDay) {
    this.pricePerDay = pricePerDay;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public int getCountOfRentDay() {
    return countOfRentDay;
  }

  public void setCountOfRentDay(int countOfRentDay) {
    this.countOfRentDay = countOfRentDay;
  }

  @Override
  public String toString() {
    return "Vehicle{" +
           "vehicleId='" + vehicleId + '\'' +
           ", year=" + year +
           ", brand='" + brand + '\'' +
           ", vehicleType=" + vehicleType +
           ", pricePerDay=" + pricePerDay +
           ", isAvailable=" + isAvailable +
           ", customer=" + customer +
           ", totalPrice=" + totalPrice +
           ", countOfRentDay=" + countOfRentDay +
           '}' + '\n';
  }
}
