import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

import org.apache.hadoop.io.WritableUtils;


public class InputUtil {

  /**
   * @param args
   * @throws IOException 
   */
  public static void main(String[] args) throws IOException {
    genInputFile();

  }
  
  public static void readInputFile() throws IOException {
    Random r = new Random();
    
    File f = new File("D:\\input.txt");
    RandomAccessFile in = new RandomAccessFile(f, "rw");
    for (int i = 0; i <recordNum; i++) {
      System.out.println(WritableUtils.readVInt(in));
    }
  }
  
  static final long recordNum = 999999;
  public static void genInputFile() throws IOException{
    Random r = new Random();
    
    File f = new File("D:\\input.txt");
    RandomAccessFile out = new RandomAccessFile(f, "rw");
    out.writeChars(""+ r.nextInt(3211));
    for(int i = 0; i < recordNum; i++) {
      int gen = r.nextInt(1000);
      System.out.println(gen);
      WritableUtils.writeVInt(out, gen);
    }
    out.close();
  }

}
