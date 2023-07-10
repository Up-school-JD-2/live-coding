package a;

public class App {

  public static void main(String[] args) {
    System.out.println("Hello World!");
    System.out.println("Hello World 1");
    System.out.println("Hello World 2");

    //downloadImage()
    //showImages()
    Runner1 runner1 = new Runner1();
    Runner2 runner2 = new Runner2();

    runner1.execute();
    runner2.execute();
  }
}

class Runner1 {
  public void execute() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Runner1 " + i);
    }
  }
} // time slicing

class Runner2 {

  public void execute() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Runner2 " + i);
    }
  }
}