public class User {

  private final Long id;

  private String fullName;

  private final Basket basket;

  public User(Long id, String fullName, Basket basket) {
    this.id = id;
    this.fullName = fullName;
    this.basket = basket;
  }

  public Long getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public Basket getBasket() {
    return basket;
  }

  @Override
  public String toString() {
    return "User{" +
           "id=" + id +
           ", fullName='" + fullName + '\'' +
           '}' + '\n';
  }
}
