public abstract class Product {

  private final Long id;

  private final String name;

  private final String artistName;

  private final String releaseDate;

  private final Double duration;

  private final Double price;

  public Product(Long id, String name, String artistName, String releaseDate, Double duration, Double price) {
    this.id = id;
    this.name = name;
    this.artistName = artistName;
    this.releaseDate = releaseDate;
    this.duration = duration;
    this.price = price;
  }

  public void purchase(User user) {
    user.getBasket().addProduct(this);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getArtistName() {
    return artistName;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public Double getDuration() {
    return duration;
  }

  public Double getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "# " + this.getClass().getName() + " " +
           "Product{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", artistName='" + artistName + '\'' +
           ", releaseDate='" + releaseDate + '\'' +
           ", duration=" + duration +
           ", price=" + price +
           '}';
  }
}
