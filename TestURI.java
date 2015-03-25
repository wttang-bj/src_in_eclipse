import java.net.URI;
import java.net.URISyntaxException;


public class TestURI {

  /**
   * @param args
   * @throws URISyntaxException 
   */
  public static void main(String[] args) throws URISyntaxException {
    // TODO Auto-generated method stub
    URI uri1 = new URI("hdfs://IBM3630m3-5:8020/user/hadoop/lisk/human_pmr/id/_temporary/_attempt_PlatformMapReduce1.5_0301_r_000106_11063");
    URI uriChild = new URI("hdfs://IBM3630m3-5:8020/user/hadoop/lisk/human_pmr/id/_temporary/_attempt_PlatformMapReduce1.5_0301_r_000106_11063/part-r-00106");
    System.out.println(uri1.relativize(uriChild));
    System.out.println(uriChild.relativize(uri1));
    
    System.out.println((int)(2147483648l));
    String path = "";
    int i =1;
    String filePath = path + "_" + (i < 10 ? ("0" + i) : i);
  }

}
