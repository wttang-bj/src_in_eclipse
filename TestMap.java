import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;


public class TestMap {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Map<Test1, String> map = new HashMap<Test1, String>();
    Test1 t1 = new Test1();
    map.put(t1, "test1");
    map.put(new Test1(2), "test2");
   // Set<Test1> sett = map.keySet();
    t1.i=13;
    System.out.println(map.get(new Test1()));
    Buffer b ;
    b.array();
   DirectBuffer db;
    

  }
  
  static class Test1 {
    public int i = 1;
    public Test1(){
      
    }
    
    public Test1(int i){
      this.i = i;
    }
    @Override
    public boolean equals(Object t) {
      return false;
      
    }
    @Override
    public String toString(){
      return "";
    }
    
    @Override
    public int hashCode() {
      return i;
    }
  }

}
