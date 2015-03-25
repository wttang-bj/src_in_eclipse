package Serialization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serial {

  /**
   * @param args
   * @throws IOException 
   */
  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
    JavaPerson p1 = new JavaPerson("Michael", 29, "Male");
    File f = new File("./serializated.txt");
    if (f.exists()) f.delete();
    FileOutputStream oStream = new FileOutputStream(f);
    ObjectOutputStream ooStream = new ObjectOutputStream(oStream);
    ooStream.writeObject(p1);
    ooStream.close();
    
    System.out.println("Wrote - " + p1 + "\n" + f.exists());
  }

}
