import java.sql.Timestamp;


public class TestAlloc {

  /**
   * @param args
   */
  @SuppressWarnings("deprecation")
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    long beg = System.currentTimeMillis();
    byte[] buf = new byte[100 <<20];
    byte [] buf2 = new byte[100<<20];
    
    System.out.println(System.currentTimeMillis() - beg);
    Timestamp.valueOf("");
    new Timestamp(0, 0, 0, 0, 0, 0, 0);
    
  }

}
