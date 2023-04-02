public class Album extends Product {

  private final Song[] songs;

  public Album(Long id, String name, String artistName, String releaseDate, Double duration, Double price,
               Song[] songs) {
    super(id, name, artistName, releaseDate, duration, price);
    this.songs = songs;
    for (Song song : songs) {
      song.setAlbum(this);
    }
  }

  public Song[] getSongs() {
    return songs;
  }

}
