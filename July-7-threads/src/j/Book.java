package j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

  private int id;

  private Lock lock;

  public Book(int id) {
    this.id = id;
    this.lock = new ReentrantLock(true);
  }

  // TTL  => time to live
  // couchbase redis
  public void read(Student student) throws InterruptedException {
    //lock.lock();
    if (lock.tryLock(2, TimeUnit.SECONDS)) {
      System.out.println(student + " starts reading" + this);
      Thread.sleep(2000);
      System.out.println(student + " has just finished reading " + this);
      lock.unlock();
    }
  }

  @Override
  public String toString() {
    return "Book" + id;
  }
}
