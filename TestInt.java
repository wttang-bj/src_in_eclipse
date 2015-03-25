
public class TestInt {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println((System.getenv("dsdf")));
    int pos = 2147418112;
    int len = 5,
        num = 32768,
        bufSize = 65536; // total size  2147483648
                         // pos         2147418112, pos is smaller than total buff by 
    System.out.println("((pos + len) <= num * bufSiz: " + (pos + len) + " <= " + 
                         + num * bufSize + " "+((pos + len) <= num * bufSize));
    if ((pos + len) <= num * bufSize) {
      System.out.println("here");
      return ;
    }
    num++;
    int ret = pos + len - (num - 1) * bufSize;
    System.out.println(ret);
    
   // String testIntS = "123s";
    
   // int i = Integer.parseInt(testIntS);
    
    System.out.println((32 - Integer.numberOfLeadingZeros(300000)) << 2);
    System.out.println(Integer.highestOneBit(17));
        
        
  }

}
