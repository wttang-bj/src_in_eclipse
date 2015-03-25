import java.io.IOException;
import java.nio.ByteBuffer;
import sun.nio.ch.DirectBuffer;


public class TestBuffer {

  /**
   * @param args
   * @throws IOException 
   * @throws InterruptedException 
   */
  public static void main(String[] args) throws IOException, InterruptedException {
//    RandomAccessFile fileStream = new RandomAccessFile("C:\\test.tct", "rw");
//    FileChannel fileChannel = fileStream.getChannel();
//    // Get a mmap region from the file channel
//    MappedByteBuffer mappedByteBuf = fileChannel.map(FileChannel.MapMode.READ_WRITE,
//        0, 10);
//    byte[] buf = new byte[10];
//    buf[5] = 0x45;
//    buf[0] = 0x46;
//    mappedByteBuf.put(buf, 0, 10);
//    mappedByteBuf.clear();
//    fileChannel.close();
//    fileStream.close();
//    System.out.println("finish");
    ByteBuffer db =  ByteBuffer.allocateDirect(Integer.parseInt(args[0])*1024*1024);
    //((DirectBuffer)db).cleaner().clean();

    System.out.println("There");
    System.out.println("Limit - " + db.limit());
    db.position(Integer.parseInt(args[0])*1024*1024-10);
    byte b = 1;
    db.put(b);
    System.out.println("filled");
    Thread.sleep(60000);
    System.out.println("Here");
  }

}
