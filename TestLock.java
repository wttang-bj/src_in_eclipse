import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Category;


public class TestLock {
  public static void main(String [] args) {
    ReadWriteLock lock = new ReentrantReadWriteLock();
    
    for (int i = 0; i<2; i++) {
      lock.readLock().lock();
      try {        
        System.out.println("Round#" + i + ": Locked, in Try");
        if (i == 1) {
          System.out.println("Round#" + i + ": break from loop");
          return;
        }
      } finally {
        System.out.println("Round#" + i + ": in finally: unlock");
        lock.readLock().unlock();
      }
      
    }
    
  }

}
