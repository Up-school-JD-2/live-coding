import java.util.Objects;

public class Product {

  private String id;

  private String name;

  private int stock;

  private double price;

  private String category;

  private ProductStatus productStatus;

  public Product() {

  }

  public Product(String id, String name, int stock, double price, String category, ProductStatus productStatus) {
    this.id = id;
    this.name = name;
    this.stock = stock;
    this.price = price;
    this.category = category;
    this.productStatus = productStatus;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public ProductStatus getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(ProductStatus productStatus) {
    this.productStatus = productStatus;
  }

  @Override
  public String toString() {
    return "Product{" +
           "id='" + id + '\'' +
           ", name='" + name + '\'' +
           ", stock=" + stock +
           ", price=" + price +
           ", category='" + category + '\'' +
           ", productStatus=" + productStatus +
           '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return stock == product.stock
           && Double.compare(product.price, price) == 0
           && Objects.equals(id, product.id)
           && Objects.equals(name, product.name)
           && Objects.equals(category, product.category)
           && productStatus == product.productStatus;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, stock, price, category, productStatus);
  }
}
