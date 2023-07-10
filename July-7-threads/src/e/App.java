package e;

public class App {

  public static void main(String[] args) {
    Thread t1 = new Runner1();
    Thread t2 = new Runner2();

    t1.setPriority(Thread.MAX_PRIORITY);
    t2.setPriority(Thread.MIN_PRIORITY);
    t1.start();
    System.out.println("t1 başladı");
    t2.start();
    System.out.println("t2 başladı");

    try {
      t1.join();

      t2.join();

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("threadler bitti");
  }
}

class Runner1 extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      //try {
      //  //Thread.sleep(500);
      //} catch (InterruptedException e) {
      //  e.printStackTrace();
      //}
      System.out.println("Runner1 1 " + i);
    }
  }
} // time slicing

class Runner2 extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      //try {
      //  Thread.sleep(500);
      //} catch (InterruptedException e) {
      //  e.printStackTrace();
      //}
      System.out.println("Runner2 2 " + i);
    }
  }
}