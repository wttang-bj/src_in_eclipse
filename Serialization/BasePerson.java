package Serialization;

import java.io.Serializable;

// This Base class must implement Serializable, 
// Otherwise, those members can not be serialized/de-serialized
public abstract class BasePerson implements Serializable{
  /**
   * 
   */
  protected String name;
  protected int age;
  protected String sex;
  
  public BasePerson(){}
  
  public BasePerson(String name
      , int age
      , String sex
      ) {
    this.name = name;
    this.age = age;
    this.sex = sex;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Object[" + super.toString() + "]:" 
        + "\n\tName:\t" + this.name
        + "\n\tAge:\t" + this.age
        + "\n\tSex:\t" + this.sex);
    return sb.toString();
  }
  
}
