import java.util.EnumSet;

public enum VehicleType {
  TRUCK(5, "Bu aracı kiralamak için minimum ehliyet yaşı 5'tir"),
  VAN(4, "Bu aracı kiralamak için minimum ehliyet yaşı 4'tir"),
  CAR(2, "Bu aracı kiralamak için minimum ehliyet yaşı 2'tir");

  private final int minLicenceYear;

  private final String errorMessage;

  VehicleType(int minLicenceYear, String errorMessage) {
    this.minLicenceYear = minLicenceYear;
    this.errorMessage = errorMessage;
  }

  public int getMinLicenceYear() {
    return minLicenceYear;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
  // "car"

  public static VehicleType getVehicleType(String type) {
    var vehicleTypes = EnumSet.allOf(VehicleType.class);
    for (VehicleType vehicleType : vehicleTypes) {
      if (vehicleType.name().equalsIgnoreCase(type)) {
        return vehicleType;
      }
    }
    return null;
  }
}
