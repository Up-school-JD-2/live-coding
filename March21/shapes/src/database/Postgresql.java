package database;

public class Postgresql extends Database {

  @Override
  public void save() {
    super.save();
    System.out.print("kaydedildi.");
  }

  @Override
  public void delete() {
    System.out.println("PostgreSQL veritabanından silindi.");
  }
}
