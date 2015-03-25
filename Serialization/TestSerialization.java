package Serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSerialization {

  /**
   * @param args
   * @throws IOException 
   * @throws ClassNotFoundException 
   */
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    // TODO Auto-generated method stub
    JavaPerson p1 = new JavaPerson("Michael", 29, "Male");
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    ObjectOutputStream oop = new ObjectOutputStream(outStream);
    oop.writeObject(p1);
    byte[] bytes = outStream.toByteArray();
    oop.close();
    for(byte b: bytes) {
      System.out.print(b +" ");
    }
    System.out.println();
    
    ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
    ObjectInputStream oip = new ObjectInputStream(inputStream);
    JavaPerson got = new JavaPerson();
    Object gO = oip.readObject();
    oip.close();
    if (gO instanceof BasePerson) {
      System.out.println((BasePerson)gO);
    }
    System.out.println(gO.getClass());
    
  }

}
