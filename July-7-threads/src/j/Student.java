package j;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Student implements Runnable {

  private int id;

  private Book[] books;

  private Random random;

  public Student(int id, Book[] books) {
    this.id = id;
    this.books = books;
    this.random = new Random();
  }

  @Override
  public void run() {
    while (true) {
      //int bookId = random.nextInt(Constants.NUMBER_OF_BOOKS);
      int bookId = ThreadLocalRandom.current().nextInt(0, Constants.NUMBER_OF_BOOKS);
      try {
        books[bookId].read(this);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public String toString() {
    return "Student #" + id;
  }
}
