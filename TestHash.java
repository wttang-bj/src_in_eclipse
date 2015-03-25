import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;


public class TestHash {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.loadLibrary("NativeSort");
    PhoneNumber pn1 = new PhoneNumber("010-234234"), 
        pn2 = new PhoneNumber("011-123432"),
        pn3 = new PhoneNumber("020-123413");
    Map<PhoneNumber, String> ht = new Hashtable<PhoneNumber, String>(20, 0.8f);
    ht.put(pn1, "pn1");
    ht.put(pn2, "pn2");
    ht.put(pn3, "pn3");
    ht.put(new PhoneNumber("013-353425"), "pn4");
    ht.put(new PhoneNumber("033-353425"), "pn4");
    ht.put(new PhoneNumber("010-353425"), "pn5");
    Set<Entry<PhoneNumber, String>> members =  ht.entrySet();
    for(Entry<PhoneNumber, String> m : members) {
      System.out.println("Key:" + m.getKey() + " , HashCode of Key: " + m.getKey().hashCode());
    }    
    String s = new String("Fives");
    byte [] sb = s.getBytes();
    System.out.println("\n\nFive characters' byte size: " + sb.length);
    
    int integer = 20;
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    bs.write(1011111131);
    System.out.println("one int byte size: " + bs.toByteArray().length);
    
    Text t = new Text("Fives");
    System.out.println("Text size: " + t.getBytes().length);
    
    IntWritable i = new IntWritable(30564);
    
    int [][] iArray = new int[10][2];
  }
}
  class PhoneNumber {
    String number;
    
    public PhoneNumber (String number) {
      this.number = number;
    }
    
    public boolean equals(PhoneNumber other) {
      return this.number.equals(other.number);
    }
    
    @Override
    public int hashCode() {
      if (number == null) {
        return 0;
      }
      String stateCode = number.split("-")[0];
        
      return Integer.parseInt(stateCode);
    }
    
    public String toString() {
      return number;
    }
  }
  