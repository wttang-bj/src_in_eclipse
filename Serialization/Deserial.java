package Serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserial {

  /**
   * @param args
   * @throws IOException 
   * @throws ClassNotFoundException 
   */
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    // TODO Auto-generated method stub
    File f = new File("./serializated.txt");
    if (!f.exists()) {
      System.out.println("Not exists");
      return;
    }
    
    FileInputStream fis = new FileInputStream(f);
    ObjectInputStream oim = new ObjectInputStream(fis);
    JavaPerson p = (JavaPerson)oim.readObject();
    System.out.println(p);
  }

}
