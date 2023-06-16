import java.util.Map;

public class Order {

  private String orderId;

  private Map<Product, Integer> productQuantityMap;

  private double totalAmount;

  public Order(String orderId, Map<Product, Integer> productQuantityMap) {
    this.orderId = orderId;
    this.productQuantityMap = productQuantityMap;
    this.totalAmount = calculateTotalAmount();
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Map<Product, Integer> getProductQuantityMap() {
    return productQuantityMap;
  }

  public void setProductQuantityMap(Map<Product, Integer> productQuantityMap) {
    this.productQuantityMap = productQuantityMap;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount() {
    this.totalAmount = calculateTotalAmount();
  }

  public void getOrderDetails() {
    productQuantityMap.forEach((key, value) -> System.out.println("Quantity: " + value + " " + key));
  }

  private double calculateTotalAmount() {
    return productQuantityMap.entrySet().stream()
                             .mapToDouble(entrySet -> entrySet.getKey().getPrice() * entrySet.getValue())
                             .sum();
  }
}
