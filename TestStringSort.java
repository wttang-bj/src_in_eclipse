import java.nio.charset.Charset;
import java.text.CollationKey;
import java.text.Collator;

import sun.misc.Compare;
import sun.misc.Sort;


public class TestStringSort {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String [] sList = new String[] {"ÌÆÎÄèº", "Wentao Tang", "Xiaojie Li", "Qin Na",
        "ÀîÏþæ¼", "°£À×", "2", "xiaojie"
        };
    
    char[] c1 = sList[0].toCharArray();
    char[] c2 = sList[1].toCharArray();
    byte[] b1 = sList[0].getBytes(Charset.forName("UTF-8"));
    byte[] b2 = sList[1].getBytes(Charset.forName("UTF-8"));
        
    Sort.quicksort(sList, new Compare (){

      @Override
      public int doCompare(Object arg0, Object arg1) {
        Collator collator = Collator.getInstance(java.util.Locale.CHINA);
        String tempname1 = (String) arg0;
        String tempname2 = (String) arg1;
        
        CollationKey c1 = collator.getCollationKey(tempname1);
        CollationKey c2 = collator.getCollationKey(tempname2);
//        return collator.compare(((CollationKey) c1).getSourceString(),
//                ((CollationKey) c2).getSourceString());
        return collator.compare(((CollationKey) c2).getSourceString(),
                ((CollationKey) c1).getSourceString());
      }
      
    });
    
    for(String s: sList) {
      System.out.println(s);
    }

  }

}
