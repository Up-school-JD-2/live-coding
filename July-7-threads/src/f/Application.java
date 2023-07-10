package f;

public class Application {

  public static void main(String[] args) {
    Process process = new Process();
    Process process2 = new Process();
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          process.produce();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          process.consume();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });
  }
}

class Process {

  public   void produce() throws InterruptedException {
    // get images
    // send email
    synchronized (this){
      System.out.println("Running the produce method.");
    }

  }

  public void consume() throws InterruptedException {
    System.out.println("Running the consume method.");
  }
}
