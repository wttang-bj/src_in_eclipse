import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Random;


public class TestOperf {

  /**
   * @param args
   * @throws IOException 
   */
  public static void main(String[] args) throws IOException {
    int rec = 100000;
    if (args.length == 2) {
      rec = Integer.parseInt(args[1]);
    }
    System.out.println("Press any key to continue");
    DataInputStream in = 
        new DataInputStream( 
          new BufferedInputStream(System.in));
    in.readLine();
    calc(rec);
    
  }
  
  static void calc (int rec) {
    double ret = 1;
    Random r = new Random();
    for (int i=0; i< rec; i++) {
      ret = ret + r.nextDouble() + r.nextInt(10) ;
    }
    System.out.println(ret);
  }

}
