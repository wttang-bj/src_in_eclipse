
public class TestArray {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    testIntArray();
    testStringArray();
    testJustClone();
  }

   static void testIntArray() {
     int [] [] [] src = new int[2][3][4];
     for (int i = 0; i < src.length; i++) {
       for (int j = 0; j < src[i].length; j ++) {
         for (int k = 0; k < src[i][j].length; ++k) {
           src[i][j][k] = Integer.parseInt((""+ i + j + k));
         }
       }
     }
     
     int [][][] dst = new int[src.length][][];
     
     //copy
     for (int i = 0; i < src.length; i++) {
       dst[i] = new int[src[i].length][];
       for (int j = 0; j < src[i].length; j ++) {
           dst[i][j] = src[i][j].clone();
       }
     }
     dst[0][0][0]=-111;
     dst[1][2][3]=-222;
     dst=null;
   }
   static void testStringArray() {
     String [] [] [] src = new String[2][3][4];
     for (int i = 0; i < src.length; i++) {
       for (int j = 0; j < src[i].length; j ++) {
         for (int k = 0; k < src[i][j].length; ++k) {
           src[i][j][k] = ((""+ i + j + k));
         }
       }
     }
     
     String [][][] dst = new String[src.length][][];
     for (int i = 0; i < src.length; i++) {
       dst[i] = new String[src[i].length][];
       for (int j = 0; j < src[i].length; j ++) {
           dst[i][j] = src[i][j].clone();
       }
     }
     
     dst[0][0][0]="-111";
     dst[1][2][3]="-222";
     dst=null;
   }
   
   static void testJustClone() {
     String [] [] [] src = new String[2][3][4];
     for (int i = 0; i < src.length; i++) {
       for (int j = 0; j < src[i].length; j ++) {
         for (int k = 0; k < src[i][j].length; ++k) {
           src[i][j][k] = ((""+ i + j + k));
         }
       }
     }
     String [][][] dst = src.clone();  
     dst[0][0][0]="-111";
     dst[1][2][3]="-222";
     String a = "aaa";
     String b = a;
     b ="bbb";
     
     int i = Integer.MAX_VALUE;
     System.out.println(Integer.toBinaryString(-i));
     String got = Integer.toBinaryString(i);
     int length = got.length();
     if (length <32) {
       for (int j =0; j < (32-length); ++j) {
         System.out.print("0");
       }
     }
     System.out.println(got);
     
     System.out.println(Integer.MIN_VALUE + " " + Integer.toBinaryString(Integer.MIN_VALUE));
     System.out.println(Integer.MAX_VALUE + "  " + Integer.toBinaryString(Integer.MAX_VALUE));
     System.out.println(a.equals(b));
   }
}
