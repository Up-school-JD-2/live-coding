package g;

import java.util.ArrayList;
import java.util.List;

public class App {

  public static void main(String[] args) {
    Processor processor = new Processor();
    Thread t1 = new Thread(() -> {
      try {
        processor.producer();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    Thread t2 = new Thread(() -> {
      try {
        processor.consumer();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    t1.start();
    t2.start();
  }
}

class Processor {

  private List<Integer> list = new ArrayList<>();

  private static final int UPPER_LIMIT = 20;

  private static final int LOWER_LIMIT = 0;

  private int value = 0;

  private final Object lock = new Object();

  public void producer() throws InterruptedException {
    synchronized (lock) {
      while (true) {
        if (list.size() == UPPER_LIMIT) {
          System.out.println("Waiting for remove items.");
          lock.wait();
        } else {
          System.out.println("Adding: " + value);
          list.add(value);
          value++;
          lock.notify();
        }
        Thread.sleep(500);
      }
    }
  }

  public void consumer() throws InterruptedException {
    synchronized (lock) {
      while (true) {
        if (list.size() == LOWER_LIMIT) {
          System.out.println("Waiting for adding items.");
          lock.wait();
        } else {
          System.out.println("Removing : " + list.remove(list.size() - 1));
          value--;
          lock.notify();
        }
        Thread.sleep(500);
      }
    }
  }
}