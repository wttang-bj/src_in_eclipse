
public class TestArray {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.arr
  }

   void testIntArray() {
     int [] [] [] src = new int[2][3][4];
     for (int i = 0; i < src.length; i++) {
       for (int j = 0; j < src[i].length; j ++) {
         for (int k = 0; k < src[i][j].length; ++k) {
           src[i][j][k] = Integer.parseInt((""+ i + j + k));
         }
       }
     }
     
     int [][][] dst = new int[src.length][][];
   }
}
