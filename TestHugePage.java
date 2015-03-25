import java.io.BufferedInputStream;
import java.io.DataInputStream;


public class TestHugePage {

 
  public native static void allocateHP(long size);

  public static void main(String [] args) throws Exception {
    
    System.loadLibrary("TestHP");

    long size = 512 * 1024 * 1024;
    if (args.length == 2) {
      size = Integer.parseInt(args[1]);
    }
    System.out.println("will allocate " + size + "byte HugePage");
    DataInputStream in = 
        new DataInputStream( 
          new BufferedInputStream(System.in)); 
    int i = in.readInt();
    
    TestHugePage.allocateHP(size);
    i = in.readInt();

  }

}
