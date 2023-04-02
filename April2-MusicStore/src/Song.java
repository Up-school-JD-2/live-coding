public class Song extends Product {

  private Album album;

  public Song(Long id, String name, String artistName, String releaseDate, Double duration, Double price) {
    super(id, name, artistName, releaseDate, duration, price);
  }

  public Album getAlbum() {
    return album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }

}
