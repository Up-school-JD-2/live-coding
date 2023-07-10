package h;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

  private static int counter = 0;

  private static Lock lock = new ReentrantLock(true);

  private static void increment() {
    try {
      lock.lock();
      for (int i = 0; i < 10000; i++) {
        counter++;
      }
    } finally {
      add();
    }
  }
  public static void add(){
    lock.unlock();
  }

  public static void main(String[] args) {
    Thread t1 = new Thread(App::increment);
    Thread t2 = new Thread(App::increment);
    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println(counter);
  }
}
