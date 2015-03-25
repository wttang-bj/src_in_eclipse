import org.apache.hadoop.fs.Path;


public class TestPath {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Path p = new Path("hdfs://sym01:8080/test/file.txt");
    System.out.println(p.getName());
    System.out.println(p.toString());
    System.out.println(p.toString().replaceAll(":", "_"));
    System.out.println(p.toString().substring(0,  p.toString().lastIndexOf("/")));
  }

}
