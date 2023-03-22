package database;

public class Main {

  public static void main(String[] args) {
    Database postgre = new Postgresql();
    Database mysql = new Mysql();
    if("A" == "TR"){
      register(postgre);
    }else{
      register(mysql);
    }

  }

  public static void register(Database database){
    database.save();
  }
}
