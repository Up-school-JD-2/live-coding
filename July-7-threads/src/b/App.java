package b;

public class App {

  public static void main(String[] args) {
    Thread t1 = new Thread(new Runner1());
    Thread t2 = new Thread(new Runner2());

    t1.start();
    System.out.println("t1 sonraki satır");
    t2.start();


    Thread t3 =  new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        System.out.println("Runner1 1 " + i);
      }
    });
  }
}

class Runner1 implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Runner1 1 " + i);
    }
  }
} // time slicing

class Runner2 implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Runner2 2" + i);
    }
  }
}