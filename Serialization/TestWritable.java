package Serialization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestWritable {

  /**
   * @param args
   * @throws IOException 
   */
  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
    WritablePerson p = new WritablePerson("Vince", 36, "Male");
    File f = new File("./writable.txt");
    if (f.exists()) f.delete();
    FileOutputStream outStream = new FileOutputStream(f);
    
    p.write(new DataOutputStream(outStream));    
    outStream.close();
    System.out.println("wrote:\n"+ p);
    System.out.println("------------------");
    
    //read
    FileInputStream inputStream = new FileInputStream(f);    
    WritablePerson got = new WritablePerson();
    got.readFields(new DataInputStream(inputStream));
    System.out.println("read:\n"+ got);
    
  }

}
