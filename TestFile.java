import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.apache.hadoop.fs.Path;


public class TestFile {

  /**
   * @param args
   */
  public static void main(String[] args) {
    FileWriter fw;
    
    Path p = new Path("hdfs://bizm03bjhs23a11:9000/bb2/bb/cc/dd");
    System.out.println(p.getParent());
    
    try {
      fw = new FileWriter("C:\\hello.txt");
      String s = "hello world";  
      fw.write(s,0,s.length());  
      fw.flush();  
      OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("C:\\hello2.txt"));  
      osw.write(s,0,s.length());  
      osw.flush();  
      PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("c:\\hello3.txt")),true);  
      pw.println(s); 
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }  

  }

}
