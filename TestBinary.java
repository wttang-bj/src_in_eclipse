
public class TestBinary {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println(Integer.toBinaryString(0x55555555));
    check(320);
    check(64);
    check(32);
    System.out.println(((Unsigne int)0xffffffff+0x00000001)/2);
  }
  
  static void check(int num) {
    System.out.println("" + num + ": 2µÄÃÝ- " +check2(num) + ", 4µÄÃÝ-" +
    check4(num));
  }
  
  public static boolean check4(int num)
  {
      if (!check2(num)) return false;
      return (num & 0x55555555)>0;
  }
  
  public static boolean check2(int x) {
    return x > 0 && (x & (x - 1)) == 0;
  }

}

