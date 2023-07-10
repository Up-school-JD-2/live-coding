package f;

public class App {

  public static int counter1 = 0;
  public static int counter2 = 0;

  private static final Object lock1 = new Object();
  private static final Object lock2 = new Object();

  // thread safe
  // monitor (intrinsic) lock
  public  static  void increment1() {
   // başka işlemler
     synchronized (lock1){
       counter1++;


     }

  }
  // DATABASE
  //  d1 d2 d3 d4  (this)
  public static synchronized void increment2() {
    synchronized (lock2) {
      counter2++;
    }
  }


  public static void main(String[] args) {
    process();
  }

  public static void process() {
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 15000; i++) {
        //counter++;
        increment1();
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 15000; i++) {
        // counter++;
        increment2();
      }
    });
    t1.setDaemon(true);
    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    //System.out.println("The counter is : " + counter);
    Thread.currentThread().getName();
  }
}

/*
    Deamon Thread (Helper, GC)  vs Worker Thread
    - main thread çalışmasının bitmesi beklenen son thread'dir.

    Deamon Threads:
    - low priority
    - arka planda çalışır- gc
    - genelde Input/output veya servislerde kullanılır
      * NFC - Bluetooth araması
     - JVM tarafından diğer worker threadler sonlandığında,
        deamon thread'de otomatik sonlandırılır

     Thread priority-> FIFS => First-in-first-served manner

     thread'lerde memory management yapmamız gerekiyor.

    Synchronization'ın temel amacı paylaşımlı kaynaklara eş zamanlı erişimi engellemek.

Object Level locking (this)

Class Level locking (App.class)
- Burada obje ile değil class'ın kendisiyle ilişkili lock alıyoruz.
- Bır sınıfın tüm örnekleri(instance) üzerinde gerçekleştirilen bir kilit mekanizmasıdır.
- Aynı sınıftan birden fazla obje oluşturulsa bile herhangi birisine bir t anında ancak
  1 thread girebilir.

  Sleep ve wait farkı
  - wait objeden çağırılır, Sleep Thread üzerinden
  - wait interrupt edilebilir ama sleep yapamaz
  - wait(notify) bir synchronize blokta olmalı, sleep'e gerek yok
  - Sleep lock'u salmaz, wait lock'u bırakır.
 */