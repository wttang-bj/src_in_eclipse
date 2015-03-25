import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;


public class NIO {

  /**
   * @param args
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    // TODO Auto-generated method stub
    RandomAccessFile fromFile = new RandomAccessFile("D://input.txt", "rw");
    FileChannel      fromChannel = fromFile.getChannel();
    System.out.println("channel size: " + fromChannel.size());
  }

}
