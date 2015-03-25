import java.util.concurrent.atomic.AtomicBoolean;


public class TestPerformance {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    AtomicBoolean progressUpdate = new AtomicBoolean(false);
    long recordCtr = 0;
    long progressBar = 100;
    long b = System.currentTimeMillis();
    for (int i = 0; i < 6000000; i ++) {
      if (((recordCtr++) % progressBar) == 0) {
        progressUpdate.set(true);
      }
    }
    long e =  System.currentTimeMillis();
    System.out.println(e - b);
  }

}
