import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductManager {

  private Map<String, Product> products;  // veritabanı

  // ŞOK => "101"
  // ÜLKER=> "102"
  private Map<String, Supplier<String>> orderNumberSuppliers;

  private List<Order> orders;

  public ProductManager() {
    products = new HashMap<>();
    orderNumberSuppliers = new HashMap<>();
    orders = new ArrayList<>();
  }

  public void addProduct(Product product) {
    products.put(product.getId(), product);
  }

  public Product getProductById(String productId) {
    return products.get(productId);
  }

  public List<Product> filterProducts(Predicate<Product> filterPredicate) {
    // products.values().stream().filter(product->product.getName().startsWith("A")).toList();
    return products.values().stream().filter(filterPredicate).toList();
  }

  // BiConsumer
  // manager.updateStock("1", 20, (product, quantity) -> {
  //  int newStock = product.getStock() + quantity;
  //  product.setStock(newStock);
  //});
  public void updateStock(String productId, int quantity, BiConsumer<Product, Integer> updateFunction) {
    Product productById = getProductById(productId);
    if (productById != null) {
      updateFunction.accept(productById, quantity);
      System.out.println("Stock updated successfully");
    } else {
      System.out.println("Product not found");
    }
  }

  // calculate total value
  //double totalValue = manager.calculateTotalValue(product -> product.getPrice() * product.getStock());
  public double calculateTotalValue(Function<Product, Double> valueFunction) {
    return products.values().stream().mapToDouble(valueFunction::apply).sum();
  }

  public void registerOrderNumberSupplier(String supplierId, Supplier<String> supplier) {
    orderNumberSuppliers.put(supplierId, supplier);
  }

  //generate Order number
  public String generateOrderNumber(String supplierId) {
    Supplier<String> supplier = orderNumberSuppliers.get(supplierId);
    if (supplier != null) {
      return supplier.get();
    } else {
      return "Supplier not found";
    }
  }

  public void isimYaz() {
    System.out.println("Asım");
  }

  // 0001  1  10
  // 0001  1  10
  // 0001  1  0
  // OrderItem  -> String productId, Integer quantity;
  public void processOrder(String orderId, Map<String, Integer> orderItems,
                           BiConsumer<Product, Integer> updateStockFunction) {
    Map<Product, Integer> productQuantityMap = new HashMap<>();
    for (Map.Entry<String, Integer> entry : orderItems.entrySet()) {
      String productId = entry.getKey();
      Integer quantity = entry.getValue();
      Product product = getProductById(productId);
      if (product != null) {
        updateStock(productId, quantity, updateStockFunction);
        productQuantityMap.compute(product, (key, value) -> {
          if (value == null) {
            return quantity;
          } else {
            return value + quantity;
          }
        });
      }
    }
    Order order = new Order(orderId, productQuantityMap);
    orders.add(order);

    System.out.println("Order processed successfully. Order ID: " + order.getOrderId());
    System.out.println("Ordered products:");
    order.getOrderDetails();
    System.out.println("Total Amount: " + order.getTotalAmount());
  }

  public List<Product> getActiveProductsSortedByPrice() {
    // ProductStatus'ü ACTIVE olan ürünleri fiyatlarına göre sıralayıp döndüren metodu yazın
    return filterProducts(product -> ProductStatus.ACTIVE.equals(product.getProductStatus())).stream()
                                                                                             .sorted(
                                                                                                 Comparator.comparingDouble(
                                                                                                     Product::getPrice))
                                                                                             .toList();

    // Comparator<Product> comparator = (product1, product2) -> Double.compare(product1.getPrice(), product2.getPrice
    // ());
  }

  public double calculateAveragePriceInCategory(String category) {
    // String olarak verilen category'e ait olan ürünlerin fiyatlarının ortalamasını yoksa 0.0 döndüren metodu yazın
    // tip: OptionalDouble kullanımını inceleyin.
    // Optional<Product> optionalProduct = Optional.of(new Product());
    //Optional.empty();
    //OptionalDouble optionalDouble = OptionalDouble.of(3.14);

    // Yöntem 1
    // List<Product> filteredProducts = filterProducts(product -> product.getCategory().equalsIgnoreCase(category));
    // OptionalDouble average = filteredProducts.stream().mapToDouble(Product::getPrice).average();
    //Yöntem  2
    OptionalDouble average = products.values()
                                     .stream()
                                     .filter(product -> product.getCategory().equalsIgnoreCase(category))
                                     .filter(product -> product.getStock() > 0)
                                     .mapToDouble(Product::getPrice)
                                     .average();
    //if(average.isPresent()){
    //  return average.getAsDouble();
    //}else{
    // return 0.0;
    //}
    return average.orElse(0.0);

    //return average.orElseThrow(()->new RuntimeException("Aranılan ürün bulunamadı"));
  }

  public Map<String, Double> getCategoryPriceSum() {
    // category'lere göre gruplayıp, her bir kategoride bulunan ürünlerin toplam fiyatını stream ile hesaplayıp
    // döndüren metodu yazın
    // örn:
    // category-1 105.2
    // category-2 45.0
    // product1  - category1
    // product1  - category1

    return products.values().stream()
                   //.collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));
                   .collect(Collectors.groupingBy(Product::getCategory,
                                                  Collectors.summingDouble(
                                                      p -> p.getPrice() * p.getStock()))); // (p,p1)->  {p.getPric}

    //return null;
  }

  // kazak -  {id:1, name:kazak, price:20

  public void temp() {
    Map<String, Product> collect =
        products.values().stream().collect(Collectors.toMap(Product::getName, Function.identity(), (p1, p2) -> {
          if (p1.getStock() > p2.getStock()) {
            return p1;
          } else {
            return p2;
          }
        }));
  }

  public void grouping2(){
    Map<String, List<Product>> collect =
        products.values().stream().collect(Collectors.groupingBy(Product::getCategory));
  }
  public void mapTemp(){
    List<String> collect = products.values().stream().map(Product::getCategory).distinct().toList();
  }
}
