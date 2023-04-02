public class Basket {

  private Product[] products = new Product[DomainConstants.BASKET_OPENING_COUNT];

  private int itemCount = 0;

  private Double totalPrice = 0d;

  public void addProduct(Product product) {
    if (itemCount == products.length - 1) {
      reInitializeArray();
    }
    products[itemCount] = product;
    itemCount++;
    totalPrice += product.getPrice();
  }

  private void reInitializeArray() {
    Product[] newProductArray = new Product[products.length + DomainConstants.BASKET_INCREASE_COUNT];
    System.arraycopy(products, 0, newProductArray, 0, products.length);
    products = newProductArray;
  }

  public void listBasket() {
    if (products == null) {
      return;
    }
    System.out.println("----- Basket List ------");
    for (int i = 0; i < itemCount; i++) {
      String productInfo =
          products[i].getClass().getName() + " " + products[i].getName() + " " + products[i].getPrice();
      System.out.println(productInfo);
    }
    //System.out.println("Total price: " + totalPrice);
    System.out.printf("Total price: %.2f \n", totalPrice);
  }
}
