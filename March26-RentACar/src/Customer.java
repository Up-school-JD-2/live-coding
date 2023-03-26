import java.util.UUID;

public class Customer {

  private final String customerId; // UUID

  private String name;

  private String surname;

  private int licenceYear;

  public Customer(String name, String surname, int licenceYear) {
    this.customerId = UUID.randomUUID().toString();
    this.name = name;
    this.surname = surname;
    this.licenceYear = licenceYear;
  }

  public String getCustomerId() {
    return customerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public int getLicenceYear() {
    return licenceYear;
  }

  public void setLicenceYear(int licenceYear) {
    this.licenceYear = licenceYear;
  }

  @Override
  public String toString() {
    return "Customer{" +
           "customerId='" + customerId + '\'' +
           ", name='" + name + '\'' +
           ", surname='" + surname + '\'' +
           ", licenceYear=" + licenceYear +
           '}' + '\n';
  }

  //public void deneme(){
  //  Customer customer = new Customer("a","asd",5);
  //  System.out.println(customer); // Customer@7564sbc4
  //}
}
